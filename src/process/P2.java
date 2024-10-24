package process;

import interfaces.MessageInterface;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class P2 implements MessageInterface {

    public static void main(String[] args) {
        try {
            // Créer et enregistrer l'objet P2 en tant que service RMI
            P2 obj = new P2();
            MessageInterface stub = (MessageInterface) UnicastRemoteObject.exportObject(obj, 0);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("P2Service", stub);

            System.out.println("P2 est prêt pour recevoir des messages.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(String message) throws RemoteException {
        System.out.println("P2 a reçu un message : " + message);

        // Après avoir reçu le message de P1, envoyer à P3
        try {
            Registry registry = LocateRegistry.getRegistry("192.168.1.12", 1099);
            MessageInterface p3 = (MessageInterface) registry.lookup("P3Service");

            // Envoyer le message à P3
            p3.sendMessage("Message de P2 à P3 après réception de P1");

            System.out.println("Message envoyé de P2 à P3.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
