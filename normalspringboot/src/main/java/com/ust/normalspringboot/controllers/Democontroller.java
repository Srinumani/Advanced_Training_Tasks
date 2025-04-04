package com.ust.normalspringboot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class Democontroller {

    @GetMapping
    public String getMethod(){
        return "its Working ..";
    }
}
