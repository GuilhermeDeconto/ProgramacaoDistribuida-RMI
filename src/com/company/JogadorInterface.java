package com.company;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface JogadorInterface extends Remote {
    public void encerrado() throws RemoteException;

    public void inicia() throws RemoteException;

    public void cutucado() throws RemoteException;
}
