package com.skillsoft.concurrency;

public class ThreadMethodsThreadGroups {
    public static class Walk implements Runnable {

        @Override
        public void run() {
            for (int i = 1; i < 5; i++) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                var threadGroup = Thread.currentThread().getThreadGroup().getName();
                int activeThreads = Thread.activeCount();

                System.out.println("I'm walking... My group " + threadGroup + "has an" +
                        " active count of " + activeThreads);
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

                var threadGroup = Thread.currentThread().getThreadGroup().getName();
                int activeThreads = Thread.activeCount();

                System.out.println("I'm chewing... My group " + threadGroup + " has an" +
                        " active count of " + activeThreads);
            }
        }
    }

    public static void main(String[] args) {
        var groupOne = new ThreadGroup("GroupOne");
        var groupTwo = new ThreadGroup("GroupTwo");

        var walkThreadTwo = new Thread(groupTwo, new ThreadMethodsThreadGroups.Walk());
        var walkThreadOne = new Thread(groupOne, new ThreadMethodsThreadGroups.Walk());
        var walkThreadThree = new Thread(groupTwo, new ThreadMethodsThreadGroups.Walk());

        var chewThreadTwo = new Thread(groupTwo, new ThreadMethodsThreadGroups.ChewGum());
        var chewThreadOne = new Thread(groupOne, new ThreadMethodsThreadGroups.ChewGum());

        walkThreadOne.start();
        walkThreadTwo.start();
        walkThreadThree.start();
        chewThreadOne.start();
        chewThreadTwo.start();

        System.out.println("Active threads for main: " + Thread.activeCount());
        System.out.println("Active threads for GroupOne: " + groupOne.activeCount());
        System.out.println("Active threads for GroupTwo: " + groupTwo.activeCount());
    }
}
