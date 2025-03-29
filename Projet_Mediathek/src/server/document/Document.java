package server.document;

import server.mediathek.IDocument;

public abstract class Document implements IDocument {
    private int numero;
    private String titre;

    public Document (int numero, String titre){
        this.numero = numero;
        this.titre = titre;
    }

    public int numero() {
        return numero;
    }

    public String getTitre() {
        return titre;
    }
}
