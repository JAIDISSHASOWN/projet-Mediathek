package test;

import server.document.DVD;
import server.document.Livre;
import server.mediathek.IDocument;
import server.mediathek.Mediathek;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class MediathekTest {

    @Test
    public void TestMediathek () {
        IDocument berserk = new Livre(1,"Berserk", 100);
        IDocument bersork = new Livre(1,"Bersork", 1);
        IDocument dbEvolution = new DVD(2,"Dragon ball Evolution", false);

        //new Mediathek();
        new Mediathek(berserk);
        new Mediathek(Arrays.asList(berserk,dbEvolution));


        //assertNull(new Mediathek(null));
    }

}