package com.smola.demo.model;

public class Greeting {
    private final long id;
    private final Content content;

    public Greeting(long id, Content content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public Content getContent() {
        return content;
    }
}
