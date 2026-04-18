package com.alpr.project.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class TestController {


    @GetMapping("/test")
    public String test(){
        return "API works";
    }

    @GetMapping("/api/secure")
    public String secure(Authentication authentication) {
        return "Giriş Yapan Kullanıcı: " + authentication.getName();
    }
    
    
}