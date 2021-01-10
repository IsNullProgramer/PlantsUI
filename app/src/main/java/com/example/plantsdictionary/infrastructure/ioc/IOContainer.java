package com.example.plantsdictionary.infrastructure.ioc;

import java.util.HashMap;
import java.util.Map;

/**
 * HardCode контейнер. Singleton,
 * фабрика его вернёт,
 * если он созданный, будет возвращён созданный экземпляр
 */
public class IOContainer implements IContainer {
    public IOContainer() {
    }
    /**
     * Map для хранения реализации
     * Поставка всех данных в единичном экземпляре
     */
    private Map<Class, Object> memoryHardContainer = new HashMap<>();

    @Override
    public <T> T resolve(Class<T> type) {
        return (T) memoryHardContainer.get(type);
    }

    @Override
    public <I, T> void register(Class<I> i, T obj) {
        memoryHardContainer.put(i, obj);
    }
}
