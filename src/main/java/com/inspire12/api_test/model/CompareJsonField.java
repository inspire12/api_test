package com.inspire12.api_test.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Iterator;

public class CompareJsonField {
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

    private static boolean hasNotEqualField(String a, JsonNode aJsonNode, JsonNode bJsonNode ) {
        if (aJsonNode.has(a) && bJsonNode.has(a)) {
            return !aJsonNode.get(a).getNodeType().equals(bJsonNode.get(a).getNodeType());
        }
        return true;
    }


}
