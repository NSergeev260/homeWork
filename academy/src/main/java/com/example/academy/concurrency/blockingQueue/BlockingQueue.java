package com.example.academy.concurrency.blockingQueue;

import java.util.ArrayList;
import java.util.List;

public class BlockingQueue {
    private final List<Runnable> tasks;
    private final int maxSize;

    public BlockingQueue(int maxSize) {
        this.maxSize = maxSize;
        this.tasks = new ArrayList<>(maxSize);
    }

    synchronized void enqueue(Runnable task) {
        while (tasks.size() >= maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        tasks.add(task);
        System.out.println(" [+] Task enqueued. Queue size: " + tasks.size() +
                " | Thread name: " + Thread.currentThread().getName());
        notifyAll();
    }

    synchronized Runnable dequeue() {
        while (tasks.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        Runnable task = tasks.get(0);
        tasks.remove(task);
        notifyAll();
        System.out.println(" [-] Task dequeued. Queue size: " + tasks.size() +
                " | Thread name: " + Thread.currentThread().getName());
        return task;
    }

    public synchronized int size() {
        return tasks.size();
    }
}
