package Aerosilla;

import java.util.concurrent.Semaphore;

public class Esquiador extends Thread {
    private Semaphore barreraEsquiadores;
    private Semaphore sillaLlena;
    private Semaphore barreraBajar;
    private int id;

    public Esquiador(int id, Semaphore barreraEsquiadores, Semaphore sillaLlena, Semaphore barreraBajar) {
        this.id = id;
        this.barreraEsquiadores = barreraEsquiadores;
        this.sillaLlena = sillaLlena;
        this.barreraBajar = barreraBajar;
    }

    @Override
    public void run() {
        while (true) {
            try {
                barreraEsquiadores.acquire(); // LLega el esquiador y espera la silla
                System.out.println("Esquiador " + id + " sentado");

                sillaLlena.release(); // Avisa que se sentó en la silla

                Thread.sleep(3000);

                System.out.println("Esquiador " + id + " bajando");
                barreraBajar.release(); // Avisa que se bajó de la silla
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}