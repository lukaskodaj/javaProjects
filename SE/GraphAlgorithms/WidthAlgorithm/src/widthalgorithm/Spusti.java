/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package widthalgorithm;

/**
 *
 * @author Lukáš
 */
public class Spusti {
    
    public static void main(String[] args) {
        WidthAlgorithm alg = new WidthAlgorithm();
        alg.enterEdgesAndVertex();
        alg.showEdgesAndVertex();
        alg.algorithm();
        alg.writeResult();
    }
    
}
