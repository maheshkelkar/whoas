package com.github.lookout.whoas


import java.util.concurrent.LinkedBlockingQueue

/**
 * A simple in-memory queue that offers no persistence between process restarts
 */
class InMemoryQueue implements IHookQueue {
    private LinkedBlockingQueue internalQueue

    InMemoryQueue() {
        this.internalQueue = new LinkedBlockingQueue()
    }

    Long getSize() {
        return this.internalQueue.size()
    }

    void pop(Closure action) {
    }

    Boolean enqueue(HookRequest request) {
    }
}
