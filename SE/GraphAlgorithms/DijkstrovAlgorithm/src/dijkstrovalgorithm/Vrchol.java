/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstrovalgorithm;

import java.util.ArrayList;

/**
 *
 * @author Lukáš
 */
public class Vrchol {

    private String nazov;
    private int dlzkaCesty;
    private String vrcholPrichodu;
    private ArrayList<String> bodyCesty;
    
    
    public Vrchol() {
        bodyCesty = new ArrayList<>();
        nazov = "-";
        dlzkaCesty = 9999;
        vrcholPrichodu = "-";
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(char nazov) {
        this.nazov = "" + nazov;
    }

    public int getDlzkaCesty() {
        return dlzkaCesty;
    }

    public void setDlzkaCesty(int dlzkaCesty) {
        this.dlzkaCesty = dlzkaCesty;
    }

    public String getVrcholPrichodu() {
        return vrcholPrichodu;
    }

    public void setVrcholPrichodu(String vrcholPrichodu) {
        this.vrcholPrichodu = vrcholPrichodu;
    }
    
    public void pridajBod(String bod) {
        bodyCesty.add(bod);
    }

    public ArrayList<String> getBodyCesty() {
        return bodyCesty;
    }
    
    public void vymazBody() {
        for(int i = 0; i < bodyCesty.size(); i++) {
            bodyCesty.remove(i);
        }
        
    }

}
