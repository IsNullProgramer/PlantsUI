package com.example.plantsdictionary.infrastructure.threads;

/**
 * Интерфейс для анонимного класса, будет реализован в new
 * @param <T>
 */
public interface ThreadResult<T> {
    T get();
}
