import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;


public class WumpusPanel extends JPanel implements KeyListener {
    public static final int PLAYING = 0;
    public static final int DEAD = 1;
    public static final int WON = 2;
    private int status;
    WumpusPlayer player;
    WumpusMap map;
    public WumpusPanel() {
        setSize(600, 800);
        setLayout(null);
        setVisible(true);
    }
    public void reset() {
        status = PLAYING;
        player = new WumpusPlayer();
        map = new WumpusMap();
        // Places the player at the position of the ladder
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }
    public void paint(Graphics g) {
        super.paint(g);

    }
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
}
