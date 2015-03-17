package com.github.lookout.whoas

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.NotEmpty;

/**
 *  Class to create different types of queues and runners
 */
public class WhoasFactory {

    /**
     *  Type of queue to create in whoas
     */
    @JsonProperty
    @NotEmpty
    String queueType

    /**
     * Get function for queue type in the factory
     *
     * @return queue type in the factory
     */
    public String getQueueType() {
        return queueType
    }

    /**
     * Set function for the queue type in the factory
     *
     * @param queueType type of queue to store
     */
    public void setQueueType(String queueType) {
        this.queueType = queueType
    }

    /**
     * Type of runner to create in whoas
     */
    @JsonProperty
    @NotEmpty
    String runnerType

    /**
     * Get function for runner type in the factory
     *
     * @return runner type in the factory
     */
    public String getRunnerType() {
        return runnerType
    }

    /**
     * Set function for runner type in the factory
     *
     * @param runnerType type of the runner to store
     * @return
     */
    public setRunnerType(String runnerType) {
        this.runnerType = runnerType
    }

    /**
     * Allocate and return the queue based on stored queue type
     *
     * @return allocated queue
     */
    public AbstractHookQueue buildQueue() {
        return Class.forName(this.queueType).newInstance()
    }

    /**
     * Allocate and return runner based on stored runner type
     *
     * @param hookQueue queue to associate with allocated runner
     * @return
     */
    public AbstractHookRunner buildRunner(AbstractHookQueue hookQueue) {
        return Class.forName(this.runnerType).getConstructor(AbstractHookQueue.class).
                newInstance(hookQueue)
    }
}