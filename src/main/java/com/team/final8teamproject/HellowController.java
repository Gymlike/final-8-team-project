package com.team.final8teamproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HellowController {

    @GetMapping("/hello")
    public String hello() {
        return "testing Dockerfile...";
    }
}