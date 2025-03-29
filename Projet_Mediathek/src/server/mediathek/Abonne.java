package server.mediathek;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;


public class Abonne {
    private int numero;
    private String nom;
    private Date dateAnniversaire;

    public Abonne (int numero, String nomDate, Date dateAnniversaire){
        this.numero = numero;
        this.nom = nom;
        this.dateAnniversaire = dateAnniversaire;
    }

    public long getAge() {
        return Period.between(
                dateAnniversaire.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                LocalDate.now()
        ).getYears();
    }

    public String getNom() {
        return nom;
    }

    public int getNumero() {
        return numero;
    }
}
