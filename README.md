# entity-factory

간단한 entity factory입니다.
create후처리 및 validate를 할 수 있습니다.

### Example

**Factory**

```java
public class PersonFactory implements EntityFactory<Person> {

    private String name;

    @Override
    public Person create() {
        return new Person(name);
    }
} 
```

**create**

```java
@Test
public void createTest() {
    // 생성된 entity를 직접 사용
    Person person = factory.create();
    Assert.assertThat("jejugamja", is(person.getName()));

    // 또는 callback 이용
    factory.create(repository::save);
    factory.create(p -> {
        repository.save(p);
        // event 생성 등 후처리
    });
}
```

**Validate Service**

```java
@Service
public class PersonValidator {

    public ValidateFunction<PersonFactory> existsNameValidate() {
        return b -> {
            if ("jejugamja".equals(b.getName())) {
                throw new RuntimeException(b.getName() + "은 이미 사용중인 이름입니다.");
            }
        };
    }

    public ValidateFunction<PersonFactory> longNameValidator() {
        return b -> {
            if (b.getName().length() > 100) {
                throw new RuntimeException("이름은 100자 미만이어야 합니다.");
            }
        };
    }
}
```

**Validate 후 create**
```java
@Test
public void validateTest() {
    factory.validate(personValidator.existsNameValidate())
            .validate(personValidator.longNameValidator())
            .create();
}
```


