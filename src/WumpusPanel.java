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
    private boolean hacks;
    private int status;
    private WumpusPlayer player;
    private WumpusMap map;
    private BufferedImage buffer;
    private boolean isDead;
    public WumpusPanel() {
        setSize(600, 800);
        setLayout(null);
        reset();
        addKeyListener(this);
        isDead = false;
        hacks = false;
    }
    public void reset() {
        status = PLAYING;
        player = new WumpusPlayer();
        map = new WumpusMap();
        player.setRowPosition(map.getLadderR());
        player.setColPosition(map.getLadderC());
        map.getSquare(player.getRowPosition(), player.getColPosition()).setVisited(true);
        isDead = false;
        hacks = false;
        repaint();
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
            if (player.getArrow())
            {
                if (key == 'i')//up
                {
                    shootArrow(player.getRowPosition(), player.getColPosition(), WumpusPlayer.NORTH);
                }
                if (key == 'k')//down
                {
                    shootArrow(player.getRowPosition(), player.getColPosition(), WumpusPlayer.SOUTH);
                }
                if (key == 'j')//left
                {
                    shootArrow(player.getRowPosition(), player.getColPosition(), WumpusPlayer.WEST);
                }
                if (key == 'l')//right
                {
                    shootArrow(player.getRowPosition(), player.getColPosition(), WumpusPlayer.EAST);
                }
            }
            if (player.getGold() && key == 'c')
            {
                if (map.getSquare(player.getRowPosition(), player.getColPosition()).getLadder())
                {
                    status = WON;
                    repaint();
                }
            }
            if (map.getSquare(player.getRowPosition(), player.getColPosition()).getGold() && key == 'p')
            {
                player.setGold(true);
                map.getSquare(player.getRowPosition(), player.getColPosition()).setGold(false);
                repaint();
            }
            if (map.getSquare(player.getRowPosition(), player.getColPosition()).getPit())
            {
                status = DEAD;
            }
        }
        if (key == '*')
        {
            if (hacks)
            {
                hacks = false;
                repaint();
            }
            else
            {
                hacks = true;
                repaint();
            }
        }
        if (key == 'n')
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
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    WumpusSquare square = map.getSquare(i, j);
                    if (square.getVisited() || hacks)
                    {
                        try {
                            buffer = ImageIO.read(new File("src/images/Floor.gif"));
                            g.drawImage(buffer, j * 50 + 50, i * 50 + 50, 50, 50, null);
                            if (square.getLadder()) {
                                buffer = ImageIO.read(new File("src/images/ladder.gif"));
                                g.drawImage(buffer, j * 50 + 50, i * 50 + 50, 50, 50, null);
                            }
                            if (square.getGold()) {
                                System.out.println("Gold is at" + i + " " + j);
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


        g.setColor(Color.RED);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        g.drawString("Inventory", 0, 630);
        g.drawString("Messages", 350, 630);
        g.setColor(Color.CYAN);
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
        x = 0;
        if (status == WON){
            g.drawString("You won! Press n for new game", 350, 660 + x);
            return;
        }
        if (isDead)
        {
            g.drawString("You hear a scream", 350, 660 + x);
            isDead = false;
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
            g.drawString("You fell down a pit to your death", 350, 660+x);
            x += 15;
        }
        if (map.getSquare(player.getRowPosition(), player.getColPosition()).getWumpus()){
            g.drawString("You are eaten by the Wumpus", 350, 660+ x);
            status = DEAD;
            x += 15;
        }
        if (map.getSquare(player.getRowPosition(), player.getColPosition()).getDeadWumpus()){
            g.drawString("You smell a stench", 350, 660 + x);
            x += 15;
        }
    }
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
    public void shootArrow(int row, int col, int direction)
    {
        if (direction == WumpusPlayer.NORTH)
        {
            for (int i = row; i >= 0; i--)
            {
                if (map.getSquare(i, col).getWumpus())
                {
                    map.getSquare(i, col).setWumpus(false);
                    map.getSquare(i, col).setDeadWumpus(true);
                    repaint();
                    isDead = true;
                }
            }
        }
        if (direction == WumpusPlayer.SOUTH)
        {
            for (int i = row; i < 10; i++)
            {
                if (map.getSquare(i, col).getWumpus())
                {
                    map.getSquare(i, col).setWumpus(false);
                    map.getSquare(i, col).setDeadWumpus(true);
                    repaint();
                    isDead = true;
                }
            }
        }
        if (direction == WumpusPlayer.WEST)
        {
            for (int i = col; i >= 0; i--)
            {
                if (map.getSquare(row, i).getWumpus())
                {
                    map.getSquare(i, col).setWumpus(false);
                    map.getSquare(row, i).setDeadWumpus(true);
                    repaint();
                    isDead = true;
                }
            }
        }
        if (direction == WumpusPlayer.EAST)
        {
            for (int i = col; i < 10; i++)
            {
                if (map.getSquare(row, i).getWumpus())
                {
                    map.getSquare(i, col).setWumpus(false);
                    map.getSquare(row, i).setDeadWumpus(true);
                    repaint();
                    isDead = true;
                }
            }
        }
        player.setArrow(false);
        repaint();
    }
}