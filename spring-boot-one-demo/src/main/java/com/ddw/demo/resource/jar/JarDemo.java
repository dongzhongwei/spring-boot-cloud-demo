package com.ddw.demo.resource.jar;

import org.springframework.context.ApplicationContext;

import java.net.URL;

public class JarDemo {

    public static void main(String[] args) {
        final ClassLoader classLoader = ApplicationContext.class.getClassLoader();
        URL url = classLoader.getResource("META-INF/license.txt");
        System.out.println(url);
    }
}
