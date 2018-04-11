package com.smola.demo.model;


import javax.persistence.*;

@Entity(name = "Content")
@Table(name = "content")
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "content_id")
    private long id;
    private String text;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Content() {
    }


    public void setText(String text) {
        this.text = text;
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
