package redis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RedisTest {
    private static final Logger log = LogManager.getLogger("redis");
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        GetLockRunnable getLockRunnable = new GetLockRunnable();
        for(int i = 0; i < 100; i++) {
            executorService.submit(getLockRunnable);
        }
    }
}
