package com.github.jejugamja.entityfactory;

import java.util.function.Consumer;

public interface EntityFactory<E> {

    E create();

    default E create(Consumer<E> complete) {
        E t =  create();
        complete.accept(t);
        return t;
    }

    default <V extends EntityFactory<E>> V validate(ValidateFunction<V> validateFunction) {
        validateFunction.validate((V) this);
        return (V)this;
    }
}
