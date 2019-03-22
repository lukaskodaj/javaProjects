package cheapskeletfinder;

import java.util.ArrayList;
import java.util.Scanner;

public class KruskalovAlgorithm {

    Scanner scan;
    private Vrchol[] poleVrch;
    private Hrana[] poleHran;
    private int pocetVrcholov;
    private int pocetHran;
    private ArrayList<Hrana> vysledok;

    public KruskalovAlgorithm() {
        scan = new Scanner(System.in);
        vysledok = new ArrayList<>();
    }

    public void vytvorVrcholy() {
        System.out.println("Zadaj pocet vrcholov: ");
        pocetVrcholov = scan.nextInt();
        poleVrch = new Vrchol[pocetVrcholov];

        for (int i = 0; i < pocetVrcholov; i++) {
            poleVrch[i] = new Vrchol();
            poleVrch[i].setNazov((char) (i + 65));
            poleVrch[i].setCisloVrchola(i + 1);
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
            System.out.println("V(" + vrchol.getNazov() + ") (" + vrchol.getCisloVrchola() + ")");
        }
    }

    public void usporiadaj() {

        boolean zamena;

        do {
            zamena = false;
            for (int i = 0; i < poleHran.length - 1; i++) {
                if (poleHran[i].getDlzkaHrany() > poleHran[i + 1].getDlzkaHrany()) {
                    Hrana pom = poleHran[i + 1];
                    poleHran[i + 1] = poleHran[i];
                    poleHran[i] = pom;
                    zamena = true;
                }
            }

        } while (zamena);
    }

    public void algorithm() {

        for (int i = 0; i < poleHran.length; i++) {

            int cisloZacVrchola = 0;
            int cisloKoncVrchola = 0;
            Vrchol zacVrchol = new Vrchol();
            Vrchol koncVrchol = new Vrchol();

            for (Vrchol vrchol : poleVrch) {
                if (vrchol.getNazov().equals(poleHran[i].getZaciatok())) {
                    cisloZacVrchola = vrchol.getCisloVrchola();
                    zacVrchol = vrchol;
                }
            }

            for (Vrchol vrchol : poleVrch) {
                if (vrchol.getNazov().equals(poleHran[i].getKoniec())) {
                    cisloKoncVrchola = vrchol.getCisloVrchola();
                    koncVrchol = vrchol;
                }
            }

            if (cisloZacVrchola == cisloKoncVrchola) {
                continue;
            } else {

                //vsetky vrcholy ktore maju cislo rovne tomu vacsiemu z tych dvoch porovnavanych vrcholov sa nadstavi cislo na to mensie z tych porovnavanych
                //vytvoria sa skupiny oznacene rovnakym cislom
                //hrana sa zapise do vysledku
                //toto zabezpeci ze vrcholy hrany sa uz pouzili a dalej sa nehlada s nimi hodnota(vsetko to ide postupne od najlacnejsej lebo je to od nej zoradene)
                //pre najdrahsiu kostru treba zoradit od najvacsieho po najmensie
                if (cisloZacVrchola < cisloKoncVrchola) {
                    for (Vrchol vrchol : poleVrch) {
                        if (vrchol.getCisloVrchola() == cisloKoncVrchola) {
                            vrchol.setCisloVrchola(cisloZacVrchola);
                        }
                    }
                } else {
                    for (Vrchol vrchol : poleVrch) {
                        if (vrchol.getCisloVrchola() == cisloZacVrchola) {
                            vrchol.setCisloVrchola(cisloKoncVrchola);
                        }
                    }
                }

                vysledok.add(poleHran[i]);

            }

        }

    }

    public void vypisVysledok() {
        System.out.println("Najlacnejsia kostra je: ");
        for (Hrana hrana : vysledok) {
            System.out.println("Hrana " + hrana.getZaciatok() + " " + hrana.getKoniec());
        }
    }

}
