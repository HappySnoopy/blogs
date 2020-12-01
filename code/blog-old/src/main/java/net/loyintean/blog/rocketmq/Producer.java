package net.loyintean.blog.rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * 生产者
 *
 * @author 林俊
 */
public class Producer {


    public static void main(
            String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {

        // 还可以指定一个RPCHook，用于在发送请求和接收响应时做点处理。
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("Snoopy-test");

        //设置NameServer地址,此处应改为实际NameServer地址，多个地址之间用；分隔
        //NameServer的地址必须有，但是也可以通过环境变量的方式设置，不一定非得写死在代码里
        defaultMQProducer.setNamesrvAddr("10.141.11.41::9876;10.141.11.42:9876");

        // TODO 看看其它配置项

        // 启动producer实例。一个实例只需要启动一次
        defaultMQProducer.start();

        // 发送一条消息
        Message message = new Message("Snoopy-test", "B", "Snoopy ceshi RocketMq".getBytes());

        SendResult sendResult = defaultMQProducer.send(message);
        System.out.println(sendResult);

        defaultMQProducer.shutdown();
    }
}
