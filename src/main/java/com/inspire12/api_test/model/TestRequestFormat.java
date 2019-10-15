package com.inspire12.api_test.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.inspire12.api_test.util.JsonUtils;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.Serializable;

@Getter
@Setter
public class TestRequestFormat {
    String name;
    String url;
    String type;
    ObjectNode requestFormat;
    ObjectNode responseFormat;

    @Override
    public String toString() {
        return "{" +
                "\"name\":\"" + name + "\",\n" +
                "\"url\":\"" + url + "\",\n" +
                "\"type\":\"" + type + "\",\n" +
                "\"requestFormat\":" + JsonUtils.prettyPrintJsonString(requestFormat) +",\n"+
                "\"responseFormat\":" + JsonUtils.prettyPrintJsonString(responseFormat) + "\n"+
                "}";
    }
}
