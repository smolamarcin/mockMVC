package com.smola.demo.model;


import org.springframework.transaction.annotation.Transactional;

public class Greeting {
    private long id;
    private Content content;

    public Greeting() {
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Greeting(long id, Content content) {
        this.id = id;
        this.content = content;
    }

    public Greeting(Content content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public Content getContent() {
        return content;
    }
}
