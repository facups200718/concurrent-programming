package com.skillsoft.concurrency;

public class MyThread implements Runnable {
    @Override
    public void run() {
        System.out.println("This has been executed by a thread");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new MyThread());
        thread.start();
        System.out.println("(thread instanceof Runnable) = " + (thread instanceof Runnable));
        System.out.println("(thread instanceof Thread) = " + (thread instanceof Thread));
        System.out.println("(thread instanceof Object) = " + (thread instanceof Object));
    }
}
