package hadik;

import potrava.Jedlo;
import potrava.Portal;
import potrava.Jablka;
import potrava.Otrava;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import potrava.Mys;

public class Snake {
    
    private Random rand;
    private HadHra hadHra;
    private Telo hlava;
    private final Font koncovePismo;
    private final Font koncovePismo2;
    private final String koncovaHlaska;
    private Rectangle okrajeChvosta;
    
    private ArrayList<Telo> castiTela;
    private Jedlo[] potravy;
    
    private boolean jePohybujuci;
    private boolean koniecHry;
    private boolean nepovolenaVelkost;
    
    private int smerX;
    private int smerY;
    private final int zacVelkost = 4;
    private final int zacPozX = 140;
    private final int zacPozY = 140;
    private int pocetOtravy;
    private int score;

    //obrazky hada
    private final Image headUp;
    private final Image headDown;
    private final Image headLeft;
    private final Image headRight;
    private final Image teloImageYellow;
    private final Image teloImageRed;
    private final Image teloImagePurple;
    
    public Snake(int paPocetOtravy, HadHra paHadHra) {
        
        
        this.rand = new Random();
        this.hlava = new Telo(0, 0);
        this.hadHra = paHadHra;
        this.castiTela = new ArrayList<>();
        this.potravy = new Jedlo[paPocetOtravy];
        this.smerX = 0;
        this.smerY = 0;
        this.score = 0;
        this.jePohybujuci = false;
        this.nepovolenaVelkost = false;
        this.koniecHry = false;
        this.koncovePismo = new Font("Arial", Font.BOLD, 40);
        this.koncovePismo2 = new Font("Arial", Font.BOLD, 20);
        this.koncovaHlaska = "Game over";
        this.pocetOtravy = paPocetOtravy;
        
        this.headRight = new ImageIcon(this.getClass().getResource("HeadRight.png")).getImage();
        this.headLeft = new ImageIcon(this.getClass().getResource("HeadLeft.png")).getImage();
        this.headDown = new ImageIcon(this.getClass().getResource("HeadDown.png")).getImage();
        this.headUp = new ImageIcon(this.getClass().getResource("HeadUp.png")).getImage();
        this.teloImageYellow = new ImageIcon(this.getClass().getResource("Telo.png")).getImage();
        this.teloImageRed = new ImageIcon(this.getClass().getResource("TeloRed.png")).getImage();
        this.teloImagePurple = new ImageIcon(this.getClass().getResource("TeloPurple.png")).getImage();
        
        this.castiTela.add(new Telo(this.zacPozX, this.zacPozY));
        for (int i = 1; i < this.zacVelkost; i++) {
            this.castiTela.add(i, new Telo(this.zacPozX - (i * 20), this.zacPozY));
        }
        
        for (int i = 0; i < this.pocetOtravy - 2; i++) {
            this.potravy[i] = new Otrava(this, 20 * (this.rand.nextInt(0 + 45)), 20 * (this.rand.nextInt(0 + 45)));
        }
        this.potravy[this.pocetOtravy - 1] = new Jablka(this, 20 * (this.rand.nextInt(0 + 45)), 20 * (this.rand.nextInt(0 + 45)));
        if (this.pocetOtravy > 1) {
            this.potravy[this.pocetOtravy - 2] = new Portal(this, 20 * (this.rand.nextInt(0 + 45)), 20 * (this.rand.nextInt(0 + 45)));
        }
        if(this.pocetOtravy > 3) {
            this.potravy[this.pocetOtravy - 3] = new Mys(this, this.rand.nextInt(0 + 45), this.rand.nextInt(0 + 45));
        }
        
    }

    /* vykresluje jedlo, hlasku a skore pri konci hry a telo a hlavu hada(jedlo a hada vykresluje ako Image) */
    public void draw(Graphics g) {

        //Game Over
        for (int i = 0; i < this.castiTela.size(); i++) {
            
            if (i == 0) {
                this.okrajeChvosta = new Rectangle(-100, -100, 20, 20);
            } else {
                this.okrajeChvosta = new Rectangle(this.castiTela.get(i).getX(), this.castiTela.get(i).getY(), 20, 20);
            }
            
            if (((this.getOkraje().intersects(this.okrajeChvosta)) && this.castiTela.size() > 2) || this.nepovolenaVelkost || this.koniecHry) {
                this.jePohybujuci = false;
                g.setFont(this.koncovePismo);
                g.setColor(Color.red);
                g.drawString(this.koncovaHlaska, 350, 450);
                g.setColor(Color.WHITE);
                g.setFont(this.koncovePismo2);
                g.drawString("Dosiahol si score " + this.score, 365, 500);
                this.koniecHry = true;
                
            }
        }

        //Vykreslovanie Hada
        Image pomImage = this.headRight;
        if (this.smerY == -1 && this.smerX == 0) {
            pomImage = this.headUp;
        } else if (this.smerX == 1 && this.smerY == 0) {
            pomImage = this.headRight;
        } else if (this.smerX == -1 && this.smerY == 0) {
            pomImage = this.headLeft;
        } else if (this.smerY == 1 && this.smerX == 0) {
            pomImage = this.headDown;
        }
        
        for (int i = 1; i < this.castiTela.size(); i++) {
            if (i % 2 == 0) {
                g.drawImage(this.teloImageYellow, this.castiTela.get(i).getX(), this.castiTela.get(i).getY(), 20, 20, null);
            } else if (i % 5 == 0) {
                g.drawImage(this.teloImageRed, this.castiTela.get(i).getX(), this.castiTela.get(i).getY(), 20, 20, null);
            } else {
                g.drawImage(this.teloImagePurple, this.castiTela.get(i).getX(), this.castiTela.get(i).getY(), 20, 20, null);
            }
            
        }
        g.drawImage(pomImage, this.castiTela.get(0).getX() - 5, this.castiTela.get(0).getY() - 5, 30, 30, null);

        //Vykreslovanie potravy, otravy a portalu
        this.zistiZhodu();

        for (Jedlo kresJedlo : this.potravy) {
            
            kresJedlo.vykresliSa(g);
        }
        
    }

    /* posunie hlavu hada v zavislosti na smere v ktorom ide a zabezpecuje posun celeho hada urcenym smerom */
    public void move() {
        
        if (this.jePohybujuci && !this.koniecHry) {
            this.hlava = this.castiTela.get(0);
            if (this.hlava.getX() == 880 && this.smerX == 1 && this.smerY == 0) {
                this.hlava.setX(-20);
            } else if (this.hlava.getX() == 0 && this.smerX == -1 && this.smerY == 0) {
                this.hlava.setX(920);
            } else if (this.hlava.getY() == 0 && this.smerY == -1 && this.smerX == 0) {
                this.hlava.setY(920);
            } else if (this.hlava.getY() == 880 && this.smerY == 1 && this.smerX == 0) {
                this.hlava.setY(-20);
            } else {
                this.hlava = this.castiTela.get(0);
            }

            //Telo chvKoniec = castiTela.get(castiTela.size() - 1);
            Telo novaHlava = new Telo(this.hlava.getX() + this.smerX * 20, this.hlava.getY() + this.smerY * 20);

            /*Zahodím poslednú pozíciu v castiTela a nahradím ju prvkom s pozíciou predtým, tak to spravím až do začiatku a na prvý prvok priradím hlavu s posunutou pozíciou,
              tým pádom sa dá preč článok s hodnotou z ktorej som sa chcel posunúť ďalej a na začiatok sa pridá článok s hodnotou ktorá je ďalej určená podľa smeru šípky ktorú,
              som stlačil, toto všetko sa prekreslí podľa určených milisekúnd a vznikne pohyb(len posuniem všetky hodnoty v ArrayListe o index ďalej a na jeho prvý index pridám
              nový článok tela s posunutou hodnotou podľa smeru)
             */
            for (int i = this.castiTela.size() - 1; i >= 1; i--) {
                
                this.castiTela.set(i, this.castiTela.get(i - 1));
                
            }
            this.castiTela.set(0, novaHlava);
            
        }
        
    }

    /* zistuje zhodu jedla s hadom a jedla s prekazkou a ak sa vykresli v ich zhode tak zabezpeci zmenu suradnic jedla a zistuje aj dotyk hlavy hada s potravou */
    public boolean zistiZhodu() {
        
        for (Jedlo kresJedlo : this.potravy) {
            
            if (kresJedlo.eatingFood()) {
                kresJedlo.posunJedlo();
                kresJedlo.vykonajZmenu();
                kresJedlo.upravScore();
            }
            
            while (kresJedlo.wrongDraw()) {
                kresJedlo.posunJedlo();
            }
            
        }
        
        return true;
        
    }

    /* vrati hodnotu X-oveho smeru */
    public int getSmerX() {
        return this.smerX;
    }

    /* nadstavi hodnotu X-oveho smeru */
    public void setSmerX(int smerX) {
        this.smerX = smerX;
    }

    /* vrati hodnotu Y-oveho smeru */
    public int getSmerY() {
        return this.smerY;
    }

    /* nadstavi hodnotu Y-oveho smeru */
    public void setSmerY(int smerY) {
        this.smerY = smerY;
    }

    /* vrati X-ovu suradnicu polohy hada */
    public int getX() {
        return this.castiTela.get(0).getX();
    }

    /* vrati Y-ovu suracnicu polohy hada */
    public int getY() {
        return this.castiTela.get(0).getY();
    }
    
    public void setX(int X) {
        this.castiTela.get(0).setX(X);
    }
    
    public void setY(int Y) {
        this.castiTela.get(0).setY(Y);
    }

    /* vrati boolean urcujuci pohyb hada */
    public boolean jePohybujuci() {
        return this.jePohybujuci;
    }

    /* nadstavi boolean urcujuci pohyb hada */
    public void setJePohybujuci(boolean jePohybujuci) {
        this.jePohybujuci = jePohybujuci;
    }

    /* vrati okraje hlavy hada */
    public Rectangle getOkraje() {
        return new Rectangle(this.castiTela.get(0).getX(), this.castiTela.get(0).getY(), 20, 20);
    }

    /* vrati aktualne skore */
    public int getScore() {
        return this.score;
    }

    /* nadstavi skore */
    public void setScore(int score) {
        this.score = score;
    }

    /* vrati hodnotu urcujucu koniec hry */
    public boolean isKoniecHry() {
        return this.koniecHry;
    }

    /* nadstavi hodnotu urcujucu koniec hry */
    public void setKoniecHry(boolean koniecHry) {
        this.koniecHry = koniecHry;
    }

    /* prida novu cast tela do arrayListu */
    public void pridajCastTela(Telo telo) {
        this.castiTela.add(telo);
    }

    /* vrati ArrayList casti tela hada */
    public ArrayList<Telo> getCastiTela() {
        return this.castiTela;
    }

    /* nadstavi hodnotu nepovolenej velkosti hada ktora sa pouziva ak je had mensi ak hlava(jeden clanok) */
    public void setNepovolenaVelkost(boolean nepovolenaVelkost) {
        this.nepovolenaVelkost = nepovolenaVelkost;
    }

    /* vrati pouzivanu instanciu tried HadHra */
    public HadHra getHadHra() {
        return this.hadHra;
    }
    
    public void vymazTeloHada() {
        for(int i = 0; i < castiTela.size(); i++) {
            this.castiTela.remove(i);
        }
    }

    public void setCastiTela(ArrayList<Telo> castiTela) {
        this.castiTela = castiTela;
    }
    
}
