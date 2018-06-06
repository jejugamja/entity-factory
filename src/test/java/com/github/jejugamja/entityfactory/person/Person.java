package com.github.jejugamja.entityfactory.person;

import lombok.Getter;
import lombok.NoArgsConstructor;

// Entity
@NoArgsConstructor
@Getter
public class Person {

    private Long id;
    private String name;

    public Person(String name) {
        this.name = name;
    }
}
