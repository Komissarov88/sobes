package com.komissarov.sobes.pingpong;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        Object monitor = new Object();
        Counter counter = new Counter();

        pool.submit(new PingPong(monitor, "Ping ", counter));
        pool.submit(new PingPong(monitor, "Pong ", counter));
        pool.submit(() -> {
            while (counter.getCount() < 100) {
                counter.increase();
                try {
                    Thread.sleep(60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                counter.unlock();
            }
        });
        pool.shutdown();
    }
}
