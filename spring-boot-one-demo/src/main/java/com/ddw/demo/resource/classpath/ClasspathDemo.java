package com.ddw.demo.resource.classpath;

import org.springframework.util.StreamUtils;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

public class ClasspathDemo {

    public static void main(String[] args) throws Exception {
        URL url = new URL("classpath:/application.properties");
        final InputStream inputStream = url.openStream();
        final String content = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));
        System.out.println(content);
    }
}
