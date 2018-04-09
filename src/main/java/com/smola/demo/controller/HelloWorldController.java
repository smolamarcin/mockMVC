package com.smola.demo.controller;

import com.smola.demo.model.Content;
import com.smola.demo.model.Greeting;
import com.smola.demo.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class HelloWorldController {
    @Autowired
    private HelloWorldService helloWorldService;

//    @PostMapping("/hello-world")
//    @ResponseBody
//    public ResponseEntity<Greeting> sayHello(@PathVariable(name = "name", required = false, defaultValue = "World!") String name) {
//        helloWorldService.addGreeting(name);
//
//    }

    @GetMapping("/hello-world/all")
    @ResponseBody
    public List<Greeting> getAll() {
        List<Greeting> greetings = helloWorldService.getAll();
        return greetings;
    }
}
