package oknaHry;

import hadik.HadHra;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RamHry extends JFrame {

    private HadHra hadik;
    private String level;
    private JPanel scorePanel;
    private JLabel scoreStitok;
    private JLabel warnPlayLabel;
    private Font scorePismo;
    private JButton menuButton;
    private int score;
    private Image ikonaImage;

    public RamHry(String paLevel) {

        this.level = paLevel;
        this.setTitle("Snake " + paLevel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.hadik = new HadHra(this, this.level);
        hadik.setCursor(new Cursor(0));
        
        this.scorePismo = new Font("Arial", Font.BOLD, 15);
        this.ikonaImage = new ImageIcon(this.getClass().getResource("jablko.png")).getImage();
        this.setIconImage(this.ikonaImage);
        this.vytvorPanelScore();

        Container pane = this.getContentPane();
        pane.add(this.hadik, BorderLayout.CENTER);
        pane.add(this.scorePanel, BorderLayout.PAGE_START);
        
        
        this.setVisible(true);
        this.pack();

    }

    /* metoda na vytvorenie panelu a vlozi do neho button pre navrat do menu a stitok na vykreslovanie skore */
    public void vytvorPanelScore() {
        this.warnPlayLabel = new JLabel(" ");
        this.warnPlayLabel.setBackground(Color.WHITE);
        this.warnPlayLabel.setFont(this.scorePismo);
        this.warnPlayLabel.setText("Stlac tab a hraj :)         ");
        
        this.menuButton = new JButton("Menu");
        this.menuButton.setBackground(Color.lightGray);
        this.menuButton.addActionListener(new BackPosluchac());
        
        this.scoreStitok = new JLabel(" ");
        this.scorePanel = new JPanel();
        this.scorePanel.add(this.warnPlayLabel);
        this.scorePanel.add(this.menuButton);
        this.scorePanel.setPreferredSize(new Dimension(900, 30));
        this.scorePanel.setBackground(Color.WHITE);
        this.scoreStitok.setFont(this.scorePismo);
        this.scoreStitok.setText("Score " + 0);
        this.scoreStitok.setForeground(Color.red);
        this.scorePanel.add(this.scoreStitok);
        
        
        

    }

    /* ActionListener pre button menu ktory vytvori nove menu a zatvori aktualny ramHry */
    public class BackPosluchac implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
            RamMenu ramMenu;
            ramMenu = new RamMenu();
            ramMenu.setVisible(true);

            dispose();

        }
    }

    /* setter pre skore */
    public void setScore(int paScore) {
        this.score = paScore;
    }

    /* nadstavi text na stitok pre skore */
    public void setScoreStitok() {
        this.scoreStitok.setText("Score " + this.score);
    }

}
