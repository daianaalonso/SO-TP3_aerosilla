package opcion2;

import java.util.concurrent.Semaphore;

public class Silla extends Thread {
    private Semaphore barreraEsquiadores;
    private Semaphore barreraBajar;
    private Semaphore mutex;
    private Semaphore sillaLlena;

    public Silla(Semaphore barreraEsquiadores, Semaphore mutex, Semaphore sillaLlena, Semaphore barreraBajar) {
        this.barreraEsquiadores = barreraEsquiadores;
        this.mutex = mutex;
        this.sillaLlena = sillaLlena;
        this.barreraBajar = barreraBajar;
    }

    public void run() {
        while (true) {
            try {
                mutex.acquire();//unica silla disponible
                System.out.println("Silla disponible. Esperando esquiadores...");

                barreraEsquiadores.release(4); //le avisa a los esquiadores

                sillaLlena.acquire(4); //espera que se suban los esquiadores
                System.out.println("Subiendo a la cima...");

                barreraBajar.acquire(4); //espera que se bajen los esquiadores

                System.out.println("Silla volviendo a la base...");
                mutex.release();//libero para que otra silla este disponible
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
       }
    }
}