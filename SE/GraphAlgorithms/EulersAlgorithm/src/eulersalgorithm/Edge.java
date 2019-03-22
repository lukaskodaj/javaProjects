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
public class Edge {
    
    private char StartName;
    private char LastName;
    private boolean firstArrive;
    private boolean OnceUsed;
    private boolean twiceUsed;

    public Edge(char StartName, char LastName) {
        this.StartName = StartName;
        this.LastName = LastName;
        firstArrive = false;
        OnceUsed = false;
        twiceUsed = false;
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

    public boolean isFirstArrive() {
        return firstArrive;
    }

    public void setFirstArrive(boolean firstArrive) {
        this.firstArrive = firstArrive;
    }

    public boolean isUsed() {
        return OnceUsed;
    }

    public void setUsed(boolean used) {
        this.OnceUsed = used;
    }

    public boolean isOnceUsed() {
        return OnceUsed;
    }

    public void setOnceUsed(boolean OnceUsed) {
        this.OnceUsed = OnceUsed;
    }

    public boolean isTwiceUsed() {
        return twiceUsed;
    }

    public void setTwiceUsed(boolean twiceUsed) {
        this.twiceUsed = twiceUsed;
    }
    
}
