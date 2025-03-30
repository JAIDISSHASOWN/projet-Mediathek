package clients;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientApplication {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Utilisation : java ClientReservation <port>");
            return;
        }

        int port;
        try {
            port = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Le port doit être un nombre entier valide.");
            return;
        }
        try (Socket socket = new Socket("localhost", port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)){

            if (port == 2000 || port == 3000) {
                // Demander le numéro d'abonné
                System.out.print("Entrez votre numéro d'abonné : ");
                String numAbonne = scanner.nextLine();
                out.println(numAbonne); // Envoyer au serveur
            }
            // Demander le numéro du document
            System.out.print("Entrez le numéro du document : ");
            String numDocument = scanner.nextLine();
            out.println(numDocument); // Envoyer au serveur


            String response = in.readLine();
            System.out.println("Réponse du serveur: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}