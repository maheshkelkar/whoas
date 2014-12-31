package com.github.lookout.whoas


/**
 *
 */
abstract class AbstractHookRunner {
    protected AbstractHookQueue queue
    protected Publisher publisher
    protected Boolean keepGoing = true

    AbstractHookRunner(AbstractHookQueue hookQueue) {
        this(hookQueue, new Publisher())
    }

    AbstractHookRunner(AbstractHookQueue hookQueue, Publisher hookPublisher) {
        this.publisher = hookPublisher
        this.queue = hookQueue
    }

    Publisher getPublisher() {
        return this.publisher
    }

    /** Block forever and run the runner's runloop. */
    abstract void run()

    /**
     * Tell the runloop to stop
     *
     * This will only come into effect after the runner has completed it's
     * currently executing work
     */
    void stop() {
        this.keepGoing = false
    }
}
