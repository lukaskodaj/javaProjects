package hadik;

import oknaHry.RamHry;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class HadHra extends JPanel implements KeyListener, Serializable {

    private RamHry ramHry;
    private Snake snake;
    private Timer timer;
    private int casovac;
    private String level;
    private ArrayList<int[]> prekazky;
    private int posuvnikHraniceRychlosti;
    private int pocetPrekazok;
    private Image plocha;
    private Image prekazkaImage;

    public HadHra(RamHry paRamHry, String paLevel) {

        this.prekazky = new ArrayList<>();
        this.level = paLevel;
        this.setPreferredSize(new Dimension(900, 900));
        this.setFocusable(true);
        this.addKeyListener(this);
        this.ramHry = paRamHry;
        this.posuvnikHraniceRychlosti = 2;

        ImageIcon uvod = new ImageIcon(this.getClass().getResource("trava.png"));
        this.plocha = uvod.getImage();
        this.plocha = this.plocha.getScaledInstance(900, 900, Image.SCALE_SMOOTH);

        this.prekazkaImage = new ImageIcon(this.getClass().getResource("voda.png")).getImage();

        this.nadstavPrekazku();
        this.vytvorCasovac(this.casovac);

    }

    /* metoda kontroluje naraz hada do prekazky, ak had narazi do prekazky tak vrati true */
    public boolean narazDoPrekazky() {
        for (int i = 1; i < this.prekazky.size(); i++) {
            Rectangle okrajePrekazky = new Rectangle(this.prekazky.get(i)[0], this.prekazky.get(i)[1], this.prekazky.get(i)[2], this.prekazky.get(i)[3]);

            if (okrajePrekazky.intersects(this.snake.getOkraje())) {
                return true;
            }
        }

        return false;
    }

    /* nacitava prekazky, casovac a pocet prekazok z textovych suborov a uklada ich hodnoty do ArrayListu prekazky */
    public void nadstavPrekazku() {

        try {
  
            InputStream inp = this.getClass().getResourceAsStream(this.level + ".txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(inp));
            String riadok;
            
            while ((riadok = br.readLine()) != null) {
                String[] polozka = riadok.split("-");
                int[] pomPrekazka = new int[4];

                pomPrekazka[0] = Integer.parseInt(polozka[0]);
                pomPrekazka[1] = Integer.parseInt(polozka[1]);
                pomPrekazka[2] = Integer.parseInt(polozka[2]);
                pomPrekazka[3] = Integer.parseInt(polozka[3]);

                this.prekazky.add(pomPrekazka);
            }

            br.close();
        } catch (IOException ex) {
            System.out.println("subor sa nenasiel");
        }

        this.pocetPrekazok = this.prekazky.get(0)[0];
        this.casovac = this.prekazky.get(0)[1];
        this.snake = new Snake(this.pocetPrekazok, this);

    }

    /* inicializuje timer a nadstavi mu casovac z parametru a pusti timer */
    public void vytvorCasovac(int paCasovac) {
        this.timer = new Timer(paCasovac, new Posluchac());
        this.timer.start();

    }

    /* vykresluje prekazky a telo hada */
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.drawImage(this.plocha, 0, 0, null);

        for (int i = 1; i < this.prekazky.size(); i++) {
            g.drawImage(this.prekazkaImage, this.prekazky.get(i)[0], this.prekazky.get(i)[1], this.prekazky.get(i)[2], this.prekazky.get(i)[3], null);
        }

        this.snake.draw(g);

    }

    /* ActionListener ktory meni hodnoty hada, casovacu, zistuje koniec hry a zakazdim spusti metodu paintComponent */
    public class Posluchac implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            HadHra.this.snake.setKoniecHry(HadHra.this.narazDoPrekazky());

            HadHra.this.snake.move();

            if (HadHra.this.snake.getScore() > HadHra.this.posuvnikHraniceRychlosti) {
                HadHra.this.vypniTimer();
                HadHra.this.casovac -= 5;
                HadHra.this.vytvorCasovac(HadHra.this.casovac);
                HadHra.this.posuvnikHraniceRychlosti += 27;
            }

            HadHra.this.ramHry.setScore(HadHra.this.snake.getScore());
            HadHra.this.ramHry.setScoreStitok();

            repaint();

        }
    }

    /* zistuje ktore tlacidlo bolo stlacene(konkretne ktora sipka) a nadstavi smer ktorym ma had ist */
    public void keyPressed(KeyEvent e) {

        if (!this.snake.jePohybujuci() && !this.snake.isKoniecHry()) {
            if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                this.snake.setJePohybujuci(true);
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (this.snake.getSmerY() != 1) {
                this.snake.setSmerY(-1);
                this.snake.setSmerX(0);
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (this.snake.getSmerY() != -1) {
                this.snake.setSmerY(1);
                this.snake.setSmerX(0);
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (this.snake.getSmerX() != -1) {
                this.snake.setSmerX(1);
                this.snake.setSmerY(0);
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (this.snake.getSmerX() != 1) {
                this.snake.setSmerX(-1);
                this.snake.setSmerY(0);
            }
        }

    }

    /* implementovana metoda z KeyListener ale nepouzita */
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_S) {

            File save = new File("save.bin");

            try {
                if (!save.exists()) {
                    save.createNewFile();
                }
            } catch (IOException ex) {
                System.out.println("Subor sa neda vytvorit");
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(save));) {

                oos.writeInt(this.snake.getCastiTela().size());
                oos.writeInt(snake.getSmerX());
                oos.writeInt(snake.getSmerY());

                for (int i = 0; i < this.snake.getCastiTela().size(); i++) {
                    oos.writeInt(this.snake.getCastiTela().get(i).getX());
                    oos.writeInt(this.snake.getCastiTela().get(i).getY());
                }

                oos.writeInt(this.snake.getScore());

            } catch (IOException ex) {
                System.out.println("Chyba pri zapise objektu");
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_L) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.bin"));) {

                int velkostTela = ois.readInt();

                snake.setJePohybujuci(false);

                snake.setSmerX(ois.readInt());
                snake.setSmerY(ois.readInt());
                
                ArrayList<Telo> pomCastiTela = new ArrayList<>();

                for (int i = 0; i < velkostTela; i++) {
                    pomCastiTela.add(new Telo(ois.readInt(), ois.readInt()));
                }

                snake.setCastiTela(pomCastiTela);

                this.snake.setScore(ois.readInt());

            } catch (IOException ex) {
                System.out.println("Subor sa nenasiel");
                this.snake.vymazTeloHada();
            }

        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {

            if (this.snake.jePohybujuci()) {
                this.snake.setJePohybujuci(false);
            } else if (this.snake.jePohybujuci() == false) {
                this.snake.setJePohybujuci(true);
            }

        }
        
    }

    /* implementovana metoda z KeyListener ale nepouzita */
    public void keyTyped(KeyEvent e) {

    }

    /* zastavi timer */
    public void vypniTimer() {
        this.timer.stop();
    }

    /* vrati ArrayList prekazky(su tam ulozene hodnoty nacitanych prekazok) */
    public ArrayList<int[]> getPrekazky() {
        return this.prekazky;
    }

}
