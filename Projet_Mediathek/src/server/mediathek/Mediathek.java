package server.mediathek;

import java.util.HashMap;
import java.util.List;

public class Mediathek {
    private HashMap<Integer, IDocument> documents;

    public Mediathek () {
        this.documents = new HashMap<>();
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
}
