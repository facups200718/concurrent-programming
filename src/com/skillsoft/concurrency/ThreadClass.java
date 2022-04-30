package com.skillsoft.concurrency;

public class ThreadClass implements Runnable {

    @Override
    public void run() {
        for (int i = 1; i < 5; i++) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " says: " + i);
        }
    }

    public static void main(String[] args) {
        var firstThread = new Thread(new ThreadClass(), "First Thread");
        var secondThread = new Thread(new ThreadClass());
        secondThread.setName("Second Thread");

        firstThread.start();
        secondThread.start();
        //Datazo: no podemos iniciar (start) un thread más de una vez, no se puede crear un mismo thread dos veces
        //Sin embargo, podemos llamar a su método run() más de una vez ya que este se ejecutará en un thread default "main"
        firstThread.run();
    }
}
