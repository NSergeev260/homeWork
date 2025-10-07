package com.example.academy.streamapi.forkjoinpool;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Long> {
    private final int start;
    private final int end;
    private static final int THRESHOLD = 5;

    public FactorialTask(int n) {
        this(1, n);
    }

    private FactorialTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int range = end - start + 1;

        if (range <= THRESHOLD) {
            return computeDirectly();
        }

        int middle = start + (end - start) / 2;
        FactorialTask leftTask = new FactorialTask(start, middle);
        FactorialTask rightTask = new FactorialTask(middle + 1, end);

        leftTask.fork();
        long rightResult = rightTask.compute();
        long leftResult = leftTask.join();
        return leftResult * rightResult;
    }

    private long computeDirectly() {
        long result = 1;
        for (int i = start; i <= end; i++) {
            result *= i;
        }

        return result;
    }
}
