package com.dew.securityapp.controller;

import com.dew.securityapp.advice.TrackExecutionTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {


    @GetMapping("/welcome")
    @TrackExecutionTime
    public String welcome() {
        return "Welcome this endpoint is secure";
    }
}
