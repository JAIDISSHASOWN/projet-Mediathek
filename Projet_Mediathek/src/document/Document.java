package document;

import mediathek.IDocument;

public abstract class Document implements IDocument {
    private int id;
    private String titre;

    public Document (int id, String titre){
        this.id = id;
        this.titre = titre;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }
}
