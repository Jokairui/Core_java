package rocketMQ;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.*;

public class TransactionDemo {

    public static void main(String[] args) throws MQClientException, InterruptedException {
        TransactionMQProducer producer = new TransactionMQProducer("Khirye-Group");
        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2000), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("client-transaction-msg-check-thread");
                return thread;
            }
        });

        producer.setExecutorService(executorService);
        TransactionListener transactionListener = new TransactionListenerImpl();
        producer.setTransactionListener(transactionListener);
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 10; i++) {
            Message message = new Message("TransactionTest", tags[i % tags.length], "KEY" + i, ("HELLO RocketMQ" + i).getBytes());
            SendResult sendResult = producer.sendMessageInTransaction(message, null);
            System.out.printf("%s%n", sendResult);
            Thread.sleep(10);
        }

//        for (int i = 0; i < 100000; i++) {
//            Thread.sleep(1000);
//        }
        producer.shutdown();
    }
}
