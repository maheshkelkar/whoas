package com.github.lookout.whoas

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.NotEmpty;

public class WhoasFactory {
    @JsonProperty
    @NotEmpty
    String queueType

    public getQueueType() {
        return queueType
    }

    public setQueueType(String queueType) {
        this.queueType = queueType
    }

    @JsonProperty
    @NotEmpty
    String runnerType

    public getRunnerType() {
        return runnerType
    }

    public setRunnerType(String runnerType) {
        this.runnerType = runnerType
    }

    public AbstractHookQueue buildQueue() {
        if(queueType == "InMemoryQueue") {
            return new InMemoryQueue()
        }
        return null
    }

    public AbstractHookRunner buildRunner(AbstractHookQueue hookQueue) {
        if(runnerType == "SequentialHookRunner") {
            return new SequentialHookRunner(hookQueue)
        }
        return null
    }
}