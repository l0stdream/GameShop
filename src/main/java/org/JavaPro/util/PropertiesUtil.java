package org.JavaPro.util;

import org.JavaPro.exception.PropertiesLoadingException;

import java.io.IOException;
import java.util.Properties;

public final class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private PropertiesUtil() {
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    private static void loadProperties() {
        try (var inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (inputStream == null) {
                throw new PropertiesLoadingException("File application.properties not found!");
            }
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new PropertiesLoadingException("Error loading application.properties file", e);
        }
    }
}