import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    private int value;
    private int row, col;
    private BufferedImage numberImage;
    private BufferedImage puzzleImage;
    
    public Tile(int value, int row, int col) {
        this.value = value;
        this.row = row;
        this.col = col;
        createNumberImage();
    }
    
    private void createNumberImage() {
        numberImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = numberImage.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 100, 100);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, 99, 99);
        
        if (value > 0) {
            g.setFont(new Font("Arial", Font.BOLD, 36));
            String text = String.valueOf(value);
            FontMetrics fm = g.getFontMetrics();
            int x = (100 - fm.stringWidth(text)) / 2;
            int y = ((100 - fm.getHeight()) / 2) + fm.getAscent();
            g.drawString(text, x, y);
        }
        g.dispose();
    }
    
    public void setPuzzleImage(BufferedImage sourceImage, int totalRows, int totalCols) {
        if (value == 0) {
            // Empty tile - create blank image
            puzzleImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = puzzleImage.createGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 100, 100);
            g.dispose();
            return;
        }
        
        // Calculate which part of the source image to use
        int index = value - 1;
        int sourceRow = index / totalCols;
        int sourceCol = index % totalCols;
        
        int tileWidth = sourceImage.getWidth() / totalCols;
        int tileHeight = sourceImage.getHeight() / totalRows;
        int x = sourceCol * tileWidth;
        int y = sourceRow * tileHeight;
        
        puzzleImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = puzzleImage.createGraphics();
        g.drawImage(sourceImage.getSubimage(x, y, tileWidth, tileHeight), 0, 0, 100, 100, null);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, 99, 99);
        g.dispose();
    }
    
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    public int getValue() { return value; }
    public int getRow() { return row; }
    public int getCol() { return col; }
    
    public BufferedImage getImage(boolean showNumbers) {
        return showNumbers ? numberImage : puzzleImage;
    }
}
