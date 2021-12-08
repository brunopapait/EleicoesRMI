package br.com.papait.bruno;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface VotoInterface extends Remote {
    int contadorVotos() throws RemoteException;

    void receberVotos(Voto voto) throws RemoteException;
}
