package com.example.academy.concurrency.complexTask;

class ComplexTask {
    private int taskId;

    public ComplexTask(int id) {
        this.taskId = id;
    }

    public void execute() {
        System.out.println(Thread.currentThread().getName() + " perform the task: " + taskId);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(Thread.currentThread().getName() + " completed the task: " + taskId);
    }
}
