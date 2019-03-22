/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafchecker;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Lukáš
 */
public class GrafChecker {

    Scanner scan;
    private ArrayList<Integer> vrcholy;

    private int[] vrchy;
    private int pocetVrcholovGrafu;

    public GrafChecker() {
        scan = new Scanner(System.in);
        vrcholy = new ArrayList<Integer>();

    }

    public void pridajVrcholy() {
        System.out.println("Zadaj počet vrcholov grafu: ");
        pocetVrcholovGrafu = scan.nextInt();
        vrchy = new int[pocetVrcholovGrafu];

        for (int i = 0; i < vrchy.length; i++) {
            System.out.print("Pridaj vrchol: ");
            vrchy[i] = scan.nextInt();
        }

    }

    public void Sort() {
        boolean zamena;

        do {
            zamena = false;
            for (int i = 0; i < vrchy.length - 1; i++) {
                if (vrchy[i] < vrchy[i + 1]) {
                    int pom = vrchy[i + 1];
                    vrchy[i + 1] = vrchy[i];
                    vrchy[i] = pom;
                    zamena = true;
                }
            }

        } while (zamena);
    }

    public void vypisVrcholy() {
        for (int i = 0; i < vrchy.length; i++) {
            System.out.print(vrchy[i]);
        }
    }

    public void valencnaPostupnost() {
        Sort();

        if (vrchy[0] < pocetVrcholovGrafu) {

            while (vrchy[0] > 0) {
                int[] pomPole = new int[pocetVrcholovGrafu - 1];

                for (int i = 1; i < vrchy.length; i++) {

                    pomPole[i - 1] = vrchy[i];

                }

                for (int i = 0; i < vrchy[0]; i++) {

                    pomPole[i] = (pomPole[i]) - 1;

                }

                int[] pomPole2 = new int[vrchy.length - 1];

                for (int i = 0; i < pomPole2.length; i++) {
                    pomPole2[i] = pomPole[i];
                }

                pocetVrcholovGrafu -= 1;

                vrchy = new int[pocetVrcholovGrafu];

                for (int i = 0; i < pomPole2.length; i++) {
                    vrchy[i] = pomPole2[i];
                }

                Sort();

            }

            if (vrchy[0] == 0) {
                System.out.println("Graf sa dá zostrojiť");
            } else if (vrchy[0] < 0) {
                System.out.println("Graf nie je možné zostrojiť");
            }

        } else {
            System.out.println("** Najväčší stupeň vrchola je vačší ako počet vrcholov **");
        }

    }

    public static void main(String[] args) {

        System.out.println("Zaciatok");
        GrafChecker ceki = new GrafChecker();
        ceki.pridajVrcholy();
        System.out.print("Neusporiadané vrcholy: ");
        ceki.vypisVrcholy();
        ceki.Sort();
        System.out.println();
        System.out.print("usporiadané vrcholy: ");
        ceki.vypisVrcholy();
        System.out.println();
        ceki.valencnaPostupnost();

    }

}
