package com.inspire12.api_test.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

@Service
public class ComparatorJsonField {


    private ObjectMapper mapper;

    @Autowired
    public ComparatorJsonField(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    private boolean hasChildNode(JsonNode jsonNode) {
        return jsonNode.isContainerNode();

    }

    private boolean compareFieldTypeSelf(JsonNode aJsonNode, JsonNode bJsonNode) {
        boolean result = true;
        for (Iterator<String> it = aJsonNode.fieldNames(); it.hasNext(); ) {
            String a = it.next();
            System.out.println(a);
            System.out.println(aJsonNode.get(a).getNodeType());
            if (hasChildNode(aJsonNode)) {
                if (!result) {
                    return result;
                }
                if (aJsonNode.get(a).getNodeType() == JsonNodeType.ARRAY){
                    result = compareArrayFieldTypeSelf((ArrayNode) aJsonNode.get(a), (ArrayNode) bJsonNode.get(a));
                }else if(aJsonNode.get(a).getNodeType() == JsonNodeType.OBJECT) {
                    result = compareFieldTypeSelf(aJsonNode.get(a), bJsonNode.get(a));
                }
            }

            if (hasNotChildEqualField(a, aJsonNode, bJsonNode)) {
                System.out.println(a + aJsonNode + bJsonNode);
                return false;
            }
        }
        return result;
    }

    private boolean compareArrayFieldTypeSelf(ArrayNode aArray, ArrayNode bArray) {
        boolean result = true;
        for (int i = 0; i < aArray.size(); i++) {
            System.out.println(aArray.get(i).getNodeType());
            if (!result) {
                return result;
            }
            result = compareFieldTypeSelf(aArray.get(i), bArray.get(i));
            if (hasNotEqualField(aArray.get(i), bArray.get(i))) {
                return false;
            }
        }
        return result;
    }

    public boolean compareFieldType(JsonNode aJsonNode, JsonNode bJsonNode) {


        return compareFieldTypeSelf(aJsonNode, bJsonNode);
        // 대칭 확인 필요
//        for (Iterator<String> it = bJsonNode.fieldNames(); it.hasNext(); ) {
//            String b = it.next();
//            if (hasNotEqualField(b, aJsonNode, bJsonNode)) {
//                return false;
//            }
//        }
//        return true;
    }

    //    public static JsonNode loadJsonFromFile(String fileName) throws ParseException {
//        JSONParser parser = new JSONParser(fileName);
//        return (JsonNode)parser.parse();
//    }
    public JsonNode loadJsonFromFile(String fileName) {

        JsonNode rootNode = null;
        try {
            InputStream exampleInput =
                    ComparatorJsonField.class.getClassLoader().getResourceAsStream(fileName);
            rootNode = mapper.readValue(exampleInput, JsonNode.class);
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rootNode;
    }

    private boolean hasNotChildEqualField(String a, JsonNode aJsonNode, JsonNode bJsonNode) {
        if (aJsonNode.has(a) && bJsonNode.has(a)) {
            return hasNotEqualField(aJsonNode.get(a), bJsonNode.get(a));
        }
        return true;
    }

    private static boolean hasNotEqualField(JsonNode aJsonNode, JsonNode bJsonNode) {
        return !aJsonNode.getNodeType().equals(bJsonNode.getNodeType());
    }
}
