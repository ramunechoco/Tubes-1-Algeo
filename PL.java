import java.util.Scanner;

public class PL {

    // {** CONSTANT **}
    final int IdxMin = 1;
    final int IdxMax = 100;

    protected int Na;
    protected int[] a;
    protected int b;

    // {** KONSTRUKTOR** }
    public PL() {
        Na = 0;
        b = 0;
        a = new int[IdxMax+1];
    }
    // Menginisiasi Na, b, dan a
    // dijalankan setiap membuat objek baru

    public void StringToPL(String StrPL, PL Pers) {

        String[] arrStrPL = StrPL.split("");
        // Merubah string ke dalam bentuk array

        int aMax = 1;
        // Variabel untuk menyimpan nilai pangakt tertinggi

        String B = "";

        if (!arrStrPL[0].equals("-")) {
            StrPL = "+" + StrPL;
            arrStrPL = StrPL.split("");
        }
        // Menambahkan tanda didepan angka pertama

        int Idx = IdxMin;

        int IdxEqSign = Idx;
        while (!arrStrPL[IdxEqSign].equals("=")) {
            IdxEqSign++;
        }

        while (Idx < IdxEqSign) {

            String Koef = "";
            String Pows = "";
            // String untuk menyimpan nilai a dan pangkat

            int IdxPlusMin = Idx;
            while (IdxPlusMin < IdxEqSign && !arrStrPL[IdxPlusMin].equals("+") && !arrStrPL[IdxPlusMin].equals("-")) {
                IdxPlusMin++;
            }
            // Mencari tanda (+) atau (-)

            int IdxPow = Idx+1;
            while (!arrStrPL[IdxPow].equals("^") && IdxPow < IdxPlusMin-1) {
                IdxPow++;
            }
            // Mencari tanda (^)

            if ( arrStrPL[IdxPow].equals("^") ) {
                if ( arrStrPL[Idx].equals("x") ) {
                    Koef = "1";
                } else {
                    for ( int IdxKoef = Idx; IdxKoef < IdxPow-1; IdxKoef++) {
                        Koef += arrStrPL[IdxKoef];
                    }
                }

                for ( int IdxPows = IdxPow + 1; IdxPows < IdxPlusMin; IdxPows++ ) {
                    Pows += arrStrPL[IdxPows];
                }
            } else if (arrStrPL[IdxPow].equals("x")) {
                for ( int IdxKoef = Idx; IdxKoef < IdxPlusMin - 1; IdxKoef++ ) {
                    Koef += arrStrPL[IdxKoef];
                }
                Pows = "1";
            }

            System.out.println("Koef " + Koef);
            System.out.println("Pows " + Pows);
            // ----- Koefisien ------
            // Koefisiennya adalah angka dari index ke-Idx sampai tanda (^)
            // Jika tidak ada angka diantara tanda tersebut maka koefisiennya 1
            // ----- Pangkat -------
            // Jika terdapat tanda (^) maka nilai pangkat nya ada diantara tanda (^) dan (+) atau (-)
            // Jika tidak terdapat tanda (^) maka nilai pangkatnya adalah 1

            int cPow = Integer.parseInt(Pows);
            if (cPow > aMax) {
                aMax = cPow;
            }
            // Memperbarui nilai pangkat tertinggi

            if (arrStrPL[Idx-1].equals("-")) {
                Pers.a[cPow] = Integer.parseInt(Koef)*(-1);
            } else {
                Pers.a[cPow] = Integer.parseInt(Koef);
            }
            // Menginisiasi nilai array dengan koefisien sebagai elemen
            // dan index sebagai pangkatnya

            Idx = IdxPlusMin + 1;
            // Memulai perncarian dari elemen setelah tanda (+) atau (-)

        }

        for (int i = IdxEqSign+1; i < arrStrPL.length; i++) {
            B += arrStrPL[i];
        }

        Pers.b = Integer.parseInt(B);

        Pers.Na = aMax;
    }
    
    // Prekondisi : persamaan valid
    // untuk persamaan ax1+ax2+....+axn = b
    // merubah persamaan dalam bentuk string
    // ke bentuk persamaan linier PL
    // mengembalikan persamaan dalam bentuk PL


    // {** SELEKTOR **}

    private int GetFirstIdx() {
        return IdxMin;
    }
    // Perkondisi : PL tidak kosong
    // Mengembalikan index pertama yang terdefinisi

    private int GetLastIdx() {
        return Na;
    }
    // Prekondisi : PL tidak kosong
    // Mengembalikan index terakhir yang terdefinisi

    public int GetB() {
        return b;
    }
    // Prekondisi : Pers terdefinisi
    // untuk persamaan ax1+ax2+....+axn = b
    // mengembalikan nilai b pada persamaan 

    public int[] GetA() {
        return a;
    }
    // Prekondisi : Pers terdefinisi
    // untuk persamaan ax1+ax2+....+axn = b
    // mengembalikan nilai a ke-1 s/d a ke-n
    // dalam bentuk array dengan nilai pangkat
    // sebagi indeksnya

    public int GetAn(int Pow) {
        return a[Pow];
    }
    // Prekondisi : Pers,Pow terdefinisi
    // mengembalikan nilai a ke-n pada persamaan

	public int GetNa() {
	    return Na;
    }
    // Prekondisi : Pers terdefinisi
    // mengembalikan banyaknya a pada persamaan 
    // (pangkat tertinggi dari 1 s/d n)

    // {** BACA dan TULIS **}

    public void BacaPL(PL Pers) {
        Scanner Input = new Scanner(System.in);

        String StrSPL = Input.nextLine();
        StringToPL(StrSPL, Pers);

    }
    // I.S : Pers Sembarang
    // F.S : Pers terdefinisi

    public void TulisPL() {
        int Idx = GetLastIdx();

        while ( Idx > GetFirstIdx() + 1 ) {
            
            if ( GetAn(Idx) != 0 ) {
                System.out.print(GetAn(Idx) + "x^" + Idx);
                if (GetAn(Idx) > 0) {
                    System.out.print("+");
                }
            }
            Idx--;
        }
        System.out.print(GetAn(GetFirstIdx()) + "x=" + GetB() + "\n");
    }
    // I.S : Pers terdefinisi
    // F.S : Menuliskan Pers ke layar

    public static void main(String[] args) {
        PL Pers = new PL();
        Pers.BacaPL(Pers);
        Pers.TulisPL();
        for (int i = 0; i < Pers.GetNa(); i++) {
            System.out.println(Pers.a[i]);
        }
    }
}