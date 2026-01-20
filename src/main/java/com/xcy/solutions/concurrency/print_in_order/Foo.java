package com.xcy.solutions.concurrency.print_in_order;

import java.util.concurrent.atomic.AtomicInteger;

public class Foo {
    private AtomicInteger secondJobSemaphore = new AtomicInteger(0);
    private AtomicInteger thirdJobSemaphore = new AtomicInteger(0);

    public void first(Runnable printFirst) {
        printFirst.run();
        secondJobSemaphore.incrementAndGet();
    }

    public void second(Runnable printSecond){
        while (secondJobSemaphore.get()!=1){
//            Thread.yield();
        }
        printSecond.run();
        thirdJobSemaphore.incrementAndGet();
    }

    public void third(Runnable printThird) {
        while (thirdJobSemaphore.get()!=1){
//            Thread.yield();
        }
        printThird.run();
    }

    public static void main(String[] args) throws InterruptedException {
        Foo foo = new Foo();
        Thread thread1 = new Thread(() -> foo.third(() -> System.out.print("third")));
        Thread thread2 = new Thread(() -> foo.second(() -> System.out.print("second")));
        Thread thread3 = new Thread(() -> foo.first(()-> System.out.print("first")));
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        System.out.println("\n All threads completed");
    }
}
