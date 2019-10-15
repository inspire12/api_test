package com.inspire12.api_test.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.inspire12.api_test.model.CompareJsonField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiCompareService {

    Logger logger = LoggerFactory.getLogger(ApiCompareService.class);

    public boolean compare(JsonNode response, JsonNode savedResponse) {

        return CompareJsonField.isFieldTypeCompare(response, savedResponse);
    }


}


