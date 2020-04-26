package redis;

import redis.clients.jedis.Jedis;

public enum SingletonJedis {
    INSTANCE;

    private Jedis jedis = new Jedis("127.0.0.1");

    public Jedis getJedis() {
        return jedis;
    }
}
