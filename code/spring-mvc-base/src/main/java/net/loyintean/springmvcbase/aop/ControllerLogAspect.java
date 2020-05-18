package net.loyintean.springmvcbase.aop;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The type ControllerLogAspect.
 * <p>
 * TODO linjun 2020-05-18  找个接口把各种Mapping注解统一起来
 *
 * @author 林俊 <junlin8@creditease.cn>
 * @date 2020 -05-18
 */
@Aspect
@Component
@Slf4j
public class ControllerLogAspect {

    private static final String URL_SPLIT = "/";

    private static String combineUrl(String onClass, String onMethod) {
        log.debug("onClass:{}, onMethod:{}", onClass, onMethod);
        if (!onClass.endsWith(URL_SPLIT) && !onMethod.startsWith(URL_SPLIT)) {
            // 都没有的情况下，加一个
            return onClass + URL_SPLIT + onMethod;
        } else if (onClass.endsWith(URL_SPLIT) && onMethod.startsWith(URL_SPLIT)) {
            // 都有的情况下，删一个
            return onClass + onMethod.substring(1);
        } else {
            // 一个有、一个没有的情况下，直接相加
            return onClass + onMethod;
        }
    }

    @Around("@annotation(mapping)")
    public Object log(ProceedingJoinPoint joinPoint, PostMapping mapping) throws Throwable {
        return doLog(joinPoint, new Mapping4PostRequest(mapping));
    }

    @Around("@annotation(mapping)")
    public Object log(ProceedingJoinPoint joinPoint, GetMapping mapping) throws Throwable {
        return doLog(joinPoint, new Mapping4GetRequest(mapping));
    }

    @Around("@annotation(mapping)")
    public Object log(ProceedingJoinPoint joinPoint, PutMapping mapping) throws Throwable {
        return doLog(joinPoint, new Mapping4PutRequest(mapping));
    }


    @Around("@annotation(mapping)")
    public Object log(ProceedingJoinPoint joinPoint, DeleteMapping mapping) throws Throwable {
        return doLog(joinPoint, new Mapping4DeleteRequest(mapping));
    }

    private Object doLog(ProceedingJoinPoint joinPoint, Mapping mapping) throws Throwable {

        log.debug("joinPoint:{}, mapping:{}", joinPoint, mapping.value());

        long start = System.currentTimeMillis();

        joinPoint.getTarget();
        joinPoint.getArgs();
        Object result = null;
        Throwable e = null;
        try {
            result = joinPoint.proceed();
            return result;
        } catch (Throwable throwable) {
            e = throwable;
            throw e;
        } finally {
            long end = System.currentTimeMillis();

            log.info("访问路径：{}; 类和方法：{}#{}(), 参数：{}, 结果:{}, 用时:{} ms, 异常（可能有）:{}", parseUrl(joinPoint, mapping),
                    joinPoint.getTarget().getClass(), joinPoint.getSignature().getName(), parseArgs(joinPoint), result,
                    (end - start), e);
        }

    }

    /**
     * Log object.
     *
     * @param joinPoint the join point
     * @param mapping   the mapping
     * @return the object
     */
    @Around("@annotation(mapping)")
    public Object log(ProceedingJoinPoint joinPoint, RequestMapping mapping) throws Throwable {
        return doLog(joinPoint, new Mapping4Request(mapping));
    }

    private String parseUrl(ProceedingJoinPoint joinPoint, Mapping mapping) {
        StringBuilder url = new StringBuilder(48);

        Set<String> urlOnClass;
        try {
            urlOnClass = Optional.of(joinPoint).map(ProceedingJoinPoint::getTarget).map(Object::getClass)
                    .map(c -> c.getAnnotation(RequestMapping.class)).map(RequestMapping::value).map(Stream::of)
                    .map(s -> s.filter(StringUtils::isNotBlank).collect(Collectors.toSet()))
                    .orElse(Collections.emptySet());
        } catch (Exception e) {
            log.warn("无法获取class上的RequestMapping。默认为空集。", e);
            urlOnClass = Collections.emptySet();
        }

        Set<String> urlOnMethod;
        try {
            urlOnMethod = Optional.ofNullable(mapping).map(Mapping::value)
                    .map(vs -> Stream.of(vs).filter(StringUtils::isNotBlank).collect(Collectors.toSet()))
                    .orElse(Collections.emptySet());
        } catch (Exception e) {
            log.warn("无法获取method上的RequestMapping。默认为空集。", e);
            urlOnMethod = Collections.emptySet();
        }

        log.debug("urlOnClass:{}, urlOnMethod:{}", urlOnClass, urlOnMethod);

        try {
            boolean urlOnClassEmpty = CollectionUtils.isEmpty(urlOnClass);
            boolean urlOnMethodEmpty = CollectionUtils.isEmpty(urlOnMethod);

            if (!urlOnClassEmpty && !urlOnMethodEmpty) {
                // 这是最常见的情况
                // 类上面和方法上面都配置了
                final Set<String> finalUrlOnClass = urlOnClass;
                url.append(urlOnMethod.stream().flatMap(m -> finalUrlOnClass.stream().map(c -> combineUrl(c, m)))
                        .collect(Collectors.joining(";")));
            } else if (!urlOnClassEmpty) {
                // 这种稍微少见一点：类上配置了，方法上没有配置
                url.append(String.join(";", urlOnClass));
            } else if (!urlOnMethodEmpty) {
                // 这种情况应该会更少见：类上没有配置，方法上有配置
                url.append(String.join(";", urlOnMethod));
            } else {
                // 最后一种情况：都没有配置。
                url.append(URL_SPLIT);
            }
        } catch (Exception e) {
            log.warn("无法获取的RequestMapping。默认为空。", e);
        }

        return url.toString();
    }

    private String parseArgs(ProceedingJoinPoint joinPoint) {
        StringBuilder argsBuilder = new StringBuilder(32);

        Object[] args = joinPoint.getArgs();
        try {
            // 只针对方法切面做处理
            if (joinPoint.getSignature() instanceof MethodSignature) {
                MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
                String[] parameterNames = methodSignature.getParameterNames();
                for (int i = 0; i < parameterNames.length; i++) {
                    argsBuilder.append(parameterNames[i]).append('=').append(args[i]);
                }
            }
        } catch (Exception e) {
            log.warn("无法获取方法上的参数名和参数值，采用默认处理。", e);
            argsBuilder.append(Arrays.toString(args));
        }

        return argsBuilder.toString();
    }


    private interface Mapping {
        String[] value();
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class Mapping4Request implements Mapping {
        @Delegate
        private RequestMapping mapping;
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class Mapping4PostRequest implements Mapping {
        @Delegate
        private PostMapping mapping;
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class Mapping4GetRequest implements Mapping {
        @Delegate
        private GetMapping mapping;
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class Mapping4PutRequest implements Mapping {
        @Delegate
        private PutMapping mapping;
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class Mapping4DeleteRequest implements Mapping {
        @Delegate
        private DeleteMapping mapping;
    }

    private static class Mapping4PatchRequest implements Mapping {
        @Delegate
        private PatchMapping mapping;
    }
}
