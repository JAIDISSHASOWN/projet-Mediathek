package server.service;

import com.unodos.Service;
import server.mediathek.IDocument;
import server.mediathek.Mediathek;

import server.document.Document;

import java.io.*;
import java.net.Socket;

public class ServiceRetour extends Service {
    public ServiceRetour(Socket socket) {
        super(socket);
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
                PrintWriter out = new PrintWriter(getSocket().getOutputStream(), true)
        ) {
            // Lire le numéro du document depuis le client
            int numeroDocument = Integer.parseInt(in.readLine());

            // Récupérer le document
            Mediathek mediatheque = Mediathek.getInstance();
            IDocument
                    document = mediatheque.getDocument(numeroDocument);

            if (document == null) {
                out.println("Erreur : Document introuvable.");
                return;
            }

            // Retourner le document
            document.retourner();
            out.println("Le document a été retourné avec succès.");

        } catch (IOException e) {
            System.err.println("Erreur ServiceRetour : " + e.getMessage());
        }
    }
}
