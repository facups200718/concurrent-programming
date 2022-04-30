package com.skillsoft.concurrency;

public class AnotherThreadClass implements Runnable {

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
        var firstThread = new Thread(new ThreadClass());
        var secondThread = new Thread(new ThreadClass());

        firstThread.run();
        secondThread.run();
        //Primero se ejecuta un for entero y luego el otro.
        //Por qué? Porque las dos llamadas se estan corriendo en el mismo Thread "main"
        //Los metodos run se ejecutaron secuencialmente en vez de concurrentemente
    }
}
