import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;


public class WumpusPanel extends JPanel implements KeyListener {
    public static final int PLAYING = 0;
    public static final int DEAD = 1;
    public static final int WON = 2;
    private int status;
    private WumpusPlayer player;
    private WumpusMap map;
    private BufferedImage buffer;
    public WumpusPanel() {
        setSize(600, 800);
        setLayout(null);
        reset();
        addKeyListener(this);
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
        char key = e.getKeyChar();
        if (status == PLAYING)
        {
            if (key == 'w'){
                if (player.getRowPosition() > 0){
                    player.setRowPosition(player.getRowPosition() - 1);
                    player.setPlayerDirection(WumpusPlayer.NORTH);
                    map.getSquare(player.getRowPosition(), player.getColPosition()).setVisited(true);
                    repaint();
                }
            }
            if (key == 's')
                if (player.getRowPosition() < 9) {
                    player.setRowPosition(player.getRowPosition() + 1);
                    player.setPlayerDirection(WumpusPlayer.SOUTH);
                    map.getSquare(player.getRowPosition(), player.getColPosition()).setVisited(true);
                    repaint();
                }
            if (key == 'a')
                if (player.getColPosition() > 0){
                    player.setColPosition(player.getColPosition() - 1);
                    player.setPlayerDirection(WumpusPlayer.WEST);
                    map.getSquare(player.getRowPosition(), player.getColPosition()).setVisited(true);
                    repaint();
                }
            if (key == 'd')
                if (player.getColPosition() < 9){
                    player.setColPosition(player.getColPosition() + 1);
                    player.setPlayerDirection(WumpusPlayer.EAST);
                    map.getSquare(player.getRowPosition(), player.getColPosition()).setVisited(true);
                    repaint();
                }
            if (player.getArrow() && key == 'i')//up
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
            if (map.getSquare(player.getRowPosition(), player.getColPosition()).getPit())
            {
                status = DEAD;
            }
            if (key == KeyEvent.VK_ASTERISK)
            {

            }
        }
        if (key == KeyEvent.VK_N)
        {
            reset();
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
                        try {
                            buffer = ImageIO.read(new File("src/images/Floor.gif"));
                            g.drawImage(buffer, j * 50 + 50, i * 50 + 50, 50, 50, null);
                            if (square.getLadder()) {
                                buffer = ImageIO.read(new File("src/images/ladder.gif"));
                                g.drawImage(buffer, j * 50 + 50, i * 50 + 50, 50, 50, null);
                            }
                            if (square.getGold()) {
                                buffer = ImageIO.read(new File("src/images/gold.gif"));
                                g.drawImage(buffer, j * 50 + 50, i * 50 + 50, 50, 50, null);
                            }
                            if (square.getBreeze()) {
                                buffer = ImageIO.read(new File("src/images/breeze.gif"));
                                g.drawImage(buffer, j * 50 + 50, i * 50 + 50, 50, 50, null);
                            }
                            if (square.getStench()) {
                                buffer = ImageIO.read(new File("src/images/stench.gif"));
                                g.drawImage(buffer, j * 50 + 50, i * 50 + 50, 50, 50, null);
                            }
                            if (square.getPit()) {
                                buffer = ImageIO.read(new File("src/images/pit.gif"));
                                g.drawImage(buffer, j * 50 + 50, i * 50 + 50, 50, 50, null);
                            }
                            if (square.getWumpus()) {
                                buffer = ImageIO.read(new File("src/images/wumpus.gif"));
                                g.drawImage(buffer, j * 50 + 50, i * 50 + 50, 50, 50, null);
                            }
                            if (square.getDeadWumpus()) {
                                buffer = ImageIO.read(new File("src/images/deadwumpus.gif"));
                                g.drawImage(buffer, j * 50 + 50, i * 50 + 50, 50, 50, null);
                            }
                            if (player.getRowPosition() == i && player.getColPosition() == j) {
                                if (player.getDirection() == 0) {
                                    buffer = ImageIO.read(new File("src/images/playerUp.png"));
                                    g.drawImage(buffer, j * 50 + 50, i * 50 + 50, 50, 50, null);
                                } else if (player.getDirection() == 1) {
                                    buffer = ImageIO.read(new File("src/images/playerRight.png"));
                                    g.drawImage(buffer, j * 50 + 50, i * 50 + 50, 50, 50, null);
                                } else if (player.getDirection() == 2) {
                                    buffer = ImageIO.read(new File("src/images/playerDown.png"));
                                    g.drawImage(buffer, j * 50 + 50, i * 50 + 50, 50, 50, null);
                                } else if (player.getDirection() == 3) {
                                    buffer = ImageIO.read(new File("src/images/playerLeft.png"));
                                    g.drawImage(buffer, j * 50 + 50, i * 50 + 50, 50, 50, null);
                                }
                            }
                        }
                        catch (Exception e) {
                            System.out.println("Error: " + e);
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
        try{
            if (player.getArrow()){
                buffer = ImageIO.read(new File("src/images/arrow.gif"));
                g.drawImage(buffer, 0, 660 + x, 50, 50, null);
                x += 15;
            }
            if (player.getGold()){
                buffer = ImageIO.read(new File("src/images/gold.gif"));
                g.drawImage(buffer, 60, 660 + x, 50, 50, null);
                x += 15;
            }
        }
        catch (Exception e){
            System.out.println("Error: " + e);
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
            g.drawString("You bot you fell into pit", 350, 660+x);
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
