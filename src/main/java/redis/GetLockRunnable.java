package redis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.util.Objects;
import java.util.UUID;

public class GetLockRunnable implements Runnable{

    private static final Logger logger = LogManager.getLogger(GetLockRunnable.class);

    private Jedis jedis = SingletonJedis.INSTANCE.getJedis();
    private String lockVersion = UUID.randomUUID().toString();
    @Override
    public void run() {
        while (true) {
            final String getLockResult = jedis.get("testLock");
            if (getLockResult == null || getLockResult.equals("")) {
                Object success = jedis.eval("return redis.call('set', 'testLock', '" + lockVersion + "', 'NX', 'PX' , 10000)");
                //到这一步也可能设置lock失败
                //因为同时有多个client在尝试加锁
                if (Objects.nonNull(success)) {
                    logger.info("set lock success! version is {}", lockVersion);
                    break;
                } else {
                    logger.info("set lock failed! Retry...");
                }
            } else {
                logger.info("the lock exists! Retry...");
            }
        }

    }
}
