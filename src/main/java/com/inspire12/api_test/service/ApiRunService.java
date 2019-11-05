package com.inspire12.api_test.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.inspire12.api_test.model.ComparatorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class ApiRunService {

    private final RestTemplate restTemplate;

    @Autowired
    public ApiRunService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public ObjectNode run(ComparatorRequest useRequest) throws Exception {

        ResponseEntity<ObjectNode> response;
        if (useRequest.getRequestType().toLowerCase().equals("get")) {
            response = restTemplate.exchange(useRequest.getUrl(), HttpMethod.GET, new HttpEntity<>(useRequest.getHeaders()), ObjectNode.class);
        } else {
            throw new Exception();
        }

        return response.getBody();
    }

    public JsonNode runControlGroup(ComparatorRequest useRequest) throws Exception{
        ResponseEntity<ObjectNode> response;
        if (useRequest.getRequestType().toLowerCase().equals("get")) {
            response = restTemplate.exchange(useRequest.getControlGroupUrl(), HttpMethod.GET, new HttpEntity<>(useRequest.getHeaders()), ObjectNode.class);
        } else {
            throw new Exception();
        }
        return response.getBody();
    }


    protected HttpHeaders getAuthHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

}
