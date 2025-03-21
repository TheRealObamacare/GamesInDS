import javax.swing.*;
import java.awt.*;

public class puzzleFrame extends JFrame {

    public puzzleFrame(String frameName) {
        super(frameName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        puzzlePanel p = new puzzlePanel();
        Insets frameInsets = getInsets();
        int frameWidth = p.getWidth() + (frameInsets.left + frameInsets.right);
        int frameHeight = p.getHeight() + (frameInsets.top + frameInsets.bottom);
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setLayout(null);
        add(p);
        pack();
        setVisible(true);
    }
}
