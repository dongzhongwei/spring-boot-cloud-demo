package com.ddw.demo.resource.file;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class propertiesDemo {

    public static void main(String[] args) throws IOException {
        final ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        final InputStream inputStream = contextClassLoader.getResourceAsStream("application.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        final String appName = properties.getProperty("spring.application.name");
        System.out.println(appName);

    }
}
