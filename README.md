# entity-factory

entity factory입니다.
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

    // callback
    factory.create(repository::save);
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
