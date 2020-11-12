package net.loyintean.serialnum.message;

import net.loyintean.serialnum.utils.SerialNumUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

import java.util.Optional;


/**
 * 拦截AMQP协议的消息处理器
 *
 * @author Snoopy
 * @date 2020年11月12日
 */
public class SerialNum4AmqpListener extends BaseSerialNumMessageListener {

    /**
     * 所有实现了接口的地方都要拦截上
     */
    @Override
    @Pointcut("execution(public void org.springframework.amqp.core.MessageListener+.onMessage(org.springframework.amqp.core.Message))")
    public void pointcut() {
    }

    /**
     * 优先从Message中获取
     *
     * @param joinPoint 其诶单
     * @return 从对方传过来的流水号。有可能为null
     * @see Message#getMessageProperties()
     * @see MessageProperties#getHeaders()
     */
    @Override
    public Object getSerialNum(ProceedingJoinPoint joinPoint) {
        // 先尝试从message中拿到对方传过来的值
        return Optional.of(joinPoint).map(JoinPoint::getArgs)
                .map(t -> (Message) t[0])
                .map(Message::getMessageProperties)
                .map(MessageProperties::getHeaders)
                .map(h -> h.get(SerialNumUtils.SERIAL_NUM_KEY))
                .orElse(null);
    }
}
