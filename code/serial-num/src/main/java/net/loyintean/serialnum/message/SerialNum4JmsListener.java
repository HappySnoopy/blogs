package net.loyintean.serialnum.message;

import net.loyintean.serialnum.utils.SerialNumUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Pointcut;

import javax.jms.JMSException;
import javax.jms.Message;

/**
 * 使用JMS的MessageListener时的拦截器ß
 *
 * @author Snoopy
 * @date 2020年11月12日
 */
public class SerialNum4JmsListener extends BaseSerialNumMessageListener {

    /**
     * 所有实现了接口的地方都要拦截上
     */
    @Override
    @Pointcut("execution(public void javax.jms.MessageListener+.onMessage(javax.jms.Message))")
    public void pointcut() {
    }

    /**
     * 优先从Message中获取
     *
     * @param joinPoint 其诶单
     * @return 从对方传过来的流水号。有可能为null
     * @throws JMSException 取消息信息时可能抛出此异常
     * @see Message#getStringProperty(String)
     */
    @Override
    public Object getSerialNum(ProceedingJoinPoint joinPoint) throws JMSException {
        // 先尝试从message中拿到对方传过来的值
        Message message = (Message) joinPoint.getArgs()[0];
        return message.getStringProperty(SerialNumUtils.SERIAL_NUM_KEY);
    }
}
