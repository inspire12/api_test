package com.inspire12.api_test.service;


import com.fasterxml.jackson.databind.node.ObjectNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiCompareService {

    Logger logger = LoggerFactory.getLogger(ApiCompareService.class);

    public boolean compare(ObjectNode response, ObjectNode savedResponse){

        try {
            response.getNodeType().equals(savedResponse.getNodeType());
        }catch (Exception e){

            return false;
        }
        return true;
    }

}
