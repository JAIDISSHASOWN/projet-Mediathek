package server.document;

public class DVD extends Document {
    private boolean adulte;

    public DVD(int numero, String titre, boolean adulte) {
        super(numero, titre);
        this.adulte = adulte;
    }

    public boolean isAdulte() {
        return adulte;
    }
}
