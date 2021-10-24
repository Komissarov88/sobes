package com.komissarov.sobes.pingpong;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {

    private int count;
    private final Lock lock = new ReentrantLock();

    public void increase() {
        lock.lock();
        count++;
    }

    public void unlock() {
        lock.unlock();
    }

    public int getCount() {
        return count;
    }
}
