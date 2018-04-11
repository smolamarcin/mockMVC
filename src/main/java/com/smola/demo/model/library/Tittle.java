package com.smola.demo.model.library;

import javax.persistence.*;

@Entity
public class Tittle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tittle_id")
    private long id;

    private String tittle;

    public Tittle() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public Tittle(String tittle) {
        this.tittle = tittle;
    }
}
