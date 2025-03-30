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

            // Essayer d'emprunter le document
            try {
                document.emprunter(abonne);
                out.println("Succès : Document " + document.numero() + " emprunté par " + abonne.getNom());
            } catch (EmpruntException e) {
                out.println("Erreur : " + e.getMessage());
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
