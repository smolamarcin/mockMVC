package com.smola.demo.model.library;

import javax.persistence.*;

@Entity
public class Tittle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tittle_id")
    private Long id;

    private String tittle;

    public Tittle() {
    }

    public String getTittle() {
        return tittle;
    }

    public Tittle(String tittle) {
        this.tittle = tittle;
    }
}
