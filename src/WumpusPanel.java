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
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 800);
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, 600, 600);
        g.fillRect(320, 600, 10, 200);
        g.setColor(Color.BLACK);
        g.fillRect(50, 50, 500, 500);
        WumpusMap map = new WumpusMap();
        WumpusPlayer player = new WumpusPlayer();
        if (status == PLAYING) {
            for (int i = 0; i < WumpusMap.NUM_ROWS; i++) {
                for (int j = 0; j < WumpusMap.NUM_COLS; j++) {
                    WumpusSquare square = map.getSquare(i, j);
                    if (square.getVisited())
                    {
                        g.fillRect(j * 50 + 50, i * 50 + 50, 50, 50);
                        g.drawImage(new ImageIcon("floor.png").getImage(), j * 50 + 50, i * 50 + 50, 50, 50, null);
                        if (square.getLadder()) {
                            g.drawImage(new ImageIcon("ladder.png").getImage(), j * 50 + 50, i * 50 + 50, 50, 50, null);
                        }if (square.getGold()) {
                            g.drawImage(new ImageIcon("gold.png").getImage(), j * 50 + 50, i * 50 + 50, 50, 50, null);
                        }if (square.getBreeze()) {
                            g.drawImage(new ImageIcon("breeze.png").getImage(), j * 50 + 50, i * 50 + 50, 50, 50, null);
                        }if (square.getStench()) {
                            g.drawImage(new ImageIcon("stench.png").getImage(), j * 50 + 50, i * 50 + 50, 50, 50, null);
                        }if (square.getPit()) {
                            g.drawImage(new ImageIcon("pit.png").getImage(), j * 50 + 50, i * 50 + 50, 50, 50, null);
                        }if (square.getWumpus()) {
                            g.drawImage(new ImageIcon("wumpus.png").getImage(), j * 50 + 50, i * 50 + 50, 50, 50, null);
                        }if (square.getDeadWumpus()) {
                            g.drawImage(new ImageIcon("deadWumpus.png").getImage(), j * 50 + 50, i * 50 + 50, 50, 50, null);
                        }if (player.getRowPosition() == i && player.getColPosition() == j) {
                            if (player.getDirection() == 0)
                                g.drawImage(new ImageIcon("playerNorth.png").getImage(), j * 50 + 50, i * 50 + 50, 50, 50, null);
                            else if (player.getDirection() == 1)
                                g.drawImage(new ImageIcon("playerEast.png").getImage(), j * 50 + 50, i * 50 + 50, 50, 50, null);
                            else if (player.getDirection() == 2)
                                g.drawImage(new ImageIcon("playerSouth.png").getImage(), j * 50 + 50, i * 50 + 50, 50, 50, null);
                            else if (player.getDirection() == 3)
                                g.drawImage(new ImageIcon("playerWest.png").getImage(), j * 50 + 50, i * 50 + 50, 50, 50, null);
                        }
                    }
                }
            }
        }
        g.setColor(Color.RED);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        g.drawString("Inventory", 0, 630);
        g.drawString("Messages", 350, 630);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        int x = 0;
        if (player.getArrow()){
            g.drawImage(new ImageIcon("arrow.png").getImage(), 0, 660 + x, 50, 50, null);
            x += 15;
        }
        if (player.getGold()){
            g.drawImage(new ImageIcon("gold.png").getImage(), 60, 660 + x, 50, 50, null);
            x += 15;
        }
        if (map.getSquare(player.getRowPosition(), player.getColPosition()).getBreeze()){
            g.drawString("You feel a breeze", 350, 660+ x);
            x += 15;
        }
        if (map.getSquare(player.getRowPosition(), player.getColPosition()).getStench()){
            g.drawString("You smell a stench", 350, 660+x);
            x += 15;
        }
        if (map.getSquare(player.getRowPosition(), player.getColPosition()).getGold()){
            g.drawString("You see a glimmer", 350, 660+x);
            x += 15;
        }
        if (map.getSquare(player.getRowPosition(), player.getColPosition()).getLadder()){
            g.drawString("You bump into a ladder", 350, 660+x);
            x += 15;
        }
        if (map.getSquare(player.getRowPosition(), player.getColPosition()).getPit()){
            g.drawString("You feel a breeze", 350, 660+x);
            x += 15;
        }
        if (map.getSquare(player.getRowPosition(), player.getColPosition()).getWumpus()){
            g.drawString("You are eaten by the Wumpus", 350, 660+ x);
            x += 15;
        }
        if (map.getSquare(player.getRowPosition(), player.getColPosition()).getDeadWumpus()){
            g.drawString("You smell a stench", 350, 660 + x);
            x += 15;
        }
        if (status == WON){
            g.drawString("You won", 350, 660 + x);
            x += 15;
        }
        if (status == DEAD) {
            g.drawString("You died", 350, 660 + x);
            x += 15;
        }
    }
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
}
