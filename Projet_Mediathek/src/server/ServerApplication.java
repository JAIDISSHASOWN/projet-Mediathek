package server;

import java.io.IOException;

import com.unodos.Server;
import server.service.ServiceEmprunt;
import server.service.ServiceReservation;
import server.service.ServiceRetour;

public class ServerApplication {

    private final static int RESERVATION_PORT = 2000;
    private final static int EMPRUNT_PORT = 3000;
    private final static int RETOUR_PORT = 4000;

    public static void main(String[] args) {
            try {
                new Thread(new Server(ServiceReservation.class, RESERVATION_PORT)).start();
                System.out.println("Serveur de réservation lancé sur le port " + RESERVATION_PORT);
                new Thread(new Server(ServiceEmprunt.class, EMPRUNT_PORT)).start();
                System.out.println("Serveur d'emprunt lancé sur le port " + EMPRUNT_PORT);
                new Thread(new Server(ServiceRetour.class, RETOUR_PORT)).start();
                System.out.println("Server de retour lancé sur le port " + RETOUR_PORT);
            } catch (IOException e) {
                System.err.println("Erreur lors de la céation du serveur : " + e.getMessage());
            }
    }
}

