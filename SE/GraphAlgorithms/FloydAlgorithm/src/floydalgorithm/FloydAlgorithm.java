
package floydalgorithm;

import java.util.Scanner;


public class FloydAlgorithm {

    private Scanner scan;
    private int[][] distanceField;
    private int[][] pathField;
    private int sizeOfMatrix;

    public FloydAlgorithm() {
        
        scan = new Scanner(System.in);
        
    }
    
    public void fillMatrix() {
        
        System.out.println("Zadaj pocet vrcholov(velkost matice):");
        sizeOfMatrix = scan.nextInt();
        
        distanceField = new int[sizeOfMatrix][sizeOfMatrix];
        pathField = new int[sizeOfMatrix][sizeOfMatrix];
        
        System.out.println();
        System.out.println("Zadaj hodnoty pre maticu VZDIALENOSTI");
        for(int i = 0; i < sizeOfMatrix; i++) {
            System.out.println("Zadaj hodnoty pre " + (i+1) + " riadok:");
            for(int j = 0; j < sizeOfMatrix; j++) {
                
                distanceField[i][j] = scan.nextInt();
            }
            
        }
          
        System.out.println();
        System.out.println("Zadaj hodnoty pre maticu EXISTUJUCEJ CESTY");
        for(int i = 0; i < sizeOfMatrix; i++) {
            System.out.println("Zadaj hodnoty pre " + (i+1) + " riadok:");
            for(int j = 0; j < sizeOfMatrix; j++) {
                
                pathField[i][j] = scan.nextInt();
            }
        }
        
        //vypisanie vysledku
        System.out.println("Matica vzdialenosti ciest: ");
        for(int i = 0; i < sizeOfMatrix; i++) {
            for(int j = 0; j < sizeOfMatrix; j++) {
                System.out.print(distanceField[i][j] + " ");
            }
            System.out.println();
        }
        
        System.out.println("Matica existujucich ciest: ");
        for(int i = 0; i < sizeOfMatrix; i++) {
            for(int j = 0; j < sizeOfMatrix; j++) {
                System.out.print(pathField[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public void algorithm() {
        for(int k = 0; k < sizeOfMatrix; k++) {
            for(int i = 0; i < sizeOfMatrix; i++) {
                for(int j = 0; j < sizeOfMatrix; j++) {
                    if(distanceField[i][j] > distanceField[i][k] + distanceField[k][j]) {
                        distanceField[i][j] = distanceField[i][k] + distanceField[k][j];
                        pathField[i][j] = pathField[k][j];
                    }
                }
            }
        }
    }
    
    public void writeResult() {
        
        
        System.out.println();
        System.out.println("---------------------------------------------");
        System.out.println("* VÝSLEDOK *");
        //su v nej zapisane vzdialenosti najkratsich ciest medzi dvomi(z bodu ktory je oznaceny riadkovym indexom do bodu oznaceneho stlpcovym indexom)
        System.out.println("Matica vzdialenosti ciest: ");
        for(int i = 0; i < sizeOfMatrix; i++) {
            for(int j = 0; j < sizeOfMatrix; j++) {
                System.out.print(distanceField[i][j] + " ");
            }
            System.out.println();
        }
        
        //su v nej zapisane cesty medzi dvomi bodmy(z bodu ktory je oznaceny riadkovym indexom do bodu oznaceneho stlpcovym indexom)
        //konkretna cesta je cesta v zapisana v riadku(zaciatok je bod ktory je ako riadkovy index a nasledujuci bod je dalsie cislo z toho riadka)
        System.out.println("Matica existujucich najkratsich ciest: ");
        for(int i = 0; i < sizeOfMatrix; i++) {
            for(int j = 0; j < sizeOfMatrix; j++) {
                System.out.print(pathField[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------------------------------");
    }
    
}
