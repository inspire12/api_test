package com.inspire12.api_test.service;


import com.fasterxml.jackson.databind.JsonNode;

import com.inspire12.api_test.model.ComparatorJsonField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ApiCompareService {

    Logger logger = LoggerFactory.getLogger(ApiCompareService.class);

    public boolean compare(JsonNode response, JsonNode savedResponse) {

        return ComparatorJsonField.isFieldTypeCompare(response, savedResponse);
    }


}


