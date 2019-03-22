/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depthalgorithm;

/**
 *
 * @author Lukáš
 */
public class Spusti {
    
    public static void main(String[] args) {
        DepthAlgorithm alg = new DepthAlgorithm();
        alg.showEdgesAndVertex();
        alg.algorithm();
        alg.writeResult();
    }
    
}
