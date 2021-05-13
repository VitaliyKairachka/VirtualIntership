package sber.kairachka.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
    public static final String pathToDBProperties = "src/main/resources/properties/database.properties";
    public static final String pathToFileProperties = "src/main/resources/properties/file.properties";
    FileInputStream fileInputStream;
    Properties properties = new Properties();

    public String getUrl() {
        try {
            fileInputStream = new FileInputStream(pathToDBProperties);
            properties.load(fileInputStream);
            return properties.getProperty("connection.url");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getFile() {
        try {
            fileInputStream = new FileInputStream(pathToFileProperties);
            properties.load(fileInputStream);
            return properties.getProperty("file");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}