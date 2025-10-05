package com.example.academy.concurrency.blockingQueue;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class BlockingQueueRunner {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executor = Executors.newFixedThreadPool(4);
        ExecutorService taskExecutor = Executors.newFixedThreadPool(2);
        BlockingQueue blockingQueue = new BlockingQueue(5);

        System.out.println("Submit workers using invokeAll()");
        List<Future<String>> futures = executor.invokeAll(
                Arrays.asList(
                        new Producer("producer1", blockingQueue, 5),
                        new Producer("producer2", blockingQueue, 5),
                        new Consumer("consumer1", blockingQueue, 5, taskExecutor),
                        new Consumer("consumer2", blockingQueue, 5, taskExecutor)
                ));

        System.out.println("Exited invokeAll()");
        for (Future<String> future : futures) {
            System.out.println("Result: " + future.get());
        }

        System.out.println("Final queue size: " + blockingQueue.size());
        executor.shutdown();
        taskExecutor.shutdown();
        executor.awaitTermination(10L, TimeUnit.SECONDS);
    }
}

//  Практическая задача - Concurrency - блокирующая очередь
//  Предположим, у вас есть пул потоков, и вы хотите реализовать блокирующую очередь
//  для передачи задач между потоками.
//  Создайте класс BlockingQueue, который будет обеспечивать безопасное добавление
//  и извлечение элементов между производителями и потребителями в контексте пула потоков.
//
//  Класс BlockingQueue должен содержать методы enqueue()
//  для добавления элемента в очередь и dequeue() для извлечения элемента.
//  Если очередь пуста, dequeue() должен блокировать вызывающий поток до появления нового элемента.
//
//  очередь должна иметь фиксированный размер.
//
//  Используйте механизмы wait() и notify() для координации между производителями и потребителями.
//  Реализуйте метод size(), который возвращает текущий размер очереди.