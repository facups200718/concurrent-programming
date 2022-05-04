package com.skillsoft.concurrency;

public class ThreadLifeCycle {
    //Las clases estaticas se usan cuando solo se requiere una instanciacion
    // para obtener la funcionalidad completa de todos los metodos.
    //Es una alternativa a poner como static a todos los metodos
    //Por qué digo que los métodos son static? Porque no pueden acceder a variables no estáticas de la clase externa, OJO!
    //Entonces por eso con solo una instancia es suficiente.
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

    public static void main(String[] args) throws InterruptedException {
        var walkThread = new Thread(new Walk());
        var chewThread = new Thread(new ChewGum());;
        System.out.println("State of walk thread after initializing: " + walkThread.getState());
        try {
            walkThread.start();
            //El metodo join del main thread dice basicamente que se espere a que se termine
            //el thread walkThread para continuar con cualquier otra ejecucion
            //En caso de que sea interrumpido este proceso de espera, InterruptedException
            System.out.println("State of walk thread after start: " + walkThread.getState());
            walkThread.join();
            System.out.println("State of walk thread after join: " + walkThread.getState());
            chewThread.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
