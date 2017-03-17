import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by SPAS on 12/03/2017.
 */
public class DrawListener implements MouseMotionListener, MouseListener {

    private DrawingPanel theDrawPanel;


    public DrawListener(DrawingPanel panel) {
        theDrawPanel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        theDrawPanel.Released();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    //handles drawing a continuous stroke
    @Override
    public void mouseDragged(MouseEvent e) {
        theDrawPanel.addALine(e.getX(), e.getY());
        theDrawPanel.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
