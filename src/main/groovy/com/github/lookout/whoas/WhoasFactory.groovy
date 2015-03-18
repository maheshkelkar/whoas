package com.github.lookout.whoas

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.NotEmpty;

/**
 *  This factory will allow clients of whoas to build
 *  different queues like in memory, persistent etc and runners
 *  like sequential.
 */
public class WhoasFactory {

    /**
     *  Queue configuration
     */
    @JsonProperty
    WhoasQueueConfig queueConfig = new WhoasQueueConfig()

    /**
     * Type of runner to create in whoas.
     *
     * Default runner in whoas is SequentialHookRunner
     */
    @JsonProperty
    String runnerType = "com.github.lookout.whoas.SequentialHookRunner"

    /**
     * Allocate and return the queue based on stored queue type.
     *
     * If the queue cannot be created, then this throws
     * ClassNotFoundException - if the class is not found
     * IllegalAccessException - if the class or its nullary constructor is not accessible.
     * InstantiationException - if this Class represents an abstract class, an interface,
     *                          an array class, a primitive type, or void;
     *                          or if the class has no nullary constructor;
     *                          or if the instantiation fails for some other reason.
     * @return allocated queue
     */
    public AbstractHookQueue buildQueue() {
        return Class.forName(this.queueConfig.type).getConstructor(WhoasQueueConfig.class).
                newInstance(queueConfig)
    }

    /**
     * Allocate and return runner based on stored runner type
     *
     * If the runner cannot be created, then this throws
     * ClassNotFoundException - if the class is not found
     * IllegalAccessException - if the class or its nullary constructor is not accessible.
     * InstantiationException - if this Class represents an abstract class, an interface,
     *                          an array class, a primitive type, or void;
     *                          or if the class has no nullary constructor;
     *                          or if the instantiation fails for some other reason.
     * @param hookQueue queue to associate with allocated runner
     * @return
     */
    public AbstractHookRunner buildRunner(AbstractHookQueue hookQueue) {
        return Class.forName(this.runnerType).getConstructor(AbstractHookQueue.class).
                newInstance(hookQueue)
    }
}