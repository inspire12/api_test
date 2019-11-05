package com.inspire12.api_test.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class ComparatorJsonField {
    private static ObjectMapper mapper;

    private ComparatorJsonField() {
        mapper = new ObjectMapper();
    }


    public static boolean isFieldTypeCompare(JsonNode aJsonNode, JsonNode bJsonNode) {
        for (Iterator<String> it = aJsonNode.fieldNames(); it.hasNext(); ) {
            String a = it.next();
            if (hasNotEqualField(a, aJsonNode, bJsonNode)){
                return false;
            }
        }
        // 대칭 확인 필요
        for (Iterator<String> it = bJsonNode.fieldNames(); it.hasNext(); ) {
            String b = it.next();
            if (hasNotEqualField(b, aJsonNode, bJsonNode)){
                return false;
            }
        }
        return true;
    }

//    public static JsonNode loadJsonFromFile(String fileName) throws ParseException {
//        JSONParser parser = new JSONParser(fileName);
//        return (JsonNode)parser.parse();
//    }
    public static JsonNode loadJsonFromFile(String fileName) {
//        InputStream exampleInput =
//                ComparatorJsonField.class.getClassLoader().getResourceAsStream(fileName);

        JsonNode rootNode = null;
        try {
            File file = new File(fileName);
            if (file.exists()) {
                rootNode = mapper.readTree(file);
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rootNode;
    }

    private static boolean hasNotEqualField(String a, JsonNode aJsonNode, JsonNode bJsonNode ) {
        if (aJsonNode.has(a) && bJsonNode.has(a)) {
            return !aJsonNode.get(a).getNodeType().equals(bJsonNode.get(a).getNodeType());
        }
        return true;
    }
}
