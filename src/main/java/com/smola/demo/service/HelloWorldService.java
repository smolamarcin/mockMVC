package com.smola.demo.service;

import com.smola.demo.model.Greeting;
import com.smola.demo.repository.HelloWorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelloWorldService {

    @Autowired
    private HelloWorldRepository helloWorldRepository;

    public List<Greeting> getAll() {
        return helloWorldRepository.getAll();
    }


    public Greeting addGreeting(Greeting greeting) {
        return helloWorldRepository.addGreeting(greeting);
    }

    public ResponseEntity deleteById(Long id) {
        ResponseEntity responseEntity = ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("This ID does not exist!");
        if (helloWorldRepository.deleteById(id)) {
            responseEntity = ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Successfully deleted! Id: " + id);
        }
        return responseEntity;

    }

    public ResponseEntity<?> update(Long id, Greeting greetingDetails) {
        helloWorldRepository.updateById(id, greetingDetails);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
