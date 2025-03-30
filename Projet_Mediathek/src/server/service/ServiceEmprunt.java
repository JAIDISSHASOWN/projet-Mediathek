package server.service;

import com.unodos.Service;
import server.document.Document;
import server.mediathek.Abonne;

import server.Exception.EmpruntException;
import server.mediathek.IDocument;
import server.mediathek.Mediathek;

import java.io.*;
import java.net.Socket;

public class ServiceEmprunt extends Service {

    public ServiceEmprunt(Socket socket) {
        super(socket);
    }

    @Override
    public void run() {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
                PrintWriter writer = new PrintWriter(getSocket().getOutputStream(), true)
        ) {
            // Lire la requête du client (format : "emprunt abonne_id document_id")
            String request = reader.readLine();

            String[] parts = request.split(" ");
            if (parts.length != 3 || !parts[0].equalsIgnoreCase("emprunt")) {
                writer.println("Erreur : format invalide. Utilisation : emprunt <id_abonne> <id_document>");
                return;
            }

            int idAbonne = Integer.parseInt(parts[1]);
            int idDocument = Integer.parseInt(parts[2]);

            Mediathek mediatheque = Mediathek.getInstance();
            Abonne abonne = mediatheque.getAbonne(idAbonne);
            IDocument document = mediatheque.getDocument(idDocument);

            if (abonne == null) {
                writer.println("Erreur : Abonné non trouvé.");
                return;
            }

            if (document == null) {
                writer.println("Erreur : Document non trouvé.");
                return;
            }

            // Essayer d'emprunter le document
            try {
                document.emprunter(abonne);
                writer.println("Succès : Document " + document.numero() + " emprunté par " + abonne.getNom());
            } catch (EmpruntException e) {
                writer.println("Erreur : " + e.getMessage());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                getSocket().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
