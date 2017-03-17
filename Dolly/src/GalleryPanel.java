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

//    private HashMap<Integer, ArrayList<Line>> saveData;
    private static final int memory = 12;
    private int filledSpace;

    private BufferedImage[] saves;

    private GalleryItem[] items;

//    private DrawingPanel theDrawingPanel;

    //on start, scans the folder for images to add in the gallery
    public GalleryPanel() {
        super();

        setLayout(new GridLayout(12, 1));

//        theDrawingPanel = canvas;
//        saveData = new HashMap<>();
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

//                add(new GalleryItem(convert(saves[i])));
//                repaint();

        }
    }

    //makes a bufferedImage into an icon
    public ImageIcon convert(BufferedImage image){
        ImageIcon icon = new ImageIcon(image.getScaledInstance(80,75,Image.SCALE_SMOOTH));
        return icon;
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(Color.BLACK);

//        setBackground(Color.CYAN);
//        repaint();
    }

//    public void saveImage(){
//        try {
//            theDrawingPanel.getPicture();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    //creates png files and panels from them
    public void saveToFile(BufferedImage picture){
        if(filledSpace < memory){
            for (int i = 0; i< memory; i++) {
                if(items[i] == null){
                    try {
//                        System.out.println("filledSpace right now: " + filledSpace);
//                        System.out.println("i right now: " + i);
                        ImageIO.write(picture,"PNG" , new File(i + ".png"));
                        saves[i] = picture;
                        items[i] = new GalleryItem(convert(picture), this, filledSpace);
                        add(items[i]);
                        filledSpace++;
//                        repaint();
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else {
            System.err.println("Gallery is FULL!!!");
        }
        revalidate();
    }

//need to check all of them since some might be deleted
    public void changeOthers(int itemNum) {
        for(int i = 0; i < memory; i++) {
            if (i != itemNum && items[i] != null) {
                items[i].setToInactive();
            }
        }
    }
//some items might be null so we chek all
    public void removeSelected() {
        for(int i = 0; i < memory; i++){
            if(items[i] != null) {
                if (items[i].getState()) {
                    remove(items[i]);
//                    items[i].setVisible(false);
                    items[i] = null;
                    saves[i] = null;
                    File temp = new File(i + ".png");
                    System.out.println("removing item: " + i);
                    temp.delete();
                    filledSpace--;

                    break;

                }
            }
        }
        revalidate();
    }

//    public void addToGallery(){
//        if (filledSpace <= memory && !theDrawingPanel.isMemoryEmpty()){
//            for(int i = 0; i < memory ; i++){ //checking for free memory slot
////                if(saveData.get(i) == null){
////                    HashMap<Integer, ArrayList<Line>> thePicture = theDrawingPanel.getPicture(); //turning into an arraylist
////                    ArrayList<Line> newList = new ArrayList<>();
////                    for(int j = 0; j < theDrawingPanel.getKey(); j++){
////                        newList.addAll(thePicture.get(j));
////                    }
////                    saveData.put(i, newList);
////                    filledSpace++;
////                    System.out.println("SAVED TO GALLERY!!");
////                    break;
////                }
//                    //if i is free
//                    theDrawingPanel.saveImage(i);
////                    break;
////                try {
////                    BufferedImage myImage = theDrawingPanel.getImage();
////                    ImageIO.write(myImage, "PNG", new File(i + ".png"));
////                    break;
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
//
//                try {
//                    ImageIO.write(theDrawingPanel.getImage(), "PNG", new File(  "asfsda.png"));
//                    break;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                //FIX LATER
////                if(saves[i] == null){
////                    saves[i] = (BufferedImage)(picture);
////                    System.out.println("SAVED!!!!");
////                    revalidate();
////                    break;
////                }
//            }
//        }
//    }
}
