package com.thomas.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;


public class TestDataReader {
    private final String filePath;

    public TestDataReader(String filePath) {
        this.filePath = (System.getProperty("user.dir")+ filePath);
    }

    public List<HashMap<String, Object>> getJsonDataToMap() {
        try {
            File file = new File(filePath);
            String jsonContent = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(
                    jsonContent, new TypeReference<List<HashMap<String, Object>>>() {
                    });

        } catch (FileNotFoundException e) {
            throw new RuntimeException("There is no such File : " + filePath, e);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load File : " + filePath, e);
        }
    }
}
