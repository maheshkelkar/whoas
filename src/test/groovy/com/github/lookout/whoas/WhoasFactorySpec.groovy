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
        whoasFactory.setQueueType("com.github.lookout.whoas.InMemoryQueue")
        InMemoryQueue inMemoryQueue = whoasFactory.buildQueue()

        expect:
        inMemoryQueue instanceof InMemoryQueue

    }

    def "given a invalid class name, buildQueue should throw ClassNotFound exception"() {
        when:
        WhoasFactory whoasFactory = new WhoasFactory()
        whoasFactory.setQueueType("com.github.lookout.InvalidQueue")
        AbstractHookQueue abstractHookQueue =  whoasFactory.buildQueue()

        then:
        thrown(ClassNotFoundException)

    }
    def "given a runner class name, buildQueue should create specified queue"() {
        given:
        WhoasFactory whoasFactory = new WhoasFactory()
        whoasFactory.setRunnerType("com.github.lookout.whoas.SequentialHookRunner")
        SequentialHookRunner sequentialHookRunner = whoasFactory.buildRunner(whoasFactory.buildQueue())

        expect:
        sequentialHookRunner instanceof SequentialHookRunner

    }

    def "given a invalid class name, buildRunner should throw ClassNotFound exception"() {
        when:
        WhoasFactory whoasFactory = new WhoasFactory()
        whoasFactory.setRunnerType("com.github.lookout.whoas.Invalidunner")
        AbstractHookRunner abstractHookRunner = whoasFactory.buildRunner(whoasFactory.buildQueue())

        then:
        thrown(ClassNotFoundException)

    }
}