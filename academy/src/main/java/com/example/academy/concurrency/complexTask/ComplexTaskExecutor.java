package com.example.academy.concurrency.complexTask;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ComplexTaskExecutor {
    private int taskCount;
    private CyclicBarrier barrier;

    public ComplexTaskExecutor(int taskCount) {
        this.taskCount = taskCount;
    }

    public void executeTasks(int numberOfTasks) {
        barrier = new CyclicBarrier(numberOfTasks, () -> {
            System.out.println("All tasks completed! Combining the results");
        });

        ExecutorService executor = Executors.newFixedThreadPool(numberOfTasks);

        for (int i = 0; i < numberOfTasks; i++) {
            int taskId = i;
            executor.submit(() -> {
                ComplexTask task = new ComplexTask(taskId);
                task.execute();

                try {
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
    }
}
