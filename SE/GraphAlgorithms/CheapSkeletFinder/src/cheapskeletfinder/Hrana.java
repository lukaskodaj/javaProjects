
package cheapskeletfinder;


public class Hrana {
    private String zaciatok;
    private String koniec;
    private int dlzkaHrany;
    

    public Hrana() {
        zaciatok = "-";
        koniec = "-";
        dlzkaHrany = 0;
    }

    public String getZaciatok() {
        return zaciatok;
    }

    public void setZaciatok(String zaciatok) {
        this.zaciatok = zaciatok;
    }

    public String getKoniec() {
        return koniec;
    }

    public void setKoniec(String koniec) {
        this.koniec = koniec;
    }

    public int getDlzkaHrany() {
        return dlzkaHrany;
    }

    public void setDlzkaHrany(int dlzkaHrany) {
        this.dlzkaHrany = dlzkaHrany;

    }
    
}
