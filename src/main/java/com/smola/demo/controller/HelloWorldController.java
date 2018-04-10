package com.smola.demo.controller;

import com.smola.demo.model.Greeting;
import com.smola.demo.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/greetings")
public class HelloWorldController {
    @Autowired
    private HelloWorldService helloWorldService;

    @GetMapping
    @ResponseBody
    public List<Greeting> getAll() {
        List<Greeting> greetings = helloWorldService.getAll();
        return greetings;
    }

    @PostMapping()
    public ResponseEntity<Greeting> addGreeting(@RequestBody Greeting greeting) {
        helloWorldService.addGreeting(greeting);
        return new ResponseEntity<>(greeting, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSingleGreeting(@PathVariable Long id) {
        return helloWorldService.deleteById(id);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGreeting(@PathVariable Long id,
                                            @RequestBody Greeting greetingDetails) {
        return helloWorldService.update(id, greetingDetails);

    }

}
