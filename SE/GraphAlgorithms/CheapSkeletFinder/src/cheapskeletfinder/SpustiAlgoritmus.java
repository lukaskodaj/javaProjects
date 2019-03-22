/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cheapskeletfinder;

/**
 *
 * @author Lukáš
 */
public class SpustiAlgoritmus {
    
    public static void main(String[] args) {
        KruskalovAlgorithm alg = new KruskalovAlgorithm();
        alg.vytvorVrcholy();
        alg.vytvorHrany();
        alg.vypisVrcholY();
        alg.vypisHrany();
        alg.usporiadaj();
        alg.vypisHrany();
        alg.algorithm();
        alg.vypisVysledok();
    }
    
}
