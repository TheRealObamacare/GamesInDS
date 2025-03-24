import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class puzzlePanel extends JPanel implements MouseListener {
    private Tile[][] tiles = new Tile[4][4];
    private int emptyRow, emptyCol;
    private int moveCount;
    private boolean showNumbers = true;
    public boolean puzzleSolved = false;
    private BufferedImage puzzleImage;
    
    private JLabel moveCounterLabel;
    private JToggleButton toggleButton;
    private JButton newGameButton;
    private JLabel imagesLabel, numbersLabel;
    
    public puzzlePanel() {
        setSize(500, 500);
        setLayout(null);
        addMouseListener(this);
        
        setupUI();
        
        loadPuzzleImage();
        
        initializePuzzle();
    }
    
    private void setupUI() {
        newGameButton = new JButton("New Game");
        newGameButton.setBounds(45, 45, 100, 30);
        newGameButton.setBackground(Color.BLACK);
        newGameButton.setForeground(Color.WHITE);
        newGameButton.setFocusPainted(false);
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (puzzleSolved) {
                    resetGame();
                }
            }
        });
        add(newGameButton);
        
        numbersLabel = new JLabel("Numbers");
        numbersLabel.setBounds(170, 10, 60, 30);
        numbersLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        numbersLabel.setBackground(Color.BLACK);
        numbersLabel.setForeground(Color.WHITE);
        numbersLabel.setOpaque(true);
        add(numbersLabel);
        
        imagesLabel = new JLabel("Images");
        imagesLabel.setBounds(270, 10, 60, 30);
        imagesLabel.setHorizontalAlignment(SwingConstants.LEFT);
        imagesLabel.setBackground(Color.BLACK);
        imagesLabel.setForeground(Color.WHITE);
        imagesLabel.setOpaque(true);
        add(imagesLabel);
        
        toggleButton = new JToggleButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                g2.setColor(Color.DARK_GRAY);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                
                g2.setColor(Color.WHITE);
                if (isSelected()) {
                    g2.fillOval(getWidth() - 25, 5, 20, 20);
                } else {
                    g2.fillOval(5, 5, 20, 20);
                }
            }
        };
        
        toggleButton.setBounds(230, 10, 40, 30);
        toggleButton.setBorderPainted(false);
        toggleButton.setContentAreaFilled(false);
        toggleButton.setFocusPainted(false);
        toggleButton.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                showNumbers = !toggleButton.isSelected();
                repaint();
            }
        });
        add(toggleButton);
        
        moveCounterLabel = new JLabel("Moves: 0");
        moveCounterLabel.setBounds(355, 45, 100, 30);
        moveCounterLabel.setFont(new Font("Arial", Font.BOLD, 16));
        moveCounterLabel.setBackground(Color.BLACK);
        moveCounterLabel.setForeground(Color.WHITE);
        moveCounterLabel.setOpaque(true);
        add(moveCounterLabel);
    }
    
    private void loadPuzzleImage() {
        try {
            File imageFile = new File("src/images/dog.jpg");
            if (imageFile.exists()) {
                puzzleImage = ImageIO.read(imageFile);
            } else {
                System.out.println("Could not find image at: " + imageFile.getAbsolutePath());
                puzzleImage = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = puzzleImage.createGraphics();
                g.setColor(new Color(70, 130, 180)); 
                g.fillRect(0, 0, 400, 400);
                
                g.setColor(new Color(176, 224, 230)); 
                g.fillOval(50, 50, 150, 150);
                g.fillOval(200, 200, 150, 150);
                
                g.dispose();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void initializePuzzle() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                int value = row * 4 + col + 1;
                if (row == 3 && col == 3) {
                    value = 0; 
                    emptyRow = row;
                    emptyCol = col;
                }
                tiles[row][col] = new Tile(value, row, col);
                
                if (puzzleImage != null) {
                    tiles[row][col].setPuzzleImage(puzzleImage, 4, 4);
                }
            }
        }
        
        shufflePuzzle();
    }
    
    private void shufflePuzzle() {
        moveCount = 0;
        moveCounterLabel.setText("Moves: 0");
        puzzleSolved = false;
        
        Random rand = new Random();
        for (int i = 0; i < 200; i++) {
            List<Point> validMoves = getValidMoves();
            if (!validMoves.isEmpty()) {
                Point move = validMoves.get(rand.nextInt(validMoves.size()));
                swapTiles(move.x, move.y);
            }
        }
        
        repaint();
    }
    
    private List<Point> getValidMoves() {
        List<Point> moves = new ArrayList<>();
        
        if (emptyRow > 0) moves.add(new Point(emptyRow - 1, emptyCol));
        if (emptyRow < 3) moves.add(new Point(emptyRow + 1, emptyCol));
        if (emptyCol > 0) moves.add(new Point(emptyRow, emptyCol - 1));
        if (emptyCol < 3) moves.add(new Point(emptyRow, emptyCol + 1));
        
        return moves;
    }
    
    private void swapTiles(int row, int col) {
        Tile clickedTile = tiles[row][col];
        tiles[row][col] = tiles[emptyRow][emptyCol];
        tiles[emptyRow][emptyCol] = clickedTile;
        
        tiles[row][col].setPosition(row, col);
        tiles[emptyRow][emptyCol].setPosition(emptyRow, emptyCol);
        
        emptyRow = row;
        emptyCol = col;
    }
    
    private boolean checkSolution() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                int expectedValue = row * 4 + col + 1;
                if (row == 3 && col == 3) {
                    expectedValue = 0; 
                }
                
                if (tiles[row][col].getValue() != expectedValue) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private void resetGame() {
        shufflePuzzle();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(new Color(240, 240, 240));
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(Color.DARK_GRAY);
        g.fillRect(45, 80, 410, 410);
        
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Tile tile = tiles[row][col];
                if (tile.getValue() > 0) { 
                    g.drawImage(tile.getImage(showNumbers), col * 100 + 50, row * 100 + 85, this);
                }
            }
        }
        
        if (puzzleSolved) {
            g.setColor(new Color(0, 0, 0, 180));
            g.fillRect(50, 235, 400, 100);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Puzzle Solved!", 150, 275);
            g.drawString("Moves: " + moveCount, 190, 315);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (puzzleSolved) {
            return;
        }
        
        int x = e.getX();
        int y = e.getY();
        
        int col = (x - 50) / 100;
        int row = (y - 85) / 100;
        
        if (row >= 0 && row < 4 && col >= 0 && col < 4) {
            if ((Math.abs(row - emptyRow) == 1 && col == emptyCol) || 
                (Math.abs(col - emptyCol) == 1 && row == emptyRow)) {
                
                swapTiles(row, col);
                moveCount++;
                moveCounterLabel.setText("Moves: " + moveCount);
                
                puzzleSolved = checkSolution();
                
                repaint();
            }
        }
        
        System.out.println("Mouse Clicked at " + e.getX() + ", " + e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
