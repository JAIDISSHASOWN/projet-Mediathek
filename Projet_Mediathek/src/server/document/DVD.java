package server.document;

import server.exception.EmpruntException;
import server.exception.ReservationException;
import server.mediathek.Abonne;

import java.time.LocalDate;
import java.time.Period;

public class DVD extends Document {
    private final boolean adulte;

    public DVD(int numero, String titre, boolean adulte) {
        super(numero, titre);
        this.adulte = adulte;
    }

    public boolean isAdulte() {
        return adulte;
    }

    private boolean abonneAutorise(Abonne ab) {
        // Vérifie si l'abonné a au moins 16 ans
        LocalDate aujourdHui = LocalDate.now();
        int age = Period.between(ab.getDateNaissance(), aujourdHui).getYears();
        return age >= 18;
    }

    @Override
    public synchronized void reserver(Abonne ab) throws ReservationException {
        // Vérifie si l'abonné a au moins 16 ans
        if (adulte && !abonneAutorise(ab)) {
            throw new ReservationException("Vous n’avez pas l’âge pour emprunter ce DVD.");
        }
        super.reserver(ab);
    }

    @Override
    public synchronized void emprunter(Abonne ab) throws EmpruntException {
        // Vérifie si l'abonné a au moins 16 ans
        if (adulte && !abonneAutorise(ab)) {
            throw new EmpruntException("Réservé aux plus de 18 ans.");
        }
        super.emprunter(ab);
    }
}
