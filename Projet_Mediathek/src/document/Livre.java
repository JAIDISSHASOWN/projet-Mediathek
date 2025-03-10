package document;
import mediathek.IDocument;

public class Livre extends Document {
    private int nbrDePages;

    public Livre(int id, String titre, int nbrDePages) {
        super(id, titre);
        this.nbrDePages = nbrDePages;
    }

    public int getNbrDePages() {
        return nbrDePages;
    }
}
