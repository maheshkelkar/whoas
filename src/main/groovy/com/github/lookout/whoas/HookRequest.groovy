package com.github.lookout.whoas

import com.fasterxml.jackson.annotation.JsonProperty
import org.joda.time.DateTime

class HookRequest {
    private Long retries
    private String url
    private String postData
    private DateTime deliverAfter


    /* Constructor for Jackson */
    HookRequest() { }

    @JsonProperty
    Long getRetries() {
        return this.retries
    }

    @JsonProperty
    String getUrl() {
        return this.url
    }

    @JsonProperty
    String getPostData() {
        return this.postData
    }

    @JsonProperty
    String getDeliverAfter() {
        return this.deliverAfter.toString()
    }
}
