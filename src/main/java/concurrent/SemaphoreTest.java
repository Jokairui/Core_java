package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < 20; i++) {
            final int index = i;
            Runnable runnable = () -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Accessing" + index);
                try {
                    Thread.sleep((long) (Math.random() * 10000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                semaphore.release();
                System.out.println("available semaphore: " + semaphore.availablePermits());
            };
            executorService.execute(runnable);
        }
    }
}
