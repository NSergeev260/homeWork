package com.example.academy.concurrency.blockingQueue;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

public class Consumer implements Callable<String> {
    private final String name;
    private final BlockingQueue blockingQueue;
    private final int taskCount;
    private final ExecutorService taskExecutor;

    public Consumer(String name, BlockingQueue blockingQueue, int taskCount,
                    ExecutorService taskExecutor) {
        this.name = name;
        this.blockingQueue = blockingQueue;
        this.taskCount = taskCount;
        this.taskExecutor = taskExecutor;
    }

    public String call() throws Exception {
        for (int i = 0; i < taskCount; i++) {
            Runnable task = blockingQueue.dequeue();
            if (task != null) {
                taskExecutor.execute(task);
            }
            Thread.sleep(100);
        }
        return name + " completed " + taskCount + " tasks";
    }
}