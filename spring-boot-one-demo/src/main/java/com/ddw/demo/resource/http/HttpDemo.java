package com.ddw.demo.resource.http;

import org.springframework.util.StreamUtils;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

public class HttpDemo {
    public static void main(String[] args) throws Exception {

        URL url = new URL("http://www.baidu.com");
        final InputStream inputStream = url.openStream();
        final String content = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));
        System.out.println(content);
    }
}
