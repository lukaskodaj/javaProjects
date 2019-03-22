/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floydalgorithm;

/**
 *
 * @author Lukáš
 */
public class Spusti {

    public static void main(String[] args) {
        FloydAlgorithm alg = new FloydAlgorithm();
        alg.fillMatrix();
        alg.algorithm();
        alg.writeResult();
    }
}
