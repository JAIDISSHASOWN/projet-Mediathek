package server.document;

public class Livre extends Document {
    private int nbrDePages;

    public Livre(int numero, String titre, int nbrDePages) {
        super(numero, titre);
        this.nbrDePages = nbrDePages;
    }

    public int getNbrDePages() {
        return nbrDePages;
    }
}
