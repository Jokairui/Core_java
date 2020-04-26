package rocketMQ;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class SimpleProducerDemo {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("Test-Producer-Group-1");
        defaultMQProducer.setNamesrvAddr("localhost:9876");
        defaultMQProducer.start();
        defaultMQProducer.setRetryTimesWhenSendAsyncFailed(0);
        for(int i = 0 ; i <= 100; i++) {
            Message message = new Message("TopicTest", "TagA", "OrderID188", ("Hello RocketMQ" + i).getBytes());
            SendResult sendResult = defaultMQProducer.send(message);
            System.out.printf("%s%n", sendResult);
        }
        defaultMQProducer.shutdown();
    }
}
