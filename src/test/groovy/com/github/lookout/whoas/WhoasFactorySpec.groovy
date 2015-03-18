package com.github.lookout.whoas

import spock.lang.*

class WhoasFactorySpec extends Specification {

    def "by default buildQueue should create InMemoryQueue"() {
        given:
        WhoasFactory whoasFactory = new WhoasFactory()
        InMemoryQueue inMemoryQueue = whoasFactory.buildQueue()

        expect:
        inMemoryQueue instanceof InMemoryQueue
    }

    def "by default buildRunner should create SequentialHookRunner"() {
        given:
        WhoasFactory whoasFactory = new WhoasFactory()
        SequentialHookRunner sequentialHookRunner = whoasFactory.buildRunner(
                                                                     whoasFactory.buildQueue())

        expect:
        sequentialHookRunner instanceof SequentialHookRunner
    }

    def "given a queue class name, buildQueue should create specified queue"() {
        given:
        WhoasFactory whoasFactory = new WhoasFactory()
        whoasFactory.queueConfig.type = "com.github.lookout.whoas.InMemoryQueue"
        InMemoryQueue inMemoryQueue = whoasFactory.buildQueue()

        expect:
        inMemoryQueue instanceof InMemoryQueue
    }

    def "given a invalid class name, buildQueue should throw ClassNotFound exception"() {
        when:
        WhoasFactory whoasFactory = new WhoasFactory()
        whoasFactory.queueConfig.type = "com.github.lookout.InvalidQueue"
        AbstractHookQueue abstractHookQueue =  whoasFactory.buildQueue()

        then:
        thrown(ClassNotFoundException)

    }
    def "given a runner class name, buildQueue should create specified queue"() {
        given:
        WhoasFactory whoasFactory = new WhoasFactory()
        whoasFactory.runnerType = "com.github.lookout.whoas.SequentialHookRunner"
        SequentialHookRunner sequentialHookRunner = whoasFactory.buildRunner(whoasFactory.buildQueue())

        expect:
        sequentialHookRunner instanceof SequentialHookRunner

    }

    def "given a invalid class name, buildRunner should throw ClassNotFound exception"() {
        when:
        WhoasFactory whoasFactory = new WhoasFactory()
        whoasFactory.runnerType = "com.github.lookout.whoas.Invalidunner"
        AbstractHookRunner abstractHookRunner = whoasFactory.buildRunner(whoasFactory.buildQueue())

        then:
        thrown(ClassNotFoundException)
    }

    def "Create RedisQueue with default hostname and port config"() {
        given:
        WhoasFactory whoasFactory = new WhoasFactory()
        whoasFactory.queueConfig.type = "com.github.lookout.whoas.RedisQueue"
        RedisQueue redisQueue = whoasFactory.buildQueue()

        expect:
        redisQueue instanceof RedisQueue
        redisQueue.queueConfig.hostname == "localhost"
        redisQueue.queueConfig.port == 6379
        redisQueue.queueConfig.key == "queue"
    }

    def "Create RedisQueue with non-default hostname and port config"() {
        given:
        WhoasFactory whoasFactory = new WhoasFactory()
        whoasFactory.queueConfig.type = "com.github.lookout.whoas.RedisQueue"
        whoasFactory.queueConfig.hostname = "redis.lookout.com"
        whoasFactory.queueConfig.port = 1234
        whoasFactory.queueConfig.key = "foo"
        RedisQueue redisQueue = whoasFactory.buildQueue()

        expect:
        redisQueue instanceof RedisQueue
        redisQueue.queueConfig == whoasFactory.queueConfig
    }
}