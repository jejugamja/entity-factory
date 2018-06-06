package com.github.jejugamja.entityfactory.person;

import com.github.jejugamja.entityfactory.EntityFactory;
import lombok.Data;

@Data
public class PersonFactory implements EntityFactory<Person> {

    private String name;

    @Override
    public Person create() {
        return new Person(name);
    }
}
