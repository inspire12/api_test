package com.inspire12.api_test.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.inspire12.api_test.exception.DuplicatedRegisterException;
import com.inspire12.api_test.model.CompareJsonField;
import com.inspire12.api_test.model.TestRequestFormat;
import com.inspire12.api_test.service.ApiCompareService;
import com.inspire12.api_test.service.ApiRunService;
import com.inspire12.api_test.util.JsonUtils;
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

    @Autowired
    ApiRegisterService apiRegisterService;

    @PostMapping("/")
    public boolean run(@RequestBody ObjectNode request) throws Exception {
        String url = request.get("url").asText();
        String requestType = request.get("type").asText();
        HttpHeaders headers = convertHeaders((ObjectNode) request.get("headers"));
        ObjectNode runResponse = apiRunService.run(url, requestType, headers);
        return apiCompareService.compare(runResponse, (ObjectNode) apiRegisterService.loadJson(url));
    }


    private HttpHeaders convertHeaders(ObjectNode objectNode) {
        Iterator<String> fieldNames = objectNode.fieldNames();
        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);

        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            headers.set(fieldName, String.valueOf(objectNode.get(fieldName)));
        }
        return headers;
    }


    @PostMapping("/add")
    public ObjectNode addValue(@RequestBody TestRequestFormat requestFormat) throws IOException {
        final String filePath = makeFilePath(requestFormat.getName() + ".json");
        File file = new File(filePath);

        try (FileWriter output = new FileWriter(file)) {
            System.out.println(requestFormat.toString());
            output.write(requestFormat.toString());
        }
        return requestFormat.getResponseFormat();
    }

    private String makeFilePath(String path) {
        return "sample/" + path;
    }
}
