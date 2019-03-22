/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Lukáš
 */
public class Matrix {

    private Scanner scan;
    private int velkX;
    private int[][] matica;
    private int[][] finalnaMatica;
    private ArrayList<int[][]> zoznamMatic;
    private int koncPom;

    public Matrix() {
        scan = new Scanner(System.in);
        zoznamMatic = new ArrayList<>();
        koncPom = 1;

    }

    public void naplnMaticu() {
        System.out.println("Zadaj pocet vrcholov: ");
        velkX = scan.nextInt();

        while (velkX < 1) {
            System.out.println("!! Zadaj Pocet vrcholov vacsi ako 0 !!");
            velkX = scan.nextInt();
        }
        matica = new int[velkX][velkX];
        System.out.println();
        System.out.println("Zadaj hodnoty matice pre prvy riadok: ");
        for (int i = 0; i < velkX; i++) {
            for (int j = 0; j < velkX; j++) {

                matica[i][j] = scan.nextInt();

            }
            if (i == velkX - 1) {
                break;
            } else {
                System.out.println("Zadaj hodnoty pre " + (i + 2) + " riadok: ");
            }
        }

        zoznamMatic.add(matica);

        System.out.println();
        System.out.println("* Hodnoty su zadane *");
        System.out.println();
    }

    public void vypisMaticu() {

        System.out.println("************************");
        System.out.println("Vstupna Matica:");
        for (int i = 0; i < velkX; i++) {
            for (int j = 0; j < velkX; j++) {
                System.out.print(matica[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("************************");
        System.out.println();
    }

    public void mocninaMatice() {
        int[][] pomMatica = new int[velkX][velkX];

        for (int c = 0; c < zoznamMatic.size(); c++) {

            if (koncPom == velkX - 1 || velkX < 2) {
                break;
            }

            int pomSucet = 0;

            for (int i = 0; i < velkX; i++) {
                for (int r = 0; r < velkX; r++) {
                    for (int s = 0; s < velkX; s++) {
                        pomSucet = pomSucet + (zoznamMatic.get(c)[i][s] * matica[s][r]);

                        if (pomSucet > 0) {
                            pomSucet = 1;
                        } else {
                            pomSucet = 0;
                        }

                        if (s == velkX - 1) {
                            pomMatica[i][r] = pomSucet;
                            pomSucet = 0;
                        }
                    }
                }

            }

            koncPom++;

            zoznamMatic.add(new int[velkX][velkX]);
            for (int a = 0; a < velkX; a++) {
                for (int b = 0; b < velkX; b++) {
                    zoznamMatic.get(zoznamMatic.size() - 1)[a][b] = pomMatica[a][b];
                }
            }
        }

    }

    public void spocitajMatice() {
        finalnaMatica = new int[velkX][velkX];

        for (int i = 0; i < zoznamMatic.size(); i++) {
            for (int a = 0; a < velkX; a++) {
                for (int b = 0; b < velkX; b++) {
                    finalnaMatica[a][b] += zoznamMatic.get(i)[a][b];
                    if (finalnaMatica[a][b] > 0) {
                        finalnaMatica[a][b] = 1;
                    } else {
                        finalnaMatica[a][b] = 0;
                    }
                }
            }
        }

        //kontrola vyslednej matice a jej vypis
        int vysledneCislo = 0;
        System.out.println("Vysledna matica: ");
        for (int c = 0; c < velkX; c++) {
            for (int d = 0; d < velkX; d++) {
                System.out.print(finalnaMatica[c][d]);
                vysledneCislo += finalnaMatica[c][d];
            }
            System.out.println();
        }
        System.out.println("************************");

        //kontrola suvislosti podla vysledneho cisla
        boolean koniecCyklu = false;
        for (int e = 0; e < velkX; e++) {
            for (int f = 0; f < velkX; f++) {
                if (finalnaMatica[e][f] == 0) {
                    System.out.println("---------------------");
                    System.out.println("| Graf je nesuvisly |");
                    System.out.println("---------------------");
                    koniecCyklu = true;
                    break;
                }
            }
            if (koniecCyklu) {
                break;
            }
        }

        if (vysledneCislo == (velkX * velkX)) {
            System.out.println("-------------------");
            System.out.println("| Graf je suvisly |");
            System.out.println("-------------------");
        }

    }

    public static void main(String[] args) {
        Matrix matrix = new Matrix();
        matrix.naplnMaticu();
        matrix.vypisMaticu();
        matrix.mocninaMatice();
        matrix.spocitajMatice();

    }
}
