package server.mediathek;

public class Abonne {
    private int numero;
    private String nom;

    public Abonne (int numero, String nom){
        this.numero = numero;
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public int getNumero() {
        return numero;
    }
}
