package com.inspire12.api_test.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;

@AllArgsConstructor
@Getter
@Setter
public class ComparatorRequest {
    String url;
    String name;
    @JsonProperty("request_type")
    String requestType;
    HttpHeaders headers;
    @JsonProperty("control_group_url")
    String controlGroupUrl;
}
