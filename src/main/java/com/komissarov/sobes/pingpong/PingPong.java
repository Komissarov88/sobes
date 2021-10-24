package com.komissarov.sobes.pingpong;

import java.util.Random;

public class PingPong implements Runnable{

    private final Object monitor;
    private final String msg;
    private final Counter counter;
    private final Random rnd = new Random();

    public PingPong(Object monitor, String msg, Counter counter) {
        this.monitor = monitor;
        this.msg = msg;
        this.counter = counter;
    }

    public void run() {
        synchronized(monitor) {
            while (counter.getCount() < 100) {
                System.out.println(msg + counter.getCount());
                try {
                    counter.increase();
                    Thread.sleep(rnd.nextInt(150));
                    counter.unlock();
                    monitor.notifyAll();
                    monitor.wait();
                } catch (InterruptedException ignored) {}
            }
            System.out.println("end");
            monitor.notifyAll();
        }
    }
}
