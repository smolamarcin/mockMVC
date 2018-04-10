package com.smola.demo.repository;

import com.smola.demo.model.Content;
import com.smola.demo.model.Greeting;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class HelloWorldRepository {
    private List<Greeting> greetings = new CopyOnWriteArrayList<>();
    private AtomicLong id = new AtomicLong(0);


    public List<Greeting> getAll() {
        return Collections.unmodifiableList(greetings);
    }

    public Greeting addGreeting(Greeting greeting) {
        Greeting greetingToAdd = new Greeting(id.getAndIncrement(), greeting.getContent());
        greetings.add(greetingToAdd);
        return greetingToAdd;
    }

    public void deleteAll() {
        id = new AtomicLong(0);
        greetings.clear();
    }

    public boolean deleteById(Long id) {
        Greeting toDelete = findById(id);
        return greetings.remove(toDelete);
    }

    private Greeting findById(Long id) {
        Greeting toReturn = null;
        for (Greeting greeting : greetings) {
            if (greeting.getId() == id) {
                toReturn = greeting;
            }
        }
        return toReturn;
    }


    public void updateById(Long id, Greeting greetingToUpdate) {
        Greeting greeting = findById(id);
        Content contentToUpdate = greetingToUpdate.getContent();
        greeting.setContent(contentToUpdate);
    }
}
