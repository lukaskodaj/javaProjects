package potrava;

import hadik.Snake;
import javax.swing.ImageIcon;

public class Portal extends Jedlo {

    public Portal(Snake paSnake, int x, int y) {
        super(x, y, 0, paSnake);
        this.setDruhPotravy(new ImageIcon(this.getClass().getResource("ceresne.png")).getImage());
    }

    /** vykona presun suradnic hlavy hada na ine random miesto **/
    @Override
    public void vykonajZmenu() {
        this.getSnake().getCastiTela().get(0).setX(20 * (this.getRand().nextInt(0 + 45)));
        this.getSnake().getCastiTela().get(0).setY(20 * (this.getRand().nextInt(0 + 45)));     
    }

}
