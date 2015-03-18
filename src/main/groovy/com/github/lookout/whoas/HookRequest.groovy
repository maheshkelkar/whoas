package com.github.lookout.whoas

import com.fasterxml.jackson.annotation.JsonProperty
import org.joda.time.DateTime


class HookRequest {

    @JsonProperty
    private Long id

    @JsonProperty
    private Long retries

    @JsonProperty
    private String url

    @JsonProperty
    private String postData

    @JsonProperty
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
}
