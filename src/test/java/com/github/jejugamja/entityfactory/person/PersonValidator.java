package com.github.jejugamja.entityfactory.person;

import com.github.jejugamja.entityfactory.ValidateFunction;

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
