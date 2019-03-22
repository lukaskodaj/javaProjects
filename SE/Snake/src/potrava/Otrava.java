package potrava;

import hadik.Snake;
import javax.swing.ImageIcon;

public class Otrava extends Jedlo {

    public Otrava(Snake paSnake, int x, int y) {
        super(x, y, -9, paSnake);
        this.setDruhPotravy(new ImageIcon(this.getClass().getResource("jablkoCervik.png")).getImage());
    }

    /** zmensi hada o urcenu cast **/
    @Override
    public void vykonajZmenu() {
        if (this.getSnake().getCastiTela().size() > 3) {
            for (int a = 0; a < 3; a++) {
                this.getSnake().getCastiTela().remove(this.getSnake().getCastiTela().size() - 1);
            }
        } else {
            this.getSnake().setNepovolenaVelkost(true);
        }
    }

}
