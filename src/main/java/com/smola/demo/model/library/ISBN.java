package com.smola.demo.model.library;

import javax.persistence.*;

@Entity
public class ISBN {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "isbn_id")
    private Long id;
    private String value;

    public ISBN() {
    }

    public String getValue() {
        return value;
    }

    public ISBN(String value) {

        this.value = value;
    }
}
