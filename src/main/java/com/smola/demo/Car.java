package com.smola.demo;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity

@Getter
@Setter

@NoArgsConstructor

@ToString
@EqualsAndHashCode
public class Car {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
