package com.github.lookout.whoas

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClientFactory {
    private static final RedisClientFactory instance = new RedisClientFactory();
    private static JedisPool pool;
    private RedisClientFactory() {}
    public final static RedisClientFactory getInstance() {
        return instance;
    }
    public void start() {
        /* Set JedisPoolConfig */
        JedisPoolConfig poolConfig = new JedisPoolConfig();

        /* Set max active connections to Redis instance */
        poolConfig.setMaxTotal(10);

        /* Tests whether connection is dead when connection
         * retrieval method is called
         */
        poolConfig.setTestOnBorrow(true);

        /* Tests whether connection is dead when returning a connection
         * to the pool
         * */
        poolConfig.setTestOnReturn(true);

        /* Number of connections to Redis that just sit there and do
         * nothing
         * */
        poolConfig.setMaxIdle(5);

        /* Minimum number of idle connections to Redis
         * These can be seen as always open and ready to serve
         * */
        poolConfig.setMinIdle(1);

        /* Tests whether connections are dead during idle periods */
        poolConfig.setTestWhileIdle(true);

        /* Maximum number of connections to test in each idle check */
        poolConfig.setNumTestsPerEvictionRun(10);

        /* Idle connection checking period */
        poolConfig.setTimeBetweenEvictionRunsMillis(60000);

        /* Create the jedisPool */
        pool = new JedisPool(poolConfig, "localhost", 6379);
    }
    public void stop() {
        pool.destroy();
    }
    public Jedis getJedis() {
        return pool.getResource();
    }
    public void returnJedis(Jedis jedis) {
        jedis.close()
    }
}