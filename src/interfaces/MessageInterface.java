package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MessageInterface extends Remote {
    void sendMessage(String message) throws RemoteException;
}
