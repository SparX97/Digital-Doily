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
    
    private Color currentColor;

//    private ArrayList<Point> savePoints;
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
    private int refOldY;
    private int refNewX;
    private int refNewY;
    private int oldXtoCenter;
    private int newXtoCenter;

//    private boolean testBool = false;

    private BufferedImage panelImage;

    private boolean saveNow;
//    private int fileName;

    private GalleryPanel theGallery;

    private Worker colorGenerator;
    private boolean useWorker;

    public DrawingPanel(GalleryPanel galleryPanel){
        super();
        theGallery = galleryPanel;
        DrawListener myListener = new DrawListener(currentColor,this);
        addMouseListener(myListener);
        addMouseMotionListener(myListener);

        currentColor = Color.WHITE;

//        savePoints = new ArrayList<>();
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

//        colorGenerator = new Worker(this);
        useWorker = false;
//        fileName = 0;

//        panelImage = new BufferedImage(0,0,BufferedImage.TYPE_4BYTE_ABGR);
//        refOldX = getWidth()/2;
//        refOldY = getHeight()/2;
//        refNewX = getWidth()/2;
//        refNewY = getHeight()/2;
    }

    @Override
    protected void paintComponent(Graphics Normalg){ //maybe public
        super.paintComponent(Normalg);
        setBackground(Color.BLACK);
        Graphics2D g = (Graphics2D)Normalg;

//        g.translate(getWidth()/2,getHeight()/2);    didnt work as i imagined
        if(numSectors > 1 && showSectors) {
            drawSectors(g);
        }
//        g.drawLine(10,3,100,30);
//        g.fillOval(getX(),getY(),pointDiameter,pointDiameter);

//        int oldX = 0, oldY = 0, newX, newY;
//        boolean notfirst = false;


        //drawing Previous mousedrags
        drawFromMap(g);

        //real-time drawing
        drawCurrent(g, saveLines);

        //saves in a BufferedImage
//        g.drawImage(panelImage,0, 0, null);
        if(saveNow){
//            panelImage.getGraphics();
//            g.drawImage(panelImage,null,0,0);
            Graphics2D imgGraphics2D = panelImage.createGraphics();
            imgGraphics2D.setColor(Color.BLACK);
            imgGraphics2D.fillRect(0,0,getWidth(),getHeight());
            drawFromMap(imgGraphics2D);
//            testBool = true;

            if(!isPanelClear) {
                theGallery.saveToFile(panelImage);
            } else {
                System.err.println("you can not add a picture with nothing in it");
            }
//            try {
//                ImageIO.write(panelImage, "PNG", new File(fileName + ".png"));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            saveNow = false;
//            System.out.println("did it save?");
        }

        }

    //Picture from previous steps
    private void drawFromMap(Graphics2D g){
        if(!isPanelClear) {
            for (int i = 0; i < key; i++) {
//                ArrayList<Line> temp = pointMap.get(i);
//                notfirst = false;
                drawCurrent(g, pointMap.get(i));
//                for (Line j : temp) {
//                    g.setColor(j.getLineColor());
//                    g.setStroke(new BasicStroke(j.getLineSize()));
////                    g.fillOval(j.getMyX(), j.getMyY(), j.getLineSize(), j.getPointSize());
//                    g.drawLine(j.getOldX(),j.getOldY(),j.getNewX(),j.getNewY());
//
//                    for(int k = 1; k <= numSectors; k++) {
//
//                        if(isReflecting) {
//                            oldXtoCenter = j.getOldX() - getWidth() / 2;
//                            newXtoCenter = j.getNewX() - getWidth() / 2;
//
//                            refOldX = getWidth() / 2 - (j.getOldX() - getWidth() / 2);
//                            refNewX = getWidth() / 2 - (j.getNewX() - getWidth() / 2);
//                        }
//
//                        AffineTransform normalRotation = g.getTransform();
//                        g.rotate(Math.toRadians(sectorAngle*k),getWidth()/2,getHeight()/2);
//                        g.drawLine(j.getOldX(), j.getOldY(), j.getNewX(),j.getNewY());
//                        if(isReflecting) g.drawLine(refOldX, j.getOldY(), refNewX, j.getNewY());
//                        g.setTransform(normalRotation);
//                    }
//                    newX = j.getMyX();
//                    newY = j.getMyY();
//                    if(notfirst){
//                        g.drawLine(oldX,oldY,newX,newY);
//                        notfirst = true;
//                    }
//                    oldX = newX;
//                    oldY = newY;
                }
            }
        }
//    }

    //draws in real-time

    //do i need this!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//    private void drawCurrent(Graphics2D g) {
//        for (Line i : saveLines) {
//            g.setColor(i.getLineColor());
//            g.setStroke(new BasicStroke(i.getLineSize()));
//            g.drawLine(i.getOldX(), i.getOldY(), i.getNewX(), i.getNewY());
//
//            for(int j = 1; j <= numSectors; j++) {
//
//                if(isReflecting) {
//                    oldXtoCenter = i.getOldX() - getWidth() / 2;
//                    newXtoCenter = i.getNewX() - getWidth() / 2;
//
//                    refOldX = getWidth() / 2 - (i.getOldX() - getWidth() / 2);
//                    refNewX = getWidth() / 2 - (i.getNewX() - getWidth() / 2);
//                }
//
//                AffineTransform normalRotation = g.getTransform();
//                g.rotate(Math.toRadians(sectorAngle*j),getWidth()/2,getHeight()/2);
//                g.drawLine(i.getOldX(), i.getOldY(), i.getNewX(),i.getNewY());
//                if(isReflecting) g.drawLine(refOldX, i.getOldY(), refNewX, i.getNewY());
//                g.setTransform(normalRotation);



//                System.out.println(i.getOldX()*temp);

                //ALGORITHUM WRONG

//                    refOldY = (int) (i.getOldY() * Math.cos(Math.toRadians(90 - 2*sectorAngle)) + i.getOldX() * Math.sin(Math.toRadians(2*sectorAngle)));

//                    refNewY = (int) (i.getNewY() * Math.cos(Math.toRadians(90 - 2*sectorAngle)) + i.getNewX() * Math.sin(Math.toRadians(2*sectorAngle)));


//                    System.out.println(refOldX);
//            }

//            g.fillOval(i.getMyX(), i.getMyY(), i.getPointSize(), i.getPointSize());
//            newX = i.getMyX();
//            newY = i.getMyY();
//            if(notfirst){
//                g.drawLine(oldX, oldY, newX, newY);
//                notfirst = true;
//            }
//            oldX = newX;
//            oldY = newY;
//        }
//    }
//draws in real-time
    public void drawCurrent(Graphics2D g, ArrayList<Line> lines) {

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        if(saveNow)
//        {
//            Graphics2D img2D = panelImage.createGraphics();
//        }
        for (Line i : lines) {
            g.setColor(i.getLineColor());
            g.setStroke(new BasicStroke(i.getLineSize()));
            g.drawLine(i.getOldX(), i.getOldY(), i.getNewX(), i.getNewY());
//            drawGraphics(g,i);
//            if(saveNow)
//            {
//                drawGraphics(img2D,i);
//            }
//            img2D.drawLine(i.getOldX(), i.getOldY(), i.getNewX(), i.getNewY());

            for (int j = 1; j <= numSectors; j++) {

                if (isReflecting) {
                    oldXtoCenter = i.getOldX() - getWidth() / 2;
                    newXtoCenter = i.getNewX() - getWidth() / 2;

                    refOldX = getWidth() / 2 - (i.getOldX() - getWidth() / 2);
                    refNewX = getWidth() / 2 - (i.getNewX() - getWidth() / 2);
                }

                AffineTransform normalRotation = g.getTransform();
                g.rotate(Math.toRadians(sectorAngle * j), getWidth() / 2, getHeight() / 2);
                g.drawLine(i.getOldX(), i.getOldY(), i.getNewX(), i.getNewY());

                if (isReflecting) g.drawLine(refOldX, i.getOldY(), refNewX, i.getNewY());
                g.setTransform(normalRotation);
//                drawRotations(g, i, j);
//                drawRotations(img2D, i, j);
            }
        }
    }

//    // handles drawing graphics to the target drawing place
//    public void drawGraphics(Graphics2D g, Line current){
//        g.setColor(current.getLineColor());
//        g.setStroke(new BasicStroke(current.getLineSize()));
//        g.drawLine(current.getOldX(), current.getOldY(), current.getNewX(), current.getNewY());
//
//    }

//    // handles drawing the line in a circle numSector times
//    public void drawRotations(Graphics2D g, Line current, int sectorNumber){
//        AffineTransform normalRotation = g.getTransform();
//        g.rotate(Math.toRadians(sectorAngle * sectorNumber), getWidth() / 2, getHeight() / 2);
//        g.drawLine(current.getOldX(), current.getOldY(), current.getNewX(), current.getNewY());
//
//        if (isReflecting) g.drawLine(refOldX, current.getOldY(), refNewX, current.getNewY());
//        g.setTransform(normalRotation);
//    }

    protected void addALine(int x, int y){
        
        if(isFirstPoint){
            startX = x;
            startY = y;
            isFirstPoint = false;
        }
        else {
            Line oneLine = new Line(startX, startY, x, y);
            oneLine.setLineColor(currentColor);
            oneLine.setLineSize(penDiameter);
            saveLines.add(oneLine);
            startX = x;
            startY = y;
        }

//        repaint();
    }

    protected void Released(){
//        savePoints = savePoints;
        addLines(saveLines);
        saveLines = new ArrayList<>(); //to clear the memory
        isFirstPoint = true;
        repaint();
    }

    public void addLines(ArrayList<Line> list) {
        pointMap.put(key, list);
        key++;
        isPanelClear = false;
    }

    public void clearScreen(){
        saveLines = new ArrayList<>();
        pointMap = new HashMap<>();
        key = 0;
        isPanelClear = true;
        repaint();
    }

//    public boolean isMemoryEmpty(){
//        return isPanelClear;
//    }

    public Color getColor(){
        return currentColor;
    }

    public void setColor(Color newColor){
        currentColor = newColor;
    }

    public int getPen(){
        return penDiameter;
    }

    public void setPenSize(int size){
        penDiameter = size;
    }

    public void undo(){
        if(key >= 1) {
            ArrayList<Line> temp = pointMap.get(key);
            temp = null; // .clear()
            key--;
            repaint();
        }
        else {
            System.err.println("You can not clear any more!");
        }
    }

    public void saveImage(){

        panelImage = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_ARGB);
        saveNow = true;
//        fileName = name;
        repaint();
//        return panelImage;
    }

//    public BufferedImage getImage(){
//        if(testBool) {
//            return panelImage;
//            System.err.println("da, ima zapazena snimka i q prashta");
//        }
//        else {
//            System.out.println("panela ne se e narisuval!!!!!!");
//            return panelImage;
//        }
//    }

    public int getKey(){
        return key;
    }

    public int getNumSectors(){
        return numSectors;
    }

    public void setNumSectors(int amount){
        numSectors = amount;
        sectorAngle = 360 / numSectors;
        repaint();
    }

    public void drawSectors(Graphics2D g){

        g.setColor(Color.LIGHT_GRAY);
        g.setStroke(new BasicStroke(1));

        int size = getWidth() * 5/8;
        g.drawLine(getWidth()/2, getHeight()/2, getWidth()/2,  size);
//        double sectorAngle = 360/numSectors;

//        int rotatedX, rotatedY;

//        AffineTransform normal = g.getTransform();

        for(int i = 1; i < numSectors; i++) {

//            rotatedX = (int) (getWidth()/2 + (size - getHeight()/2)* Math.sin(Math.toRadians(sectorAngle * i)));
//
//            rotatedY = (int) (getHeight()/2 + (size - getHeight()/2)* Math.cos(Math.toRadians(sectorAngle * i)));
//            g.drawLine(getWidth()/2, getHeight()/2, rotatedX, rotatedY);


            AffineTransform normalRotation = g.getTransform();
            g.rotate(Math.toRadians(sectorAngle*i),getWidth()/2,getHeight()/2);
            g.drawLine(getWidth()/2, getHeight()/2, getWidth()/2, size);
            g.setTransform(normalRotation);
        }
//        g.setTransform(normal);
    }

    public void toggleSectors(){
        showSectors = !showSectors;
        repaint();
    }

    public void toggleReflections(){
        isReflecting = !isReflecting;
        repaint();
    }

    public void toggleRandomColors(){
//        SwingWorker<Void, Void> colorGenerator;
//        if(startOrStop) {
//            SwingWorker<Void, Void> colorGenerator = new SwingWorker<Void, Void>() {
//
//                //call the process method every half a second
//                @Override
//                protected Void doInBackground() throws Exception {
//                    while (true){
//
//                        process();
//                        Thread.sleep(100);
//                    }
////                    return null;
//                }
//
//                protected void process(){
//
//                    Random rand = new Random();
//
//                    float red = rand.nextFloat();
//                    float green = rand.nextFloat();
//                    float blue = rand.nextFloat();
//
//                    setColor(new Color(red, green, blue));
//
//                }
//            };
        useWorker = !useWorker;
        if(useWorker) {
            colorGenerator = new Worker(this);
            colorGenerator.execute();
        }
        else
            colorGenerator.cancel(true);
//        }
//        else {
//            colorGenerator.cancel();
//        }


    }

    private void process(Color color) {
    }

//    private Ellipse2D.Float drawBrush(int x1, int y1, int brushStrokeWidth, int brushStrokeHeight)
//    {
//
//        return new Ellipse2D.Float(
//                x1, y1, brushStrokeWidth, brushStrokeHeight);
//
//    }
}
