package com.github.lookout.whoas


/**
 * Interface defining how 'HookQueue' providers should behave
 *
 * This allows for different queueing implementations behind whoas
 */
interface IHookQueue {
    /**
     * Return the size of the queue, may not be implemented by some providers
     * in which case it will return -1
     */
    Long getSize()


    /**
     *
     */
    void pop(Closure action)

    /**
     *
     */
    Boolean push(HookRequest request)
}
