/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eulersalgorithm;

/**
 *
 * @author Lukáš
 */
public class Vertex {
    
    private char name;

    public Vertex(char paName) {
        this.name = paName;
    }

    public char getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }
    
}
