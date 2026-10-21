package com.ga.concurrentcsvprocessor.collector;

import com.ga.concurrentcsvprocessor.model.EmployeeResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ResultCollector {

    private List<EmployeeResult> results = new ArrayList<>();
    private ReentrantLock lock = new ReentrantLock();

    public void addResult(EmployeeResult result) {

        lock.lock();

        try {
            results.add(result);

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

}