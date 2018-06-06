package com.github.jejugamja.entityfactory;

import com.github.jejugamja.entityfactory.person.Person;
import com.github.jejugamja.entityfactory.person.PersonFactory;
import com.github.jejugamja.entityfactory.person.PersonValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;

public class EntityFactoryTest {

    private PersonFactory factory;
    private PersonValidator personValidator;

    @Before
    public void setup() {
        factory = new PersonFactory();
        factory.setName("jejugamja");

        personValidator = new PersonValidator();
    }

    @Test
    public void createTest() {
        Person person = factory.create();
        Assert.assertThat("jejugamja", is(person.getName()));

        factory.create(p -> {
            Assert.assertThat("jejugamja", is(p.getName()));
//            repository.save(p);
        });
    }

    @Test(expected = RuntimeException.class)
    public void validateTest() {
        factory.validate(personValidator.existsNameValidate())
                .validate(personValidator.longNameValidator())
                .create();
    }

}
