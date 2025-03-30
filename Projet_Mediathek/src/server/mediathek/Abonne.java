package server.mediathek;

import java.time.LocalDate;
import java.time.Period;

public class Abonne {
    private int numero;
    private String nom;
    private LocalDate dateNaissance;


    // on instancie comme ça
    // Abonne ab = new Abonne(1, "Jean Dupont", LocalDate.of(2005, 6, 15));
    public Abonne(int numero, String nom, LocalDate dateNaissance) {
        this.numero = numero;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
    }

    public int getAge() {
        return Period.between(dateNaissance, LocalDate.now()).getYears();
    }

    public String getNom() {
        return nom;
    }

    public int getNumero() {
        return numero;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }
}