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
public class Edge {

    private char StartName;
    private char LastName;

    public Edge(char StartName, char LastName) {
        this.StartName = StartName;
        this.LastName = LastName;
    }

    public char getStartName() {
        return StartName;
    }

    public void setStartName(char StartName) {
        this.StartName = StartName;
    }

    public char getLastName() {
        return LastName;
    }

    public void setLastName(char LastName) {
        this.LastName = LastName;
    }

}
