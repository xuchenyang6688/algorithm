package com.xcy.solutions.concurrency.print_in_order;

import java.util.concurrent.Semaphore;

public class FooSemaphore {
    Semaphore secondSemaphore = new Semaphore(0);
    Semaphore thirdSemaphore = new Semaphore(0);

    public void first (Runnable printFirst) {
        printFirst.run();
        secondSemaphore.release(); // release means secondSemaphore permits increased 0->1
    }
    public void second(Runnable printSecond) throws InterruptedException {
        secondSemaphore.acquire(); // 1 -> 0
        printSecond.run();
        thirdSemaphore.release();
    }
    public void third (Runnable printThird) throws InterruptedException {
        thirdSemaphore.acquire();
        printThird.run();
    }

    public static void main(String[] args) throws Exception {
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
