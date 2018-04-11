package com.smola.demo.controller;

import com.smola.demo.model.Greeting;
import com.smola.demo.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/greetings")
public class HelloWorldController {
    @Autowired
    private HelloWorldService helloWorldService;

    @GetMapping
    @ResponseBody
    public Iterable<Greeting> getAll() {
        Iterable<Greeting> greetings = helloWorldService.getAll();
        return greetings;
    }

    @PostMapping()
    public ResponseEntity<Greeting> addGreeting(@RequestBody Greeting greeting) {
        helloWorldService.addGreeting(greeting);
        return new ResponseEntity<>(greeting, HttpStatus.CREATED);
    }



}
