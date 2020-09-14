package net.loyintean.springmvcbase.common.aop;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * 重复处理的AOP实现类
 *
 * @author Snoopy
 */
@Aspect
@Component
public class RepeatableAspectImpl implements ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(RepeatableAspectImpl.class);

    private static final SpelExpressionParser PARSER = new SpelExpressionParser();
    private ApplicationContext ctx;

    @Resource
    private Redisson redissonClient;

    @Around("@annotation(r)")
    public Object doRepeatableRequest(ProceedingJoinPoint p, Repeatable r) throws Throwable {
        log.debug("重复请求处理，开始。pjp:{}", p);

        String lockKey = parseLockKey(p, r);

        if (StringUtils.isBlank(lockKey)) {
            // key是空字符串，意为不需要加锁，直接进行幂等处理
            return idempotent(p, r);
        } else {
            // key非空，则先加分布式锁、后进行幂等处理
            return lockAndIdempotent(p, r, lockKey);
        }

    }

    private Object lockAndIdempotent(ProceedingJoinPoint p, Repeatable r, String lockKey) throws Throwable {
        RLock lock = redissonClient.getLock(r.lockPrefix() + lockKey);

        try {
            if (lock.tryLock(r.lockWaitTime(), r.lockReleaseTime(), TimeUnit.MILLISECONDS)) {
                log.debug("重复请求处理，并发加锁成功。p:{}, lockKey:{}", p, lockKey);
                return idempotent(p, r);
            } else {
                // 锁定失败，直接抛出异常
                log.warn("重复请求处理，并发加锁失败。p:{}, lockKey:{}", p, lockKey);
                throw new IdempotentException(p, lockKey);
            }
        } finally {
            if (lock.isLocked()) {
                try {
                    lock.unlock();
                } catch (Exception e) {
                    log.warn("重复请求处理，解锁发生异常，继续执行逻辑。p:{}", p, e);
                }
            }
        }
    }

    private Object idempotent(ProceedingJoinPoint p, Repeatable r) throws Throwable {
        Object result;

        if (StringUtils.isNotBlank(r.idempotentBean())) {
            // 从上下文中获取一个bean
            Object idempotentBean = ctx.getBean(r.idempotentBean(), p.getTarget().getClass());
            MethodSignature m = (MethodSignature) p.getSignature();
            Method method = idempotentBean.getClass().getDeclaredMethod(m.getName(), m.getParameterTypes());
            result = method.invoke(idempotentBean, p.getArgs());
        } else {
            // 没有声明幂等处理的bean，则直接按原方法处理
            result = null;
        }
        if (result == null) {
            result = p.proceed();
        }
        log.debug("重复请求处理，幂等处理完成。p:{}, r:{}, result:{}", p, r, result);
        return result;
    }

    private String parseLockKey(ProceedingJoinPoint p, Repeatable r) {
        String key;

        Object[] args = p.getArgs();
        if (args != null && args.length > 0) {
            MethodSignature m = (MethodSignature) p.getSignature();

            String[] names = m.getParameterNames();

            EvaluationContext ctx = new StandardEvaluationContext();
            for (int i = 0; i < names.length; i++) {
                ctx.setVariable(names[i], args[i]);
            }

            Expression exp = PARSER.parseExpression(r.lockKey());
            key = exp.getValue(ctx, String.class);
        } else {
            key = r.lockKey();
        }

        log.debug("重复请求处理，解析得到分布式锁key. pjp:{}, key:{}", p, key);
        return key;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.ctx = applicationContext;
    }

    public static class IdempotentException extends Throwable {

        private ProceedingJoinPoint pjp;
        private String lockKey;

        public IdempotentException(ProceedingJoinPoint p0, String lockKey) {
            this.pjp = p0;
            this.lockKey = lockKey;
        }

        @Override
        public String getMessage() {
            return "对 " + pjp + " 进行并发处理时发生异常：并发锁竞争失败。并发锁key = " + lockKey;
        }
    }
}
