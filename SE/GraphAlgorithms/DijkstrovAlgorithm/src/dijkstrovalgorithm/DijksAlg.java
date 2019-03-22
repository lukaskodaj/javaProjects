package dijkstrovalgorithm;

import java.util.ArrayList;
import java.util.Scanner;

public class DijksAlg {

    Scanner scan;
    private Vrchol[] poleVrch;
    private Hrana[] poleHran;
    private int pocetVrcholov;
    private int pocetHran;

    public DijksAlg() {
        scan = new Scanner(System.in);
    }

    public void vytvorVrcholy() {
        System.out.println("Zadaj pocet vrcholov: ");
        pocetVrcholov = scan.nextInt();
        poleVrch = new Vrchol[pocetVrcholov];

        for (int i = 0; i < pocetVrcholov; i++) {
            poleVrch[i] = new Vrchol();
            poleVrch[i].setNazov((char) (i + 65));
        }

        poleVrch[0].setDlzkaCesty(0);

        for (int i = 1; i < pocetVrcholov; i++) {
            poleVrch[i].setDlzkaCesty(9999);
        }
    }

    public void vytvorHrany() {

        System.out.println("Zadaj pocet hran:");
        pocetHran = scan.nextInt();
        pocetHran = pocetHran;
        poleHran = new Hrana[pocetHran];

        for (int i = 0; i < pocetHran; i++) {
            poleHran[i] = new Hrana();

            System.out.println("Zadaj zaciatok hrany č. " + (i + 1));
            poleHran[i].setZaciatok(scan.next());
            System.out.println("Zadaj koniec hrany č. " + (i + 1));
            poleHran[i].setKoniec(scan.next());
            System.out.println("Zadaj dlzku hrany č. " + (i + 1));
            poleHran[i].setDlzkaHrany(scan.nextInt());

        }

        for (Hrana hrana : poleHran) {
            for (Vrchol vrchol : poleVrch) {
                if (hrana.getKoniec().equals(vrchol.getNazov())) {
                }
            }

        }

    }

    public void vypisHrany() {
        System.out.println("Hrany: ");
        for (Hrana hrana : poleHran) {
            System.out.println("H(" + hrana.getZaciatok() + ", " + hrana.getKoniec() + ") (" + hrana.getDlzkaHrany() + ")");
        }
    }

    public void vypisVrcholY() {
        System.out.println("Vrcholy: ");
        for (Vrchol vrchol : poleVrch) {
            System.out.println("V(" + vrchol.getNazov() + ") (" + vrchol.getDlzkaCesty() + ")");
        }
    }

    public void algoritmus() {

        int pom = 0;
        int pom1 = 0;

        for (int i = 0; i < pocetVrcholov; i++) {
            for (int j = 0; j < pocetHran; j++) {
                pom = 0;
                pom1 = 0;
                
                //ak sa vrchol rovna zaciatku hrane
                if (poleVrch[i].getNazov().equals(poleHran[j].getZaciatok())) {

                    //do pom sa ulozi zatial najkratsia cesta do toho vrchola + dĺžka hrany kam smeruje
                    pom = poleVrch[i].getDlzkaCesty() + poleHran[j].getDlzkaHrany();

                    for (Vrchol vrchol : poleVrch) {
                        
                        //najde sa vrchol ktorý sa rovná koncu tejto hrany
                        if (vrchol.getNazov().equals(poleHran[j].getKoniec())) {
                            
                            //ulozi sa tam dlzka zatial najkratšej cesty do toho vrchola
                            pom1 = vrchol.getDlzkaCesty();
                        }
                    }

                    //ak je cesta do toho vrchola(pom1) dlhšia ako nova cesta do toho vrchola(pom)
                    if (pom1 > pom) {

                        for (Vrchol vrchol1 : poleVrch) {
                            if (vrchol1.getNazov().equals(poleHran[j].getKoniec())) {
                                ArrayList<String> pomAray = new ArrayList<>();
                                vrchol1.setDlzkaCesty(poleVrch[i].getDlzkaCesty() + poleHran[j].getDlzkaHrany());
                                vrchol1.setVrcholPrichodu(poleVrch[i].getNazov());
                                
                            }

                        }
                    }
                }
            }
            
            //po prvom vrchole sa nadstavi na do prveho vrcholu nekonečno
            if(i == 0) {
                poleVrch[i].setDlzkaCesty(9999);
            }
        }

        System.out.println();
        System.out.println("Najkratšia cesta: ");
        for (Vrchol vrchol : poleVrch) {
            if(vrchol.getNazov().equals("A")) {
                if(vrchol.getDlzkaCesty() == 9999) {
                    vrchol.setDlzkaCesty(0);
                }
            }
            System.out.println("do " + vrchol.getNazov() + " má dlžku " + vrchol.getDlzkaCesty());
        }
        
    }

}
