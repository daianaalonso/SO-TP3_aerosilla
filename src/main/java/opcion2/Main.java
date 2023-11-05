package opcion2;

import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        int cantidadEsquiadores = 8;
        int cantidadSillas = 3;
        Semaphore barreraEsquiadores = new Semaphore(0); //barrera para los 4 esquiadores
        Semaphore barreraBajar = new Semaphore(0); //barrera para los 4 esquiadores
        Semaphore mutex = new Semaphore(1); // para que solo acceda una silla a la vez
        Semaphore sillaLlena = new Semaphore(0); //avisa si la silla esta llena

        for (int i = 1; i <= cantidadEsquiadores; i++) {
            new Esquiador(i, barreraEsquiadores, sillaLlena, barreraBajar).start();
        }

        for (int i = 1; i <= cantidadSillas; i++) {
            new Silla(barreraEsquiadores, mutex, sillaLlena, barreraBajar).start();
        }
    }
}
