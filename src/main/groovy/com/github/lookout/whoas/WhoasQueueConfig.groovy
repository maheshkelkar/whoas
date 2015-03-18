package com.github.lookout.whoas

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *  Whoas Queue Configuration
 */
public class WhoasQueueConfig {

    /**
     *  Type of queue (full class name) to create in whoas
     *
     *  Default queue in whoas is InMemoryQueue
     */
    @JsonProperty
    String type = "com.github.lookout.whoas.InMemoryQueue"

    /**
     *  key to idenitfy the distributed queue
     *
     *  Default key is "queue"
     */
    @JsonProperty
    String key = "queue"

    /**
     *  Hostname of the distributed queue server
     *
     *  Default hostname is localhost
     */
    @JsonProperty
    String hostname = "localhost"

    /**
     *  Port number of the distributed queue server
     *
     *  Default port is 6379 (i.e. redis)
     */
    @JsonProperty
    Integer port = 6379

}