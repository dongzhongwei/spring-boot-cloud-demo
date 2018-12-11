package com.ddw.demo.service;

import com.jarvis.cache.annotation.Cache;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class HelloWorldService {

    @Cache(expire = 10, key = "chche-test")
    public String cache(){
        System.out.println("aaaa");
        return "test-chche";
    }
}
