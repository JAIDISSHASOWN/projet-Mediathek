package server.document;

import server.Exception.EmpruntException;
import server.Exception.ReservationException;
import server.mediathek.Abonne;
import server.mediathek.IDocument;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Document implements IDocument {
    private int numero;
    private String titre;
    private Date dateFinReservation;


    private Abonne emprunteur = null;
    private Abonne reserveur = null;
    private Timer timerReservation = null;

    public Document(int numero, String titre) {
        this.numero = numero;
        this.titre = titre;
    }

    public int numero() {
        return numero;
    }

    public String getTitre() {
        return titre;
    }

    @Override
    public void reserver(Abonne ab) throws ReservationException {
        synchronized (this) {
            if (reserveur != null) {
                throw new ReservationException("ce document est déjà reservé");
            }
            if (emprunteur != null) {
                throw new ReservationException("Ce document est actuellement emprunté.");
            }

            // Réservation acceptée
            reserveur = ab;
            System.out.println("Document " + titre + " réservé par " + ab.getNom() + " pour 1h.");

            dateFinReservation = new Date(System.currentTimeMillis() + (60 * 60 * 1000));

            // Lancer un timer pour annuler la réservation après 1h
            timerReservation = new Timer();
            timerReservation.schedule(new TimerTask() {
                @Override
                public void run() {
                    annulerReservation();
                }
            }, 60 * 60 * 1000); // 1 heure en millisecondes
        }
    }

    private void annulerReservation() {
        synchronized (this) {
            System.out.println("La réservation du document " + titre + " a expiré.");
            reserveur = null;
            if (timerReservation != null) {
                timerReservation.cancel();
                timerReservation = null;
            }
        }
    }

    @Override
    public void emprunter(Abonne ab) throws EmpruntException {
        synchronized (this) {
            if (emprunteur != null) {
                throw new EmpruntException("Ce document est déjà emprunté.");
            }
            if (reserveur != null && !reserveur.equals(ab)) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH'h'mm");
                String heureFin = sdf.format(dateFinReservation);
                throw new EmpruntException("Ce document est réservé jusqu’à " + heureFin + ".");
            }

            // Annuler la réservation si l'emprunteur est bien celui qui l'a réservé
            if (reserveur != null && reserveur.equals(ab)) {
                annulerReservation();
            }

            // Emprunt accepté
            emprunteur = ab;
            System.out.println("Document " + titre + " emprunté par " + ab.getNom() + ".");
        }
    }

    @Override
    public void retourner() {
            synchronized (this) {
                if (emprunteur == null && reserveur == null) {
                    System.out.println("Aucun emprunteur ou réservation pour ce document.");
                    return;
                }

                System.out.println("Document " + titre + " retourné.");
                emprunteur = null;
                annulerReservation();
            }
        }
}
