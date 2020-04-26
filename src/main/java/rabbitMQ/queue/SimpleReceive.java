package rabbitMQ.queue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class SimpleReceive {
    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("localhost");
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String (delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received " + message);
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), true);
            };
            channel.basicConsume(QUEUE_NAME, false, deliverCallback, consumerTag -> {});
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
