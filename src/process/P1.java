package process;

import interfaces.MessageInterface;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class P1 {
    public static void main(String[] args) {
        try {
            // Récupérer l'objet distant P2 (qui est sur une autre machine)
            Registry registry = LocateRegistry.getRegistry("192.168.1.14", 1099);
            MessageInterface p2 = (MessageInterface) registry.lookup("P2Service");
            // Envoyer le message à P2
            p2.sendMessage("Message de P1 à P2");

            System.out.println("Message envoyé de P1 à P2.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
