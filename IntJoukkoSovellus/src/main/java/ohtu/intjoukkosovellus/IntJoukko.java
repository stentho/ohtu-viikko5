package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            kapasiteetti = 0;
        }
        ljono = new int[kapasiteetti];
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;

    }

    public boolean lisaaJoukkoonLuku(int luku) {
        if (kuuluuJoukkoon(luku)) {
            return false;
        }
        ljono[alkioidenLkm] = luku;
        alkioidenLkm++;
        if (alkioidenLkm % ljono.length == 0) {
            luoUusiJoukko();
        }
        return true;
    }

    public boolean kuuluuJoukkoon(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poistaJoukostaLuku(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (ljono[i] == luku) {
                siirraJoukonViimeiseksiById(i);
                alkioidenLkm--;
                return true;
            }
        }
        return false;
    }

    private void siirraJoukonViimeiseksiById(int i) {
        for (int j = i; j < alkioidenLkm - 1; j++) {
            int apu = ljono[j];
            ljono[j] = ljono[j + 1];
            ljono[j + 1] = apu;
        }
    }

    private void kopioiTaulukkoYhdestaToiseen(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    private void luoUusiJoukko() {
        int[] taulukkoOld = new int[ljono.length];
        taulukkoOld = ljono;
        ljono = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukkoYhdestaToiseen(taulukkoOld, ljono);
    }

    public int getAlkoioidenLkm() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        String alkiot = "";
        if (alkioidenLkm != 0) {
            alkiot = getAlkoitString();
        }
        return "{" + alkiot + "}";
    }

    private String getAlkoitString() {
        String alkiot = "";
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            alkiot += ljono[i];
            alkiot += ", ";
        }
        alkiot += ljono[alkioidenLkm - 1];
        return alkiot;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = ljono[i];
        }
        return taulu;
    }

    public static IntJoukko yhdisteJoukoista(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        lisaaJoukkoJoukkoon(a.toIntArray(), x);
        lisaaJoukkoJoukkoon(b.toIntArray(), x);
        return x;
    }
    
    private static void lisaaJoukkoJoukkoon(int[] taulu, IntJoukko x) {
        for (int i = 0; i < taulu.length; i++) {
            x.lisaaJoukkoonLuku(taulu[i]);
        }
    }
    
    private static void poistaJoukostaJoukko(int[] taulu, IntJoukko x) {
        for (int i = 0; i < taulu.length; i++) {
            x.poistaJoukostaLuku(i);
        }
    }

    public static IntJoukko leikkausJoukoista(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    x.lisaaJoukkoonLuku(bTaulu[j]);
                }
            }
        }
        return x;

    }

    public static IntJoukko erotusJoukoista(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        lisaaJoukkoJoukkoon(a.toIntArray(), x);
        poistaJoukostaJoukko(b.toIntArray(), x);
        return x;
    }
}
