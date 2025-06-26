package org.cooee.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class JsonDataReader {

    // ✅ This supports login tests expecting: [{ "url": "...", "username": "...", "password": "..." }]
    public static List<HashMap<String, String>> getJsonData(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        List<HashMap<String, Object>> rawData = objectMapper.readValue(
            new File(filePath),
            new TypeReference<List<HashMap<String, Object>>>() {}
        );

        // Convert each entry: flatten array values like "plans" into string or remove them
        List<HashMap<String, String>> cleanedData = new ArrayList<>();

        for (HashMap<String, Object> entry : rawData) {
            HashMap<String, String> simpleMap = new HashMap<>();
            for (Map.Entry<String, Object> e : entry.entrySet()) {
                if (e.getValue() instanceof String) {
                    simpleMap.put(e.getKey(), (String) e.getValue());
                }
                // Optional: skip or log non-string fields like array (e.g., "plans")
            }
            cleanedData.add(simpleMap);
        }

        return cleanedData;
    }

    // ✅ Optional: Use this in plan-based test to fetch just the "plans" list
    public static List<String> getPlanAmounts(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> testData = objectMapper.readValue(
            new File(filePath),
            new TypeReference<List<Map<String, Object>>>() {}
        );

        return (List<String>) testData.get(0).get("plans");
    }
    public static List<HashMap<String, Object>> getObjectMapData(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), new TypeReference<List<HashMap<String, Object>>>() {});
    }

}
