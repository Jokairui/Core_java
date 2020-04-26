package rabbitMQ.route;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class SimpleRoutePruducer {

    private static final String EXCHANGE_NAME = "log";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        try (Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");
            String message = "Hello , rabbit routing!";
            channel.basicPublish(EXCHANGE_NAME, "info", null, message.getBytes());
            System.out.println("[x] Sent '" + message + "'");
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
