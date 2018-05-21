package com.ddw.demo.resource.file;

import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

public class FileDemo {

    public static void main(String[] args) throws Exception {
        File file = new File("spring-boot-one-demo/src/main/resources/application.yml");
        URL fileURL = file.toURI().toURL();

//        URL httpsURL = new URL("https://www.baidu.com");  //htts 协议
//        URL ftpURL = new URL("ftp://ftp.baidu.com");     //ftp 协议
//        URL jarURL = new URL("jar://jar.baidu.com");     //jar 协议
//        URL dubboURL = new URL("dubbo://");              //dubbo 协议
//        URL classpathURL = new URL("classpath:/");       // classpath

        // file URLStreamHandler   = sun.net.www.protocol.file.Handler
        // http URLStreamHandler  =  sun.net.www.protocol.http.Handler
        // https URLStreamHandler  = sun.net.www.protocol.https.Handler
        // jar URLStreamHandler  = sun.net.www.protocol.jar.Handler
        // ftp URLStreamHandler  = sun.net.www.protocol.ftp.Handler
        // 模式 URLStreamHandler =  sun.net.www.protocol.${protocol}.Handler

        final InputStream inputStream = fileURL.openStream();

        String content = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));

        System.out.println(content);

        // URL -> URLConnection -> URLStreamHandler -> InputStream

    }
}
