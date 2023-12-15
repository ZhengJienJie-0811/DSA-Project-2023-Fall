package com.example.demo;

import java.util.Collections;

import org.springframework.web.bind.annotation.*;

@RestController
public class AdditionController {

    @GetMapping("/add")
    public String addNumbers(@RequestParam int num1, @RequestParam int num2) {
        int sum = num1 + num2;
        return Collections.singletonMap("result", sum);
        // return "{\"result\": \"" + sum + "\"}";
    }
}