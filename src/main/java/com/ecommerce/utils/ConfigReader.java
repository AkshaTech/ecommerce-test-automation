package com.ecommerce.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    // Properties is a built-in Java class for reading .properties files
    private static Properties properties = new Properties();

    // Static block runs ONCE when the class is first loaded
    static {
        try {
            // Point to your config.properties file
            FileInputStream file = new FileInputStream(
                "src/main/resources/config.properties"
            );
            properties.load(file);
            file.close();
        } catch (IOException e) {
            // If file not found, stop everything — tests can't run without config
            throw new RuntimeException("config.properties file not found! " + e.getMessage());
        }
    }

    // Get the base URL
    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    // Get the browser name
    public static String getBrowser() {
        return properties.getProperty("browser");
    }

    // Get implicit wait time as a number (not text)
    public static int getImplicitWait() {
        return Integer.parseInt(properties.getProperty("implicit.wait"));
    }

    // Get page load timeout as a number
    public static int getPageLoadTimeout() {
        return Integer.parseInt(properties.getProperty("page.load.timeout"));
    }

    public static String getValidEmail() {
    return properties.getProperty("valid.email");
    }

    public static String getValidPassword() {
        return properties.getProperty("valid.password");
    }

    public static String getInvalidEmail() {
        return properties.getProperty("invalid.email");
    }

    public static String getInvalidPassword() {
        return properties.getProperty("invalid.password");
    }
}