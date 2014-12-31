package com.github.lookout.whoas

import java.util.concurrent.ArrayBlockingQueue
import spock.lang.*

class InMemoryQueueSpec extends Specification {

    def "getSize() should return 0 by default"() {
        given:
        InMemoryQueue queue = new InMemoryQueue()

        expect:
        queue.size == 0
    }
}


class InMemoryQueueWithGivenQueueSpec extends Specification {
    def "getSize() should return 0 by default"() {
        given:
        ArrayBlockingQueue internal = new ArrayBlockingQueue(1)
        InMemoryQueue queue = new InMemoryQueue(internal)

        expect:
        queue.size == 0
    }
}
