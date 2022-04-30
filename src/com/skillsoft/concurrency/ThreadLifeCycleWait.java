package com.skillsoft.concurrency;

public class ThreadLifeCycleWait {
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
        var walkThread = new Thread(new ThreadLifeCycle.Walk());
        var chewThread = new Thread(new ThreadLifeCycle.ChewGum());;

        try {
            walkThread.start();
            //Si queremos que se espere al walkThread por solo 5 seg, por ejemplo
            walkThread.join(5000);
            chewThread.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
