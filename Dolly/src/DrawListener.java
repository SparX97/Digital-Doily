import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by SPAS on 12/03/2017.
 */
public class DrawListener implements MouseMotionListener, MouseListener {


    private Color currentColor;
    private DrawingPanel theDrawPanel;


    public DrawListener(Color theColor, DrawingPanel panel){
        currentColor = theColor;
        theDrawPanel = panel;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        theDrawPanel.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        theDrawPanel.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        theDrawPanel.Released();
//        theDrawPanel.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
//        System.out.println("doing somethingaaa");

        theDrawPanel.addALine(e.getX(), e.getY());

        theDrawPanel.repaint();


    }

    @Override
    public void mouseMoved(MouseEvent e) {
//        System.out.println(e.getX() +" " + e.getY());
//        theDrawPanel.repaint();
    }
}
