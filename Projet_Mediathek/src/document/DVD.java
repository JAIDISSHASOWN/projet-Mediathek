package document;

import mediathek.IDocument;

public class DVD extends Document {
    private boolean adulte;

    public DVD(int id, String titre, boolean adulte) {
        super(id, titre);
        this.adulte = adulte;
    }

    public boolean isAdulte() {
        return adulte;
    }
}
