package server.mediathek;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mediathek {
    private static Mediathek instance;
    private HashMap<Integer, IDocument> documents;
    private HashMap<Integer, Abonne> abonnes;


    private Mediathek() {  // Constructeur privé : interdit la création avec "new"
        abonnes = new HashMap<>();
        documents = new HashMap<>();

        abonnes.put(1, new Abonne(1, "Jean Dupont", LocalDate.of(1990, 6, 15)));  // 15 juin 1990
        abonnes.put(2, new Abonne(2, "Alice Martin", LocalDate.of(2010, 3, 22)));
        // 15 juin 1990
        documents.put(101, new server.document.Livre(101, "Le Petit Prince", 96));
        documents.put(102, new server.document.DVD(102, "Interstellar", true)); // DVD adulte
    }

    public static Mediathek getInstance() {
        if (instance == null) {
            instance = new Mediathek();
        }
        return instance;
    }



    public Mediathek (IDocument document) {
        this();
        ajoutDocument(document);
    }

    public Mediathek (List<IDocument> documents) {
        this();
        ajoutDocument(documents);
    }


    public void ajoutDocument(IDocument documents){
        this.documents.put(documents.numero(), documents);
    }
    public void ajoutDocument(List<IDocument> documents){
        for (IDocument document : documents) {
            this.documents.put(document.numero(), document);
        }
    }

    public void removeDocument(int id) {
        documents.remove(id);
    }

    public IDocument getDocument(int id) {
        return documents.get(id);
    }


    public Abonne getAbonne(int id) {
        return abonnes.get(id);
    }

}
