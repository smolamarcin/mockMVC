package com.smola.demo.service;

import com.smola.demo.model.Greeting;
import com.smola.demo.repository.HelloWorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

    @Autowired
    private HelloWorldRepository helloWorldRepository;

    public Iterable<Greeting> getAll() {
        return helloWorldRepository.findAll();
    }


    public Greeting addGreeting(Greeting greeting) {
        return helloWorldRepository.save(greeting);
    }


}
