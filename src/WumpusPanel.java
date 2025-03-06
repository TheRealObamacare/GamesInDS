import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;


public class WumpusPanel extends JPanel implements KeyListener {
    public static final int PLAYING = 0;
    public static final int DEAD = 1;
    public static final int WON = 2;
    private int status;
    private WumpusPlayer player;
    private WumpusMap map;
    public WumpusPanel() {
        setSize(600, 800);
        setLayout(null);
        reset();
    }
    public void reset() {
        status = PLAYING;
        player = new WumpusPlayer();
        map = new WumpusMap();
        player.setRowPosition(map.getLadderR());
        player.setColPosition(map.getLadderC());
        map.getSquare(player.getRowPosition(), player.getColPosition()).setVisited(true);
    }
    public void keyTyped(KeyEvent e)
    {
        int key = e.getKeyCode();
        if (status == PLAYING)
        {
            if (key == KeyEvent.VK_W)
                player.setRowPosition(player.getRowPosition() - 1);
            if (key == KeyEvent.VK_S)
                player.setRowPosition(player.getRowPosition() + 1);
            if (key == KeyEvent.VK_A)
                player.setColPosition(player.getColPosition() - 1);
            if (key == KeyEvent.VK_D)
                player.setColPosition(player.getColPosition() + 1);
            if (player.getArrow() && key == KeyEvent.VK_I)//up
            {

            }
            if (player.getArrow() && key == KeyEvent.VK_K)//down
            {

            }
            if (player.getArrow() && key == KeyEvent.VK_J)//left
            {

            }
            if (player.getArrow() && key == KeyEvent.VK_L)//right
            {

            }
            if (player.getGold() && key == KeyEvent.VK_C)
            {
                if (map.getSquare(player.getRowPosition(), player.getColPosition()).getLadder())
                {
                    status = WON;
                }
            }
            if (map.getSquare(player.getRowPosition(), player.getColPosition()).getGold() && key == KeyEvent.VK_P)
            {
                player.setGold(true);
            }
            if (key == KeyEvent.VK_N)
            {
                if (status == WON || status == DEAD)
                {
                    reset();
                }
            }
            if (key == KeyEvent.VK_ASTERISK)
            {

            }
        }
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
        if (status == PLAYING) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    WumpusSquare square = map.getSquare(i, j);
                    if (square.getVisited())
                    {
                        System.out.println("Visited: " + i + " " + j);
                        g.drawImage(new ImageIcon("Floor.gif").getImage(), j * 50 + 50, i * 50 + 50, 50, 50, null);
                        if (square.getLadder()) {
                            System.out.println("Ladder: " + i + " " + j);
                            g.drawImage(new ImageIcon("ladder.gif").getImage(), j * 50 + 50, i * 50 + 50, 50, 50, null);
                        }if (square.getGold()) {
                            g.drawImage(new ImageIcon("gold.gif").getImage(), j * 50 + 50, i * 50 + 50, 50, 50, null);
                        }if (square.getBreeze()) {
                            g.drawImage(new ImageIcon("breeze.gif").getImage(), j * 50 + 50, i * 50 + 50, 50, 50, null);
                        }if (square.getStench()) {
                            g.drawImage(new ImageIcon("stench.gif").getImage(), j * 50 + 50, i * 50 + 50, 50, 50, null);
                        }if (square.getPit()) {
                            g.drawImage(new ImageIcon("pit.gif").getImage(), j * 50 + 50, i * 50 + 50, 50, 50, null);
                        }if (square.getWumpus()) {
                            g.drawImage(new ImageIcon("wumpus.gif").getImage(), j * 50 + 50, i * 50 + 50, 50, 50, null);
                        }if (square.getDeadWumpus()) {
                            g.drawImage(new ImageIcon("deadwumpus.GIF").getImage(), j * 50 + 50, i * 50 + 50, 50, 50, null);
                        }if (player.getRowPosition() == i && player.getColPosition() == j) {
                            if (player.getDirection() == 0)
                                g.drawImage(new ImageIcon("playerUp.png").getImage(), j * 50 + 50, i * 50 + 50, 50, 50, null);
                            else if (player.getDirection() == 1)
                                g.drawImage(new ImageIcon("playerRight.png").getImage(), j * 50 + 50, i * 50 + 50, 50, 50, null);
                            else if (player.getDirection() == 2)
                                g.drawImage(new ImageIcon("playerDown.png").getImage(), j * 50 + 50, i * 50 + 50, 50, 50, null);
                            else if (player.getDirection() == 3)
                                g.drawImage(new ImageIcon("playerLeft.png").getImage(), j * 50 + 50, i * 50 + 50, 50, 50, null);
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
