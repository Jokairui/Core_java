package concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(2);


        Runnable runnable = () -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("the other thread is count down");
            countDownLatch.countDown();
        };

        new Thread(runnable).start();

        countDownLatch.countDown();
        System.out.println("waiting");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("start to working");
    }
}
