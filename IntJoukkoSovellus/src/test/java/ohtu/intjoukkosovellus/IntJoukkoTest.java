package ohtu.intjoukkosovellus;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntJoukkoTest {

    IntJoukko joukko;

    @Before
    public void setUp() {
        joukko = new IntJoukko();
        joukko.lisaaJoukkoonLuku(10);
        joukko.lisaaJoukkoonLuku(3);
    }

    @Test
    public void lukujaLisattyMaara() {
        joukko.lisaaJoukkoonLuku(4);
        assertEquals(3, joukko.getAlkoioidenLkm());
    }

    @Test
    public void samaLukuMeneeJoukkoonVaanKerran() {
        joukko.lisaaJoukkoonLuku(10);
        joukko.lisaaJoukkoonLuku(3);
        assertEquals(2, joukko.getAlkoioidenLkm());
    }

    @Test
    public void vainLisatytLuvutLoytyvat() {
        assertTrue(joukko.kuuluuJoukkoon(10));
        assertFalse(joukko.kuuluuJoukkoon(5));
        assertTrue(joukko.kuuluuJoukkoon(3));
    }

    @Test
    public void poistettuEiOleEnaaJoukossa() {
        joukko.poistaJoukostaLuku(3);
        assertFalse(joukko.kuuluuJoukkoon(3));
        assertEquals(1, joukko.getAlkoioidenLkm());
    }
    
    @Test
    public void palautetaanOikeaTaulukko() {
        int[] odotettu = {3, 55, 99};
        
        joukko.lisaaJoukkoonLuku(55);
        joukko.poistaJoukostaLuku(10);
        joukko.lisaaJoukkoonLuku(99);

        int[] vastaus = joukko.toIntArray();
        Arrays.sort(vastaus);
        assertArrayEquals(odotettu, vastaus);
    }
    
    
    @Test
    public void toimiiKasvatuksenJalkeen(){
        int[] lisattavat = {1,2,4,5,6,7,8,9,11,12,13,14};
        for (int luku : lisattavat) {
            joukko.lisaaJoukkoonLuku(luku);
        }
        assertEquals(14, joukko.getAlkoioidenLkm());
        assertTrue(joukko.kuuluuJoukkoon(11));
        joukko.poistaJoukostaLuku(11);
        assertFalse(joukko.kuuluuJoukkoon(11));
        assertEquals(13, joukko.getAlkoioidenLkm());
    }
    
    @Test
    public void toStringToimii(){
        assertEquals("{10, 3}", joukko.toString());
    }
    
    @Test
    public void toStringToimiiYhdenKokoiselleJoukolla(){
        joukko = new IntJoukko();
        joukko.lisaaJoukkoonLuku(1);
        assertEquals("{1}", joukko.toString());
    }

    @Test
    public void toStringToimiiTyhjallaJoukolla(){
        joukko = new IntJoukko();
        assertEquals("{}", joukko.toString());
    }     
}
