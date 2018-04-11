package com.smola.demo.model.library;

import javax.persistence.*;

@Entity
public class ISBN {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "isbn_id")
    private long id;
    private String value;

    public ISBN() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ISBN(String value) {

        this.value = value;
    }
}
