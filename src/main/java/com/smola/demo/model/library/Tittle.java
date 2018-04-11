package com.smola.demo.model.library;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Tittle {
    private long id;

    private String tittle;

    public Tittle() {
    }

    public Tittle(String tittle) {
        this.tittle = tittle;
    }
}
