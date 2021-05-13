package sber.kairachka.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
    public static final String PATH_TO_PROPERTIES = "src/main/resources/properties/database.properties";
    FileInputStream fileInputStream;
    Properties properties = new Properties();

    public String getUrl() {
        try {
            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            properties.load(fileInputStream);
            return properties.getProperty("connection.url");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}