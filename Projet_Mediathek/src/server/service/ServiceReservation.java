package server.service;

import com.unodos.Service;
import server.mediathek.IDocument;
import server.mediathek.Mediathek;

import server.mediathek.Abonne;
import server.exception.ReservationException;

import java.io.*;
import java.net.Socket;

public class ServiceReservation extends Service {
    public ServiceReservation(Socket socket) {
        super(socket);
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
                PrintWriter out = new PrintWriter(getSocket().getOutputStream(), true)
        ) {
            // Lire les numéros depuis le client
            int numeroAbonne = Integer.parseInt(in.readLine());
            int numeroDocument = Integer.parseInt(in.readLine());

            // Récupération des objets
            Mediathek mediatheque = Mediathek.getInstance();
            Abonne abonne = mediatheque.getAbonne(numeroAbonne);
            IDocument document = mediatheque.getDocument(numeroDocument);

            if (abonne == null) {
                out.println("Erreur : Abonné introuvable.");
                return;
            }
            if (document == null) {
                out.println("Erreur : Document introuvable.");
                return;
            }

            // Tentative de réservation
            try {
                document.reserver(abonne);
                out.println("Réservation réussie. Vous avez 1h pour venir chercher le document avant que la réservation " +
                        "s'annule");
            } catch (ReservationException e) {
                out.println("Échec de la réservation : " + e.getMessage());
            }

        } catch (IOException e) {
            System.err.println("Erreur ServiceReservation : " + e.getMessage());
        }
    }
}
