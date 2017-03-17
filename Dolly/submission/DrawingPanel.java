import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by SPAS on 12/03/2017.
 */
public class DrawingPanel extends JPanel {

    private static int penDiameter = 1;
    private static Color currentColor = Color.WHITE;
    private ArrayList<Line> saveLines;
    private HashMap<Integer, ArrayList<Line>> pointMap;
    private int key;
    private int startX, startY;
    private boolean isFirstPoint;
    private boolean isPanelClear;
    private int numSectors;
    private double sectorAngle;
    private boolean showSectors;
    private boolean isReflecting;
    private int refOldX;
    private int refNewX;
    private BufferedImage panelImage;
    private boolean saveNow;
    private GalleryPanel theGallery;
    private Worker colorGenerator;
    private boolean useWorker;


    public DrawingPanel(GalleryPanel galleryPanel) {
        super();
        theGallery = galleryPanel;
        DrawListener myListener = new DrawListener(this);
        addMouseListener(myListener);
        addMouseMotionListener(myListener);

        saveLines = new ArrayList<>();

        pointMap = new HashMap<>();
        key = 0;

        isPanelClear = true;
        isFirstPoint = true;

        numSectors = 1;
        sectorAngle = 0;

        showSectors = true;
        isReflecting = false;

        saveNow = false;

        useWorker = false;

    }

    @Override
    protected void paintComponent(Graphics Normalg) {
        super.paintComponent(Normalg);
        setBackground(Color.BLACK);
        Graphics2D g = (Graphics2D) Normalg;

        if (numSectors > 1 && showSectors) {
            drawSectors(g);
        }

        //drawing Previous mousedrags
        drawFromMap(g);

        //real-time drawing
        drawCurrent(g, saveLines);

        //saves in a BufferedImage
        if (saveNow) {
            drawToBufferedImage();
        }
    }

    //calls drawing the previous strokes
    private void drawFromMap(Graphics2D g) {
        if (!isPanelClear) {
            for (int i = 0; i < key; i++) {
                drawCurrent(g, pointMap.get(i));
            }
        }
    }

    //draws in real-time. Handles drawing and rotating
    public void drawCurrent(Graphics2D g, ArrayList<Line> lines) {

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        for (Line i : lines) {
            g.setColor(i.getLineColor());
            g.setStroke(new BasicStroke(i.getLineSize()));
            g.drawLine(i.getOldX(), i.getOldY(), i.getNewX(), i.getNewY());

            for (int j = 1; j <= numSectors; j++) {

                if (isReflecting) {

                    refOldX = getWidth() / 2 - (i.getOldX() - getWidth() / 2);
                    refNewX = getWidth() / 2 - (i.getNewX() - getWidth() / 2);
                }

                AffineTransform normalRotation = g.getTransform();
                g.rotate(Math.toRadians(sectorAngle * j), getWidth() / 2, getHeight() / 2);
                g.drawLine(i.getOldX(), i.getOldY(), i.getNewX(), i.getNewY());

                if (isReflecting) g.drawLine(refOldX, i.getOldY(), refNewX, i.getNewY());
                g.setTransform(normalRotation);
            }
        }
    }

    //saves the current painting in a BufferedImage
    public void drawToBufferedImage() {

        Graphics2D imgGraphics2D = panelImage.createGraphics();
        imgGraphics2D.setColor(Color.BLACK);
        imgGraphics2D.fillRect(0, 0, getWidth(), getHeight());
        drawFromMap(imgGraphics2D);

        if (!isPanelClear) {
            theGallery.saveToFile(panelImage);
        } else {
            System.err.println("you can not add a picture with nothing in it");
        }
        saveNow = false;
    }

    //stores every point to point coordinate
    protected void addALine(int x, int y) {

        if (isFirstPoint) {
            startX = x;
            startY = y;
            isFirstPoint = false;
        } else {
            Line oneLine = new Line(startX, startY, x, y);
            oneLine.setLineColor(currentColor);
            oneLine.setLineSize(penDiameter);
            saveLines.add(oneLine);
            startX = x;
            startY = y;
        }

    }

    //saves after the new stroke is finished
    protected void Released() {
        addLines(saveLines);
        saveLines = new ArrayList<>(); //to clear the memory
        isFirstPoint = true;
        repaint();
    }

    //adds the last stroke to the memory
    public void addLines(ArrayList<Line> list) {
        pointMap.put(key, list);
        key++;
        isPanelClear = false;
    }

    //deletes everything up to this point
    public void clearScreen() {
        saveLines = new ArrayList<>();
        pointMap = new HashMap<>();
        key = 0;
        isPanelClear = true;
        repaint();
    }

    public Color getColor() {
        return currentColor;
    }

    public void setColor(Color newColor) {
        currentColor = newColor;
    }

    public int getPen() {
        return penDiameter;
    }

    public void setPenSize(int size) {
        penDiameter = size;
    }

    //deletes the last stroke
    public void undo() {
        if (key >= 1) {
            ArrayList<Line> temp = pointMap.get(key);
            temp = null; // .clear()
            key--;
            repaint();
        } else {
            System.err.println("You can not clear any more!");
        }
    }

    //schedules to save the drawing in the gallery
    public void saveImage() {
        panelImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        saveNow = true;
        repaint();
    }

    public int getNumSectors() {
        return numSectors;
    }

    //handles changing the number of sectors
    public void setNumSectors(int amount) {
        numSectors = amount;
        sectorAngle = 360 / numSectors;
        repaint();
    }

    //creates the sectors on the panel
    public void drawSectors(Graphics2D g) {

        g.setColor(Color.LIGHT_GRAY);
        g.setStroke(new BasicStroke(1));

        int size = getWidth() * 5 / 8;
        g.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2, size);

        for (int i = 1; i < numSectors; i++) {
            AffineTransform normalRotation = g.getTransform();
            g.rotate(Math.toRadians(sectorAngle * i), getWidth() / 2, getHeight() / 2);
            g.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2, size);
            g.setTransform(normalRotation);
        }
    }

    public void toggleSectors() {
        showSectors = !showSectors;
        repaint();
    }

    public void toggleReflections() {
        isReflecting = !isReflecting;
        repaint();
    }

    // I really wanted to make this extension
    //quickly changes colors
    public void toggleRandomColors() {
        useWorker = !useWorker;
        if (useWorker) {
            colorGenerator = new Worker(this);
            colorGenerator.execute();
        } else
            colorGenerator.cancel(true);
    }
}
