package com.xcy.solutions.concurrency.print_in_order;

import java.util.concurrent.CountDownLatch;public class FooCountDownLatch {
    CountDownLatch second = new CountDownLatch(1);
    CountDownLatch third = new CountDownLatch(1);

    public void first(Runnable printFirst) {
        printFirst.run();
        second.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        second.await(); //count reaches 0, the gate opens and all waiting threads can proceed
        printSecond.run();
        third.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        third.await();
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
