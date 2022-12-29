package br.com.dictaapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class helloWorldController {
    
    @GetMapping
    public String helo() {
        return "Hello World Dicta API";
    }
}
