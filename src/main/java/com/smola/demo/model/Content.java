package com.smola.demo.model;


public class Content  {
    private String text;

    public Content() {
    }

    public Content(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}
