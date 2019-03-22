
package tarryalgorithm;


public class Edge {

    private char StartName;
    private char LastName;
    private boolean firstArrive;
    private boolean used;

    public Edge(char StartName, char LastName) {
        this.StartName = StartName;
        this.LastName = LastName;
        firstArrive = false;
        used = false;
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
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    
    
}
