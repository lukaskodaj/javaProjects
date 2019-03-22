
package cheapskeletfinder;

public class Vrchol {
    
    private String nazov;
    private int cisloVrchola;
    
    
    public Vrchol() {
        this.cisloVrchola = 0;
        this.nazov = "-";      
    }

    public int getCisloVrchola() {
        return cisloVrchola;
    }

    public void setCisloVrchola(int cisloVrchola) {
        this.cisloVrchola = cisloVrchola;
    }
    
    public String getNazov() {
        return nazov;
    }

    public void setNazov(char nazov) {
        this.nazov = "" + nazov;
    }
    
}
