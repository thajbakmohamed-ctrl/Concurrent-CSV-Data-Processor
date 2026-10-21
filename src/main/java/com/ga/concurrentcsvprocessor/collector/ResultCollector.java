package com.ga.concurrentcsvprocessor.collector;

import com.ga.concurrentcsvprocessor.model.EmployeeResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.atomic.AtomicInteger;

public class ResultCollector {

    private List<EmployeeResult> results = new ArrayList<>();
    private ReentrantLock lock = new ReentrantLock();
    private AtomicInteger processedCount = new AtomicInteger(0);

    public void addResult(EmployeeResult result) {

        lock.lock();

        try {
            results.add(result);
            processedCount.incrementAndGet();

        } finally {
            lock.unlock();
        }
    }

    public List<EmployeeResult> getResults() {

        lock.lock();

        try {
            return new ArrayList<>(results);

        } finally {
            lock.unlock();
        }
    }
    public int getProcessedCount() {
        return processedCount.get();
    }

}