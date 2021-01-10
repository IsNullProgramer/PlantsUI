package com.example.plantsdictionary.infrastructure.ioc;

/**
 * Самописный контейнер.
 */
public interface IContainer {
    /**
     * Получить сущность
     * @param type
     * @param <T>
     * @return
     */
    <T> T resolve(Class<T> type);

    /**
     * Зарегистрировать класс
     * @param i
     * @param obj
     * @param <I>
     * @param <T>
     */
    <I, T> void register(Class<I> i, T obj);
}
