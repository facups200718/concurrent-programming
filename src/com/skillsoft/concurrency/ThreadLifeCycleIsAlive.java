package com.skillsoft.concurrency;

public class ThreadLifeCycleIsAlive {
    public static class Walk implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i < 5; i++) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("I'm walking... My state is " + Thread.currentThread().isAlive());
            }
        }
    }

    public static void main(String[] args) {
        var walkThread = new Thread(new ThreadLifeCycleIsAlive.Walk());
        System.out.println("State of walkThread after init: " + walkThread.isAlive());

        try {
            walkThread.start();
            System.out.println("isAlive State of walkThread after start: " + walkThread.isAlive());

            Thread.sleep(5000);
            System.out.println("isAlive State of walkThread after join: " + walkThread.isAlive());

            Thread.sleep(10000);
            System.out.println("isAlive State of walkThread at the end: " + walkThread.isAlive());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
