package rabbitMQ.exchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class SimpleExchangeProducer {

    private static final String EXCHANGE_NAME = "MY_EXCHANGE";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (Connection connection = connectionFactory.newConnection()){
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
            String message = "Hello Exchange";
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
            System.out.println("[x] Sent '" + message + "'");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
