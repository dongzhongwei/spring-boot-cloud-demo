package com.ddw.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("helloWorld")
    public String helloWorld(@RequestParam String name) throws Exception {
        return name + "Hello,World";
    }
}
