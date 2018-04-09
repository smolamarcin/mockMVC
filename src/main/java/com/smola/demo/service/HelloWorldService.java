package com.smola.demo.service;

import com.smola.demo.model.Greeting;
import com.smola.demo.repository.HelloWorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelloWorldService {

    @Autowired
    private HelloWorldRepository helloWorldRepository;

    public List<Greeting> getAll() {
        return helloWorldRepository.getAll();
    }
}
