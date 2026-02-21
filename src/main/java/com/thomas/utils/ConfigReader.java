package com.thomas.utils;

import java.io.FileInputStream;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigReader {
    private final Map<String, String> propertiesMap = new HashMap<>();

    public ConfigReader(String filePath) {
        this.loadProperties(filePath);
    }

    private void loadProperties(String filePath) {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            properties.load(fileInputStream);
            for (String key : properties.stringPropertyNames())
                propertiesMap.put(key, properties.getProperty(key));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file : " + filePath, e);
        }
    }

    public String getValue(String key) {
        return propertiesMap.entrySet()
                .stream()
                .filter(entry->entry.getKey().equals(key))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(()->new RuntimeException(String.format("No Key named : %s found", key)));
    }

    public String getKey(String value) {
        return propertiesMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(value))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("No Value named : %s found", value)));
    }
}
