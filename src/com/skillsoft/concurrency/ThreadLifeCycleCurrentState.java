package com.skillsoft.concurrency;

public class ThreadLifeCycleCurrentState {
    public static class Walk implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i < 5; i++) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("I'm walking... My state is " + Thread.currentThread().getState());
            }
        }
    }

    public static void main(String[] args) {
        var walkThread = new Thread(new Walk());
        System.out.println("State of walkThread after init: " + walkThread.getState());
        System.out.println("State of main thread after walkThread init: " + Thread.currentThread().getState());

        try {
            walkThread.start();
            System.out.println("State of walkThread after start: " + walkThread.getState());
            System.out.println("State of main thread after walkThread start: " + Thread.currentThread().getState());

            Thread.sleep(1000);
            walkThread.join(5000);

            System.out.println("State of walkThread after join: " + walkThread.getState());
            System.out.println("State of main thread after walkThread join: " + Thread.currentThread().getState());

            System.out.println("Main thread will sleep for 20s...");
            Thread.sleep(20000);

            System.out.println("State of walkThread at the end: " + walkThread.getState());
            System.out.println("State of main thread at the end: " + Thread.currentThread().getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
