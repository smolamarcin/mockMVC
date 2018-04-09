package com.smola.demo.repository;

import com.smola.demo.model.Content;
import com.smola.demo.model.Greeting;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class HelloWorldRepository {
    private List<Greeting> greetings = new ArrayList<>();

    public List<Greeting> getAll() {
        Content content = new Content("a");
        Greeting greeting = new Greeting(1,content);
        greetings.add(greeting);
        return new ArrayList<>(greetings);
    }

    void addGreeting(Greeting greeting){
        greetings.add(greeting);
    }

}
