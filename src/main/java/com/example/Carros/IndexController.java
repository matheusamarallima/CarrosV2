package com.example.Carros;


import org.springframework.web.bind.annotation.GetMapping;

public class IndexController {
    @GetMapping()
    public String get() {
        return "API dos Carros - Heroku Git";
    }
//
//    @GetMapping("/userInfo")
//    public UserDetails userInfo(@AuthenticationPrincipal UserDetails user) {
//        return user;
//    }
}

