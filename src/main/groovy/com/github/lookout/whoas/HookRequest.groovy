package com.github.lookout.whoas

import com.fasterxml.jackson.annotation.JsonProperty
import org.joda.time.DateTime

class HookRequest {
    private Long retries
    private String url
    private String postData
    private DateTime deliverAfter


    /** Constructor for Jackson */
    HookRequest() { }

    /**
     * Default constructor for creating a simple HookRequest with a URL and the
     * POST data to be delivered to that URL
     */
    HookRequest(String hookUrl, String hookData) {
        this.retries = 0
        this.url = hookUrl
        this.postData = hookData
    }

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
