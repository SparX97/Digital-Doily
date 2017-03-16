import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by SPAS on 15/03/2017.
 */
public class GalleryItem extends JLabel implements MouseListener {

//    private BufferedImage image;

//    private ImageIcon

    public boolean isPressed;
    GalleryPanel theGallery;
    private int myNum;

    Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
    Border redBorder = BorderFactory.createLineBorder(Color.RED,3);

    public GalleryItem(ImageIcon imageIcon, GalleryPanel panelRef, int number){
        super(imageIcon);
        addMouseListener(this);
//        itemsRef = arrayRef;
        theGallery = panelRef;
        myNum = number;
        setBorder(blackBorder);
//        setFocusable(true);
//        image = picture;
    }

    public void setToInactive(){
        isPressed = false;
        setBorder(blackBorder);
    }

    public boolean getState(){
        return isPressed;
    }

    protected void paintComponent(){
//        setBackground(Color.BLACK);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

        theGallery.changeOthers(myNum);
        isPressed = !isPressed;
        if(isPressed) setBorder(redBorder);
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

//    public void setIcon()
//    protected void paintComponent(Graphics2D g) {
//        super.paintComponent(g);
//        g.drawImage(image,0,0, this);
//    }
}
