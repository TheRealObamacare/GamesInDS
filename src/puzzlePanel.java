import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class puzzlePanel extends JPanel implements MouseListener {
    public puzzlePanel() {
        setSize(600, 800);
        setLayout(null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        e.getPoint();
        System.out.println("Mouse Clicked at " + e.getX() + ", " + e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
