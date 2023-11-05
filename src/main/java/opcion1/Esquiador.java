package opcion1;

import java.util.concurrent.Semaphore;

public class Esquiador extends Thread {
    private Semaphore barreraEsquiadores;
    private Semaphore barreraBajar;
    private int id;

    public Esquiador(int id, Semaphore barreraEsquiadores, Semaphore barreraBajar) {
        this.barreraEsquiadores = barreraEsquiadores;
        this.id = id;
        this.barreraBajar = barreraBajar;
    }

    @Override
    public void run() {
        while (true) {
            try {
                barreraEsquiadores.acquire(); // LLega el esquiador y espera la silla
                System.out.println("Esquiador " + id + " sentado");

                Thread.sleep(5000);// Tiempo que tarda en subir la silla

                System.out.println("Esquiador " + id + " bajando");
                barreraBajar.release(); // Avisa que se bajó de la silla
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}