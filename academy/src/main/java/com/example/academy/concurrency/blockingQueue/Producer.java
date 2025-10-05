package com.example.academy.concurrency.blockingQueue;

import java.util.concurrent.Callable;

public class Producer implements Callable<String> {
    private final String name;
    private final BlockingQueue blockingQueue;
    private final int taskCount;

    public Producer(String name, BlockingQueue blockingQueue, int taskCount) {
        this.name = name;
        this.blockingQueue = blockingQueue;
        this.taskCount = taskCount;
    }

    public String call() throws Exception {
        for (int i = 0; i < taskCount; i++) {
            final int taskId = i;
            blockingQueue.enqueue(() -> {
                System.out.println("Task completed: " + name + " | taskID: " + taskId);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            Thread.sleep(50);
        }
        return name + " completed " + taskCount + " tasks";
    }
}