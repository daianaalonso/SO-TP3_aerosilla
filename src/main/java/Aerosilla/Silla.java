package Aerosilla;

import java.util.concurrent.Semaphore;

public class Silla extends Thread {
    private Semaphore barreraEsquiadores;
    private Semaphore barreraBajar;
    private Semaphore mutex;
    private Semaphore sillaLlena;
    private int id;

    public Silla(int id, Semaphore barreraEsquiadores, Semaphore mutex, Semaphore sillaLlena, Semaphore barreraBajar) {
        this.id = id;
        this.barreraEsquiadores = barreraEsquiadores;
        this.mutex = mutex;
        this.sillaLlena = sillaLlena;
        this.barreraBajar = barreraBajar;
    }

    public void run() {
        while (true) {
            try {
                System.out.println("Silla " + id + " esperando");

                mutex.acquire();//unica silla disponible
                System.out.println("Silla " + id + " disponible. Esperando esquiadores");

                barreraEsquiadores.release(4); //le avisa a los esquiadores

                sillaLlena.acquire(4); //espera que se suban los esquiadores

                System.out.println("Subiendo a la cima");

                barreraBajar.acquire(4); //espera que se bajen los esquiadores

                System.out.println("Silla " + id + " volviendo a la base");
                mutex.release();//libero para que otra silla este disponible
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}