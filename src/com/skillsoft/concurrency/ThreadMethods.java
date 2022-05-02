package com.skillsoft.concurrency;

public class ThreadMethods {
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
        var walkThread = new Thread(new Walk());
        var chewThread = new Thread(new ChewGum());

        walkThread.start();
        chewThread.start();

        System.out.println("\nwalkThread.getId() = " + walkThread.getId());
        System.out.println("chewThread.getId() = " + chewThread.getId());
        System.out.println("Thread.currentThread().getId() = " + Thread.currentThread().getId());

        System.out.println("\nwalkThread.getName() = " + walkThread.getName());
        System.out.println("chewThread.getName() = " + chewThread.getName());
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());

        System.out.println("\nwalkThread.getThreadGroup() = " + walkThread.getThreadGroup());
        System.out.println("chewThread.getThreadGroup() = " + chewThread.getThreadGroup());
        System.out.println("Thread.currentThread().getThreadGroup() = " + Thread.currentThread().getThreadGroup());

        System.out.println("\nwalkThread.getPriority() = " + walkThread.getPriority());
        System.out.println("chewThread.getPriority() = " + chewThread.getPriority());
        System.out.println("Thread.currentThread().getPriority() = " + Thread.currentThread().getPriority());

        System.out.println("#Thread.activeCount() = " + Thread.activeCount());
        System.out.println("\n\n");
    }
}
