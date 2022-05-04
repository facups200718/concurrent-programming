package com.skillsoft.concurrency;

public class ThreadMethodsDaemon {
    public static class Walk implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i < 5; i++) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("I'm walking...");
            }
        }
    }

    public static class ChewGum implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i < 5; i++) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("I'm chewing gum...");
            }
        }
    }

    public static void main(String[] args) {
        var walkThread = new Thread(new ThreadMethodsDaemon.Walk());
        var chewThread = new Thread(new ThreadMethodsDaemon.ChewGum());

        chewThread.setDaemon(true);

        System.out.println("walkThread.isDaemon() = " + walkThread.isDaemon());
        System.out.println("chewThread.isDaemon() = " + chewThread.isDaemon());
        System.out.println("Thread.currentThread().isDaemon() = " + Thread.currentThread().isDaemon());

        System.out.println("\n\n");

        try {
            walkThread.start();
            walkThread.join(5000);
            chewThread.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
