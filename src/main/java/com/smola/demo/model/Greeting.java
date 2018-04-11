package com.smola.demo.model;

import javax.persistence.*;

@Entity(name = "Greeting")
@Table(name = "greeting")
public class Greeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "content_id")
    private Content content;

    public Greeting() {
    }

    public void setId(long id) {
        this.id = id;
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
