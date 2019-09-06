package com.inspire12.api_test.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.inspire12.api_test.service.ApiCompareService;
import com.inspire12.api_test.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempTestController {

    @Autowired
    ApiCompareService apiCompareService;

    @Autowired
    RegisterService registerService;

    @GetMapping(value = "/test")
    public ObjectNode checkApi(){
        ObjectMapper objectMapper = new ObjectMapper();
        final ObjectNode test = objectMapper.createObjectNode();
        test.put("test", "test");
        ObjectNode t = obj(test);
        JsonParser jsonParser = t.traverse();

        JsonNode expectedResponse = registerService.loadJson("");

        try{

        }catch (Exception e){
            return objectMapper.createObjectNode();
        }

        return test;

//        String a = new String("vasdf");
//        a.getClass().getSimpleName();
    }

    private ObjectNode obj(ObjectNode t ){
//        ObjectNode t = test.deepCopy();
        t.put("v","Vv");
        return t;
    }



}
