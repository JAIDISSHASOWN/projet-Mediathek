package test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import server.mediathek.Mediathek;
import server.mediathek.IDocument;
import server.mediathek.Abonne;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import server.exception.ReservationException;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class ServiceReservationTest {

    private Mediathek mediatheque;
    private Abonne abonne;
    private IDocument document;

    @BeforeEach
    void setUp() {
        mediatheque = mock(Mediathek.class);
        abonne = mock(Abonne.class);
        document = mock(IDocument.class);
    }

    @Test
    void testReservationReussie() throws ReservationException {
        // Simule un abonné et un document existant
        when(mediatheque.getAbonne(123)).thenReturn(abonne);
        when(mediatheque.getDocument(456)).thenReturn(document);

        // Simule un document non réservé ni emprunté
        doNothing().when(document).reserver(abonne);

        // Vérifie qu'aucune exception n'est levée
        assertDoesNotThrow(() -> document.reserver(abonne));
        verify(document, times(1)).reserver(abonne);
    }

    @Test
    void testReservationEchoue_DejaReserve() throws ReservationException {
        when(mediatheque.getAbonne(123)).thenReturn(abonne);
        when(mediatheque.getDocument(456)).thenReturn(document);

        // Simule une exception quand on essaie de réserver un document déjà réservé
        doThrow(new ReservationException("Ce document est déjà réservé.")).when(document).reserver(abonne);

        ReservationException exception = assertThrows(ReservationException.class, () -> document.reserver(abonne));
        assertEquals("Ce document est déjà réservé.", exception.getMessage());

        verify(document, times(1)).reserver(abonne);
    }

    @Test
    void testReservationEchoue_DejaEmprunte() throws ReservationException {
        when(mediatheque.getAbonne(123)).thenReturn(abonne);
        when(mediatheque.getDocument(456)).thenReturn(document);

        // Simule une exception quand le document est déjà emprunté
        doThrow(new ReservationException("Ce document est actuellement emprunté.")).when(document).reserver(abonne);

        ReservationException exception = assertThrows(ReservationException.class, () -> document.reserver(abonne));
        assertEquals("Ce document est actuellement emprunté.", exception.getMessage());

        verify(document, times(1)).reserver(abonne);
    }
}