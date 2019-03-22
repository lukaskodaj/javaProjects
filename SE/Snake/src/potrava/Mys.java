/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potrava;

import hadik.Snake;
import hadik.Telo;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author Lukáš
 */
public class Mys extends Jedlo {

    Timer timer;

    public Mys(Snake paSnake, int paX, int paY) {
        super(paX, paY, 18, paSnake);
        this.setDruhPotravy(new ImageIcon(this.getClass().getResource("mouse.png")).getImage());
        timer = new Timer(700, new Listener());
        timer.start();
    }

    @Override
    public void vykonajZmenu() {

        for (int i = 0; i < 2; i++) {
            int pomX = this.getSnake().getCastiTela().get(this.getSnake().getCastiTela().size() - 1).getX();
            int pomY = this.getSnake().getCastiTela().get(this.getSnake().getCastiTela().size() - 1).getY();
            this.getSnake().pridajCastTela(new Telo(pomX, pomY));
        }

    }

    public void vykresliSa(Graphics g) {
        g.drawImage(this.getDruhPotravy(), this.getX()-5, this.getY()-5, 30, 30, null);
    }
    
    public void pohybujJedlo() {

        if (this.getX() > 40 && this.getX() < 860 && this.getY() > 40 && this.getY() < 860) {
            int nahodnySmer = this.getRand().nextInt(0 + 4);
            switch (nahodnySmer) {
                case 1:
                    this.setX(this.getX() + (20));
                    break;
                case 2:
                    this.setX(this.getX() + (-20));
                    break;
                case 3:
                    this.setY(this.getY() + (-20));
                    break;
                case 4:
                    this.setY(this.getY() + (20));
                    break;
                default:
                    break;
            }
        } else {
            if (this.getX() <= 40) {
                while (this.getX() <= 40) {
                    this.setX(this.getX() + 20);
                }
            } else if (this.getX() >= 860) {
                while (this.getX() >= 860) {
                    this.setX(this.getX() - 20);
                }
            } else if (this.getY() <= 40) {
                while (this.getY() <= 40) {
                    this.setY(this.getY() + 20);
                }
            } else if (this.getY() >= 860) {
                while (this.getY() >= 860) {
                    this.setY(this.getY() - 20);
                }
            }
        }

    }

    public class Listener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            pohybujJedlo();
        }
    }

}
