import interfaces.MessageInterface;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class P3 implements MessageInterface {

    public static void main(String[] args) {
        try {
            // Créer et enregistrer l'objet P3 en tant que service RMI
            P3 obj = new P3();
            MessageInterface stub = (MessageInterface) UnicastRemoteObject.exportObject(obj, 0);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("P3Service", stub);

            System.out.println("P3 est prêt pour recevoir des messages.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(String message) throws RemoteException {
        System.out.println("P3 a reçu un message : " + message);
    }
}
