import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by SPAS on 13/03/2017.
 */
public class GalleryPanel extends JPanel {

    private static final int memory = 12;
    private int filledSpace;
    private BufferedImage[] saves;
    private GalleryItem[] items;


    //on start, scans the folder for images to add in the gallery
    public GalleryPanel() {
        super();

        setLayout(new GridLayout(12, 1));
        filledSpace = 0;
        saves = new BufferedImage[memory];
        items = new GalleryItem[memory];

        for (int i = 0; i < memory; i++) {
            try {
                if (ImageIO.read(new File(i + ".png")) != null) {
                    saves[i] = ImageIO.read(new File(i + ".png"));
                    items[i] = new GalleryItem(convert(saves[i]), this, filledSpace);
                    add(items[i]);
                    filledSpace++;
                }
            } catch (IOException e) {
//                System.out.println(e + "number " + i);
            }
        }
    }

    //makes a bufferedImage into an icon
    private ImageIcon convert(BufferedImage image) {
        return new ImageIcon(image.getScaledInstance(80, 75, Image.SCALE_SMOOTH));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
    }

    //creates png files and panels from them
    public void saveToFile(BufferedImage picture) {
        if (filledSpace < memory) {
            for (int i = 0; i < memory; i++) {
                if (items[i] == null) {
                    try {
                        ImageIO.write(picture, "PNG", new File(i + ".png"));
                        saves[i] = picture;
                        items[i] = new GalleryItem(convert(picture), this, filledSpace);
                        add(items[i]);
                        filledSpace++;
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.err.println("Gallery is FULL!!!");
        }
        revalidate();
    }

    // unselects all but one little picture
    public void changeOthers(int itemNum) {
        for (int i = 0; i < memory; i++) {
            if (i != itemNum && items[i] != null) {
                if(items[i].getState())
                items[i].setToInactive();
            }
        }
    }

    //Removes 1 of the galleryItems. Some items might be null so checks all
    public void removeSelected() {
        for (int i = 0; i < memory; i++) {
            if (items[i] != null) {
                if (items[i].getState()) {
                    remove(items[i]);
                    items[i] = null;
                    saves[i] = null;
                    File temp = new File(i + ".png");
                    temp.delete();
                    filledSpace--;
                    break;
                }
            }
        }
        revalidate();
    }
}
