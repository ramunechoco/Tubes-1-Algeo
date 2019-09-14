public class SPL {

    // {** CONSTANT **}
    final int IdxMin = 1;
    final int IdxMax = 100;

    protected int NPL;
    protected PL[] PLs;

    public SPL() {
        NPL = 0;
        PLs = new PL[100];
    }

    // {** SELEKTOR **}
    public int GetNPL(SPL SPers) {
        return SPers.NPL;
    }
    // Mengembalikan banyaknya persamaan pada SPL

    public PL[] GetPLs(SPL SPers) {
        return SPers.PLs;
    }
    // Mengembalikan persamaan pada SPL
    // dalam bentuk array

    public PL GetPL(SPL SPers, int n) {
        return SPers.PLs[n];
    }
    // Mengembalikan Persamaan ke-n pada SPL
}