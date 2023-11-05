package opcion1;

import java.util.concurrent.Semaphore;

public class Silla extends Thread {
    private Semaphore barreraEsquiadores;
    private Semaphore barreraBajar;
    private Semaphore mutex;

    public Silla(Semaphore barreraEsquiadores, Semaphore mutex, Semaphore barreraBajar) {
        this.barreraEsquiadores = barreraEsquiadores;
        this.mutex = mutex;
        this.barreraBajar = barreraBajar;
    }

    public void run() {
        while (true) {
            try {
                mutex.acquire();//unica silla disponible
                System.out.println("Silla disponible. Esperando esquiadores...");

                barreraEsquiadores.release(4); //le avisa a los esquiadores

                barreraBajar.acquire(4);
                System.out.println("Silla volviendo a la base...");
                mutex.release();//libero para que otra silla este disponible
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}