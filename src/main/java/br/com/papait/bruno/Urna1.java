package br.com.papait.bruno;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Urna1 {

    public Urna1() {
        executar();
    }

    public void executar() {
        try {
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            VotoInterface stub = (VotoInterface) registro.lookup("receberVotos");
            Scanner scanner = new Scanner(System.in);
            int opcao = 0;

            do {
                System.out.println("Eleições 2021");
                System.out.println("Jair Messias Bolsonaro - Digite 1");
                System.out.println("Ciro Gomes - Digite 2");
                System.out.println("Sergio Moro - Digite 3");
                System.out.println("Encerrar votação - Digite 0");
                opcao = scanner.nextInt();

                if (opcao != 0) {
                    final var voto = computaVotos(opcao);
                    stub.receberVotos(voto);
                }
            } while (opcao != 0);

            System.out.println("Una 1 encerrada.");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    public Voto computaVotos(int opcao) {
        Voto voto;
        switch (opcao) {
            case 1:
                voto = new Voto("Jair Messias Bolsonaro");
                break;
            case 2:
                voto = new Voto("Ciro Gomes");
                break;
            case 3:
                voto = new Voto("Sergio Moro");
                break;
            default:
                voto = new Voto("Branco");
                break;
        }

        return voto;
    }

    public static void main(String[] args) {
        new Urna1();
    }

}
