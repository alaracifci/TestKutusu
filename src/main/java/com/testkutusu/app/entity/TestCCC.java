package com.testkutusu.app.entity;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestCCC {

    @GetMapping("/hello")
    public String hello(){
        return "spring boot çalışıyor";
    }
}
