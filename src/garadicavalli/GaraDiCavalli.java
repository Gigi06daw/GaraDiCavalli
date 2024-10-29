package garadicavalli;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Cavallo extends Thread {
    private final String nome;
    private final int lunghezzaPercorso;
    private int distanzaPercorsa = 0;
    private static final Random random = new Random();

    public Cavallo(String nome, int lunghezzaPercorso) {
        this.nome = nome;
        this.lunghezzaPercorso = lunghezzaPercorso;
    }

    @Override
    public void run() {
        while (distanzaPercorsa < lunghezzaPercorso) {
            int passo = random.nextInt(10) + 1; // Avanzamento casuale tra 1 e 10 metri
            distanzaPercorsa += passo;
            System.out.println(nome + " ha percorso " + distanzaPercorsa + " metri su " + lunghezzaPercorso);
            try {
                Thread.sleep(200); // Pausa per simulare il tempo di avanzamento
            } catch (InterruptedException e) {
                System.out.println(nome + " è stato interrotto!");
            }
        }
        System.out.println(nome + " ha terminato la gara!");
    }
}

public class GaraDiCavalli {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci la lunghezza del percorso (in metri): ");
        int lunghezzaPercorso = scanner.nextInt();

        System.out.print("Inserisci il numero di cavalli: ");
        int numeroCavalli = scanner.nextInt();
        scanner.nextLine(); // Consuma il newline

        List<Cavallo> cavalli = new ArrayList<>();

        for (int i = 0; i < numeroCavalli; i++) {
            System.out.print("Inserisci il nome del cavallo " + (i + 1) + ": ");
            String nome = scanner.nextLine();
            Cavallo cavallo = new Cavallo(nome, lunghezzaPercorso);
            cavalli.add(cavallo);
        }

        System.out.println("I cavalli stanno per partire!");

        for (Cavallo cavallo : cavalli) {
            cavallo.start();
        }

        for (Cavallo cavallo : cavalli) {
            try {
                cavallo.join();
            } catch (InterruptedException e) {
                System.out.println("La gara è stata interrotta!");
            }
        }

        System.out.println("La gara è terminata!");
        scanner.close();
    }
}
