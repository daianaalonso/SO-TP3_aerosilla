package opcion2;

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

                Thread.sleep(5000); // Tiempo que tarda en subir la silla

                System.out.println("Esquiador " + id + " bajando");
                barreraBajar.release(); // Avisa que se bajó de la silla
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
/*
import static opcion3.Main.CONTADOR;

public class Esquiador extends Thread {
    private Semaphore barreraEsquiadores;
    private Semaphore sillaLlena;
    private Semaphore mtxContador;
    private int id;

    public Esquiador(int id, Semaphore barreraEsquiadores, Semaphore sillaLlena, Semaphore mtxContador) {
        this.barreraEsquiadores = barreraEsquiadores;
        this.sillaLlena = sillaLlena;
        this.mtxContador = mtxContador;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            barreraEsquiadores.acquire(); // Aviso que el esquiador llega a la silla
            System.out.println("Esquiador "+id+ " sentado.");

            mtxContador.acquire();
            CONTADOR++;

            if (CONTADOR == 4) {
                CONTADOR = 0;
                mtxContador.release();
                sillaLlena.release(); //el ultimo en sentarse señaliza a la silla
            } else mtxContador.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
 */