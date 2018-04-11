package com.smola.demo.model.library;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Author {
    private long id;
    private String name;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }
}
