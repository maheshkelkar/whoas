package com.github.lookout.whoas

import groovy.transform.InheritConstructors

/**
 * The SequentialHookRunner is will dequeue HookRequest items from the
 * configured AbstractHookQueue and publish those webhooks sequentially.
 *
 * This is the simplest and slowest hook runner
 */
@InheritConstructors
class SequentialHookRunner extends AbstractHookRunner {

    /** Execute an infinitely blocking single-threaded runloop */
    void run() {
        while (this.keepGoing) {
            this.queue.pop { HookRequest request ->
                this.publisher.publish(request)
            }
        }
    }
}
