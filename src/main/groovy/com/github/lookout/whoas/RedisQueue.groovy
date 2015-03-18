package com.github.lookout.whoas

import com.fasterxml.jackson.databind.ObjectMapper
import redis.clients.jedis.Jedis;

/**
 * A redis queue that offers distributed and persistent queue
 */
class RedisQueue extends AbstractHookQueue {
    private final RedisClientFactory redisClientFactory
    private static nextId = 0
    private String hostname
    private Integer port

    /**
     * Create the RedisQueue with valid config
     */
    RedisQueue(WhoasQueueConfig queueConfig) {
        redisClientFactory = RedisClientFactory.getInstance()
        this.hostname = queueConfig.hostname
        this.port = queueConfig.port
    }

    /**
     * Default constructor
     */
    RedisQueue() {
        redisClientFactory = RedisClientFactory.getInstance()
        this.hostname = "localhost"
        this.port = 6379
    }
    /**
     * Return the number of elements in the queue
     */
    Long getSize() {
        if (!this.started) {
            throw new Exception("Queue must be started before this operation is invoked")
        }
        Jedis redisclient = null
        Long queueSize = 0
        try {
            redisclient = redisClientFactory.getJedis()
            queueSize = redisclient.llen("queue")
        } catch (all) {
        } finally {
            if (redisclient != null) {
                redisClientFactory.returnJedis(redisclient)
            }
        }
        return queueSize
    }

    /**
     * Setup the Redis client factory
     */
    @Override
    void start() {
        super.start()
        redisClientFactory.start(this.hostname, this.port)
    }

    /**
     * Stop the Redis client factory
     */
    @Override
    void stop() {
        super.stop()
        redisClientFactory.stop()
    }

    /**
     * Performs a blocking pop on the queue and invokes the closure with the
     * item popped from the queue
     *
     * If the Closure throws an exception, the dequeued item will be returned
     * to the tail end of the queue
     */
    void pop(Closure action) {
        if (action == null) {
            throw new Exception("Must provide a Closure to RedisQueue.pop()")
        }

        if (!this.started) {
            throw new Exception("Queue must be started before this operation is invoked")
        }

        List<String> messages = null
        Jedis redisclient = null
        try {
            redisclient = redisClientFactory.getJedis()
            messages = redisclient.blpop(0, "queue");

            /* Decode message */
            if (messages) {
                ObjectMapper mapper = new ObjectMapper()
                HookRequest request = mapper.readValue(messages.get(1), HookRequest.class)
                action.call(request)
            }
        } catch (all) {
            /* Put this back on the front of the queue */
            if (messages) {
                redisclient.lpush("queue", messages.get(1))
            }
        } finally {
            if (redisclient != null) {
                redisClientFactory.returnJedis(redisclient)
            }
        }
    }

    /**
     * Attempt to insert the request into the queue
     *
     * If the request cannot be inserted, this method will return false,
     * otherwise true.
     */
    Boolean push(HookRequest request) {
        if (!this.started) {
            throw new Exception("Queue must be started before this operation is invoked")
        }

        request.id = ++nextId
        ObjectMapper mapper = new ObjectMapper()
        String jsonPayload = mapper.writeValueAsString(request)
        Jedis redisclient = null
        Integer ret = 0
        try {
            redisclient = redisClientFactory.getJedis()
            ret = redisclient.rpush("queue", jsonPayload)
        } catch (Exception e) {
        } finally {
            if (redisclient != null) {
                redisClientFactory.returnJedis(redisclient)
            }
        }
        return ret == 1;
    }
}
