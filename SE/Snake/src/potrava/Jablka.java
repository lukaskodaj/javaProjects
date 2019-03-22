package potrava;

import hadik.Snake;
import hadik.Telo;
import javax.swing.ImageIcon;

public class Jablka extends Jedlo {

    public Jablka(Snake paSnake, int x, int y) {
        super(x, y, 9, paSnake);
        this.setDruhPotravy(new ImageIcon(this.getClass().getResource("jablko.png")).getImage());
    }

    /** zvacsi hada o jednu cast **/
    @Override
    public void vykonajZmenu() {
        int pomX = this.getSnake().getCastiTela().get(this.getSnake().getCastiTela().size() - 1).getX();
        int pomY = this.getSnake().getCastiTela().get(this.getSnake().getCastiTela().size() - 1).getY();
        this.getSnake().pridajCastTela(new Telo(pomX, pomY));
    }

}
