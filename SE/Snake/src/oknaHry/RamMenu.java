package oknaHry;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;

public class RamMenu extends JPanel {

    private String aktualnyLevel;
    private String[] typyLevelov = {"Level 1", "Level 2", "Level 3", "Level 4", "Level 5", "Level 6", "Level 7"};

    private RamHry ramHry;
    private JButton start;
    private JComboBox levely;
    private Font startPismo;
    private Image uvodnyObrazok;
    private JFrame okno;
    private Color startColor;
    private final Image ikonaObrazok;

    public RamMenu() {

        this.okno = new JFrame();
        this.okno.setTitle("Snake menu");
        this.okno.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.okno.setResizable(false);

        //nadstavenie pozicie do stredu obrazovky
        this.okno.setLocationRelativeTo(null);
        double x = this.okno.getLocation().getX();
        double y = this.okno.getLocation().getY();
        int a = (int)x;
        int b = (int)y;
        this.okno.setLocation(a - 250, b - 250);

        ImageIcon uvod = new ImageIcon(this.getClass().getResource("Had.png"));
        this.uvodnyObrazok = uvod.getImage();
        this.uvodnyObrazok = this.uvodnyObrazok.getScaledInstance(520, 520, Image.SCALE_SMOOTH);

        ImageIcon icon = new ImageIcon(this.getClass().getResource("jablko.png"));
        this.ikonaObrazok = icon.getImage();

        this.startPismo = new Font("Arial", Font.BOLD, 20);
        this.startColor = new Color(0, 180, 0);
        this.start = new JButton("Play");
        this.start.setFont(this.startPismo);
        this.levely = new JComboBox(this.typyLevelov);
        this.levely.setFont(this.startPismo);
        this.start.setBackground(Color.WHITE);
        this.levely.setBackground(Color.WHITE);
        this.start.setForeground(this.startColor);
        this.setPreferredSize(new Dimension(500, 500));
        this.add(this.start);
        this.add(this.levely);
        this.setLayout(null);

        this.start.setBounds(30, 96, 80, 38);
        this.levely.setBounds(115, 96, 95, 38);
        this.levely.addActionListener(new ComboPosluchac());
        this.levely.setSelectedIndex(0);
        this.start.addActionListener(new PosluchacButtonu());
        okno.setCursor(new Cursor(1));
        this.okno.add(this);
        this.okno.setIconImage(this.ikonaObrazok);
        this.okno.pack();
        this.okno.setVisible(true);

    }

    /* metoda na vykreslenie grafiky pre menu */
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.drawImage(this.uvodnyObrazok, 0, 0, this);

    }

    /* ActionListener pre Combo, pocuva udalosti Comba */
    public class ComboPosluchac implements ActionListener {

        public void actionPerformed(ActionEvent a) {
            if (a.getSource() == RamMenu.this.levely) {
                JComboBox pom = (JComboBox)a.getSource();
                RamMenu.this.aktualnyLevel = (String)pom.getSelectedItem();

            }
        }
    }

    /* ActionListener pre button play, vytvori ram hry a ramMenu nadstavi na neviditelny */
    public class PosluchacButtonu implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            RamMenu.this.ramHry = new RamHry(RamMenu.this.aktualnyLevel);
            RamMenu.this.ramHry.setLocationRelativeTo(null);
            RamMenu.this.okno.setVisible(false);

        }
    }

}
