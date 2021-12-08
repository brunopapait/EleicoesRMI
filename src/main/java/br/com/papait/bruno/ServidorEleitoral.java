package br.com.papait.bruno;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServidorEleitoral implements VotoInterface {
    private List<Voto> votos = new ArrayList<>();
    private boolean stopMostraResultadoParcial = false;

    private void mostraResultadoParcial() throws InterruptedException, RemoteException {
        while (!stopMostraResultadoParcial) {
            if (votos.size() > 0) {
                System.out.println("\n\n Resultado Parcial");
                final var votosMap = votos
                        .stream()
                        .collect(Collectors.groupingBy(Voto::getNomeCandidato, Collectors.counting()));

                System.out.println("Quantidade de votos: " + contadorVotos());
                votosMap.forEach((key, value) -> System.out.println("Nome: " + key + " - " + value + " votos."));
            } else {
                System.out.println("Aguardando votos ...");
            }

            Thread.sleep(5000);
        }
    }

    @Override
    public int contadorVotos() throws RemoteException {
        return votos.size();
    }

    @Override
    public void receberVotos(Voto voto) throws RemoteException {
        votos.add(voto);
    }

    public static void main(String[] args) {
        try {
            ServidorEleitoral servidorEleitoral = new ServidorEleitoral();
            VotoInterface stub = (VotoInterface) UnicastRemoteObject.exportObject(servidorEleitoral, 0);

            Registry registro = LocateRegistry.createRegistry(1099);

            registro.bind("receberVotos", stub);
            System.out.println("Servidor iniciado !");

            servidorEleitoral.mostraResultadoParcial();
        } catch (RemoteException | AlreadyBoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
