package com.github.lookout.whoas

import spock.lang.*

class InMemoryQueueSpec extends Specification {

    def "getSize() should return 0 by default"() {
        given:
        InMemoryQueue queue = new InMemoryQueue()

        expect:
        queue.size == 0
    }
}
