package com.smola.demo.model.library;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ISBN {
    private long id;
    private String value;

    public ISBN() {
    }

    public ISBN(String value) {

        this.value = value;
    }
}
