package com.inspire12.api_test.controller;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.inspire12.api_test.Model.CompareResult;
import com.inspire12.api_test.Model.TestRequestFormat;
import com.inspire12.api_test.service.ApiCompareService;
import com.inspire12.api_test.service.ApiRunService;
import com.inspire12.api_test.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
public class ApiController {


    @Autowired
    ApiCompareService apiCompareService;

    @Autowired
    ApiRunService apiRunService;

    @Autowired
    RegisterService registerService;

    @PostMapping("")
    public boolean run(@RequestBody ObjectNode request){
        String url = request.get("url").asText();
        String requestType = request.get("type").asText();
        HttpHeaders headers = convertHeaders((ObjectNode)request.get("headers"));
        ObjectNode runResponse = apiRunService.run(url, requestType, headers);
        return apiCompareService.compare(runResponse, (ObjectNode) registerService.loadJson(url));
    }


    private HttpHeaders convertHeaders(ObjectNode objectNode){
        Iterator<String> fieldNames = objectNode.fieldNames();
        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);

        while (fieldNames.hasNext()){
            String fieldName = fieldNames.next();
            headers.set(fieldName, String.valueOf(objectNode.get(fieldName)));
        }
        return headers;
    }


//    @PostMapping("/add")
////    public ObjectNode addValue(TestRequestFormat requestFormat){
////
////
////        return
////    }

}
