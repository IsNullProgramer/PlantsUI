package com.example.plantsdictionary.infrastructure.threads;

/**
 * Класс для синхронного выполнения кода + получения результата
 * @param <T>
 */
public class Future<T> extends Thread {
    /**
     * Код делегата
     */
    ThreadResult<T> threadResult;
    /**
     * Результат
     */
    T result;
    public Future(ThreadResult<T> threadResult) {
        this.threadResult = threadResult;
        //Старт нового потока
        this.start();
    }

    @Override
    public void run() {
        //Выполнение в новом потоке
        super.run();
        result = threadResult.get();
    }

    /**
     * Дождемся завершения и вернем результат
     * Получение реузльтата из другого потока
     * @return
     */
    public T get() {
        try {
            this.join();
            return result;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
