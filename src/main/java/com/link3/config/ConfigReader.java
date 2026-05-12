package com.link3.config;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    static Properties properties;

    static {

        try {

            properties =
                    new Properties();

            FileInputStream file =

                    new FileInputStream(
                            "src/main/resources/config.properties"
                    );

            properties.load(file);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static String getProperty(
            String key
    ) {

        return properties.getProperty(key);
    }
}