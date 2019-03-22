/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstrovalgorithm;

/**
 *
 * @author Lukáš
 */
public class Spusti {

    public static void main(String[] args) {
        System.out.println("* Spustil sa Dijkstrov algoritmus *");
        System.out.println();
        DijksAlg alg = new DijksAlg();
        alg.vytvorVrcholy();
        alg.vytvorHrany();
        alg.vypisVrcholY();
        alg.vypisHrany();
        alg.algoritmus();
        
    }
    
}
