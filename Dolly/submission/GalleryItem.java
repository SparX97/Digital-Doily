import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by SPAS on 15/03/2017.
 */
public class GalleryItem extends JLabel implements MouseListener {

    Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
    Border redBorder = BorderFactory.createLineBorder(Color.RED, 3);
    private boolean isPressed;
    private GalleryPanel theGallery;
    private int myNum;

    public GalleryItem(ImageIcon imageIcon, GalleryPanel panelRef, int number) {
        super(imageIcon);
        addMouseListener(this);
        theGallery = panelRef;
        myNum = number;
        setBorder(blackBorder);
    }

    public void setToInactive() {
        isPressed = false;
        setBorder(blackBorder);
    }

    //used to search for the selected GalleryItem
    public boolean getState() {
        return isPressed;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    //handles selecting this current GalleryItem
    @Override
    public void mousePressed(MouseEvent e) {
        theGallery.changeOthers(myNum);
        isPressed = !isPressed;
        if (isPressed) setBorder(redBorder);
        else setBorder(blackBorder);
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
