package com.github.lookout.whoas

import java.util.Queue
import java.util.concurrent.LinkedBlockingQueue

/**
 * A simple in-memory queue that offers no persistence between process restarts
 */
class InMemoryQueue implements IHookQueue {
    private Queue internalQueue

    /**
     * Create the InMemoryQueue with it's own internal queueing implementation
     */
    InMemoryQueue() {
        this.internalQueue = new LinkedBlockingQueue()
    }

    /**
     * Create the InMemoryQueue with the given Queue object
     */
    InMemoryQueue(Queue queue) {
        this.internalQueue = queue
    }

    Long getSize() {
        return this.internalQueue.size()
    }

    void pop(Closure action) {
    }

    Boolean enqueue(HookRequest request) {
    }
}
