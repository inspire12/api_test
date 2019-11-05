package com.inspire12.api_test.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.inspire12.api_test.exception.DuplicatedRegisterException;
import com.inspire12.api_test.model.ComparatorJsonField;
import com.inspire12.api_test.model.ComparatorRequest;
import com.inspire12.api_test.model.TestRequestFormat;
import com.inspire12.api_test.service.ApiCompareService;
import com.inspire12.api_test.service.ApiRunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.Iterator;



@RestController
public class ApiController {


    @Autowired
    ApiCompareService apiCompareService;

    @Autowired
    ApiRunService apiRunService;

    @GetMapping("/instant/compare")
    public boolean runInstantComapre(@RequestBody ComparatorRequest request) throws Exception {
        String url = request.getUrl();
        String name = request.getName();
        String requestType = request.getRequestType();
        HttpHeaders headers = request.getHeaders();
        ObjectNode response = apiRunService.run(url, requestType, headers);
        JsonNode controlGroup = ComparatorJsonField.loadJsonFromFile(makeFilePath(name));
        return apiCompareService.compare(response, controlGroup);
    }




    @PostMapping("/register")
    public ObjectNode addValue(@RequestBody TestRequestFormat requestFormat) throws IOException {
        final String filePath = makeFilePath(requestFormat.getName());
        File file = new File(filePath);
        if (file.exists()){
            throw new DuplicatedRegisterException();
        }
        try (FileWriter output = new FileWriter(file)) {
            System.out.println(requestFormat.toString());
            output.write(requestFormat.toString());
        }
        return requestFormat.getResponseFormat();
    }

    private String makeFilePath(String path) {
        return "sample/" + path + ".json";
    }

//    private HttpHeaders convertHeaders(HttpHeaders objectNode) {
//        Iterator<String> fieldNames = objectNode.entrySet();
//        HttpHeaders headers = new HttpHeaders();
////        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        while (fieldNames.hasNext()) {
//            String fieldName = fieldNames.next();
//            headers.set(fieldName, String.valueOf(objectNode.get(fieldName)));
//        }
//        return headers;
//    }

}
