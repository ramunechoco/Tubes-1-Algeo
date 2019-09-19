import java.util.Scanner;

public class PL {

    // {** CONSTANT **}
    static final int IdxMin = 1;
    static final int IdxMax = 100;

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

    static PL StringToPL(String StrPL, PL Pers) {

        String[] Arr = StrPL.split("");
        // Merubah string ke dalam bentuk array

        int i = 1;
        while ( i < Arr.length ) {
            while ( !Arr[i].equals("-") && i < Arr.length ) {
                i++;
                if ( i == Arr.length) {
                    break;
                }
            }

            if ( i < Arr.length ) {
                if ( Arr[i].equals("-") && !Arr[i-1].equals("+") && !Arr[i-1].equals("=") ) {
                    StrPL = "";
                    for ( int j = 0; j < i; j++ ) {
                        StrPL += Arr[j];
                    }
    
                    StrPL += "+";
    
                    for ( int j = i; j < Arr.length; j++ ) {
                        StrPL += Arr[j];
                    }
                }
            }

            Arr = StrPL.split("");

            i++;
        }
        
        Arr = StrPL.split("[+=]");
        for ( int j = 0; j < Arr.length - 2; j++ ) {
            String[] Arr2 = Arr[j].split("x");
            Pers.a[Integer.parseInt(Arr2[1])] = Integer.parseInt(Arr2[0]);
        }
        
        return Pers;
        
    }
    
    // Prekondisi : persamaan valid
    // untuk persamaan ax1+ax2+....+axn = b
    // merubah persamaan dalam bentuk string
    // ke bentuk persamaan linier PL
    // mengembalikan persamaan dalam bentuk PL


    // {** SELEKTOR **}

    static int GetFirstIdx(PL Pers) {
        return IdxMin;
    }
    // Perkondisi : PL tidak kosong
    // Mengembalikan index pertama yang terdefinisi

    static int GetLastIdx(PL Pers) {
        return Pers.Na;
    }
    // Prekondisi : PL tidak kosong
    // Mengembalikan index terakhir yang terdefinisi

    static int GetB(PL Pers) {
        return Pers.b;
    }
    // Prekondisi : Pers terdefinisi
    // untuk persamaan ax1+ax2+....+axn = b
    // mengembalikan nilai b pada persamaan 

    static int[] GetA(PL Pers) {
        return Pers.a;
    }
    // Prekondisi : Pers terdefinisi
    // untuk persamaan ax1+ax2+....+axn = b
    // mengembalikan nilai a ke-1 s/d a ke-n
    // dalam bentuk array dengan nilai pangkat
    // sebagi indeksnya

    static int GetAn(PL Pers,int Pow) {
        return Pers.a[Pow];
    }
    // Prekondisi : Pers,Pow terdefinisi
    // mengembalikan nilai a ke-n pada persamaan

	static int GetNa(PL Pers) {
	    return Pers.Na;
    }
    // Prekondisi : Pers terdefinisi
    // mengembalikan banyaknya a pada persamaan 
    // (pangkat tertinggi dari 1 s/d n)

    // {** BACA dan TULIS **}

    // static PL BacaPL(PL Pers) {
    //     Scanner Input = new Scanner(System.in);

    //     String StrSPL = Input.nextLine();
    //     Pers = StringToPL(StrSPL, Pers);
    //     return Pers;
    // }
    // I.S : Pers Sembarang
    // F.S : Pers terdefinisi

    static void TulisPL(PL Pers) {

        if ( GetAn(Pers,GetLastIdx(Pers)) != 0 ) {
            System.out.print(GetAn(Pers,GetLastIdx(Pers)) + "x^" + GetLastIdx(Pers));
        }

        int Idx = GetLastIdx(Pers)-1;

        while ( Idx > GetFirstIdx(Pers) ) {
            
            if ( GetAn(Pers,Idx) != 0 ) {
                if (GetAn(Pers,Idx) > 0) {
                    System.out.print("+");
                }
                System.out.print(GetAn(Pers,Idx) + "x^" + Idx);
            }
            Idx--;
        }

        System.out.print("=" + GetB(Pers) + "\n");
        
    }
    // I.S : Pers terdefinisi
    // F.S : Menuliskan Pers ke layar

    public static void main(String[] args) {
        PL Pers = new PL();
        Pers = StringToPL("2x=-10", Pers);
    }
}