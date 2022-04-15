package com.skillsoft.concurrency;

public class MyThreadClass extends Thread {
    @Override
    public void run() {
        System.out.println("Thread is running");
    }

    public static void main(String[] args) {
        MyThreadClass myThread = new MyThreadClass();
        myThread.start();
        System.out.println("(thread instanceof Runnable) = " + (myThread instanceof Runnable));
        System.out.println("(thread instanceof Thread) = " + (myThread instanceof Thread));
        System.out.println("(thread instanceof Object) = " + (myThread instanceof Object));
    }
}
