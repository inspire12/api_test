package com.inspire12.api_test.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.inspire12.api_test.controller.TempTestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class RegisterService {

    @Autowired
    ObjectMapper mapper;


    public JsonNode loadJson(String fileName){


        JsonNode test = getExampleRoot();

        return test;
    }


    public ResponseEntity<ObjectNode> registerApi(String url, ObjectNode requestBody){

        return (ResponseEntity<ObjectNode>) ResponseEntity.ok();
    }


    JsonNode getExampleRoot() {
        InputStream exampleInput =
                TempTestController.class.getClassLoader()
                        .getResourceAsStream("api_setting/push_setting_response.json");

        JsonNode rootNode = null;
        try {
            rootNode = mapper.readTree(exampleInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rootNode;
    }
}

