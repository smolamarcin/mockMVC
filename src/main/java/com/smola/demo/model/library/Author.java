package com.smola.demo.model.library;

import javax.persistence.*;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private long id;
    private String name;

    public Author() {
    }

    public String getName() {
        return name;
    }

    public Author(String name) {
        this.name = name;
    }
}
