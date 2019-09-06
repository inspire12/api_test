package com.inspire12.api_test.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;

@Service
public class ApiRunService {

    @Autowired
    RestTemplate restTemplate;


    public ObjectNode run(String api, String requestType, HttpHeaders headers) {
        ResponseEntity<ObjectNode> response;
        if (requestType.equals("get")) {
//            response = restTemplate.exchange(api, , ObjectNode.class, headers);
            response = restTemplate.exchange(api, HttpMethod.GET, new HttpEntity<>(headers), ObjectNode.class);
        } else {
            response = null;
        }

        return response.getBody();
    }

    protected HttpHeaders authHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }
}
