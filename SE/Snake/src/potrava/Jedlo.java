package potrava;

import hadik.Snake;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

public abstract class Jedlo {

    private int x;
    private int y;
    private int posunScore;
    private final int dlzkaHrany;
    private Random rand;
    private Snake snake;
    private Image druhPotravy;

    public Jedlo(int paX, int paY, int paPosunScore, Snake paSnake) {
        this.rand = new Random();
        this.snake = paSnake;
        this.posunScore = paPosunScore;
        this.x = paX;
        this.y = paY;
        this.dlzkaHrany = 20;

    }

    /** zisti ci had zjedol jedlo **/
    public boolean eatingFood() {

        if (this.snake.getOkraje().intersects(this.getOkrajeJedla())) {
            return true;
        }
        return false;
        
    }

    /** zisti ci sa jedlo nevykreslilo na mieste prekazky alebo hada **/
    public boolean wrongDraw() {

        for (int i = 1; i < this.snake.getCastiTela().size(); i++) {
            Rectangle okrajeHadika = new Rectangle(this.snake.getCastiTela().get(i).getX(), this.snake.getCastiTela().get(i).getY(), 20, 20);

            for (int j = 1; j < this.snake.getHadHra().getPrekazky().size(); j++) {
                int[] pomPole = new int[4];
                pomPole[0] = this.snake.getHadHra().getPrekazky().get(j)[0];
                pomPole[1] = this.snake.getHadHra().getPrekazky().get(j)[1];
                pomPole[2] = this.snake.getHadHra().getPrekazky().get(j)[2];
                pomPole[3] = this.snake.getHadHra().getPrekazky().get(j)[3];
                Rectangle okrajePrekazky = new Rectangle(pomPole[0], pomPole[1], pomPole[2], pomPole[3]);

                if (this.getOkrajeJedla().intersects(okrajePrekazky) || okrajeHadika.intersects(okrajePrekazky)) {
                    return true;
                }
            }

        }

        return false;
    }

    /** abstraktna metoda ktora sa definuje v potomkoch a zabezpeci zmenu hada **/
    public abstract void vykonajZmenu();

    /** zmeni skore **/
    public void upravScore() {
        this.snake.setScore(this.snake.getScore() + this.posunScore);
    }

    /** zmeni polohu jedla **/
    public void posunJedlo() {
        this.x = 20 * (this.rand.nextInt(0 + 45));
        this.y = 20 * (this.rand.nextInt(0 + 45));
    }

    /* abstraktna metoda pre vykreslenie jedla */
    public void vykresliSa(Graphics g) {
        g.drawImage(this.druhPotravy, this.x, this.y, 20, 20, null);
    }

    /* setter pre nadstavenie suradnice X jedla */
    public void setX(int x) {
        this.x = x;
    }

    /* setter pre nadstavenie surednice Y jedla */
    public void setY(int y) {
        this.y = y;
    }

    /* getter pre ziskanie suradnice X jedla */
    public int getX() {
        return this.x;
    }

    /* getter pre ziskanie suradnice Y jedla */
    public int getY() {
        return this.y;
    }

    /* getter pre ziskanie dlzky hrany jedla */
    public int getDlzkaHrany() {
        return this.dlzkaHrany;
    }

    /* vrati random */
    public Random getRand() {
        return this.rand;
    }

    /* vrati okraje jedla */
    public Rectangle getOkrajeJedla() {
        return new Rectangle(this.x, this.y, this.dlzkaHrany, this.dlzkaHrany);
    }

    /* vrati aktualnu instanciu triedy snake */
    public Snake getSnake() {
        return this.snake;
    }

    /* vrati hodnotu posunu skore ktora zavisi na druhu jedla */
    public int getPosunScore() {
        return this.posunScore;
    }

    /* nadstavy obrazok ktory sa vykresluje podla druhu potravy */
    public void setDruhPotravy(Image druhPotravy) {
        this.druhPotravy = druhPotravy;
    }

    public Image getDruhPotravy() {
        return druhPotravy;
    }

    
    
}
