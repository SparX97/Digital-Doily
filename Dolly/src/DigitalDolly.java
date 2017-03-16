import javax.swing.*;
import java.awt.*;

/**
 * Created by SPAS on 12/03/2017.
 */
public class DigitalDolly {

    public static void main(String[] args) {
//        dollyFrame myFrame = new dollyFrame("Doily");
//        myFrame.initialise();

        SwingUtilities.invokeLater (
                new Runnable () {
                    public void run() {
                        dollyFrame myFrame = new dollyFrame("Doily");
                        myFrame.initialise();
                    }
                }
        );
    }
}

class dollyFrame extends JFrame {

    public dollyFrame(String title) {
        super(title);
    }

    public void initialise(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500,1000);


        GalleryPanel gallery = new GalleryPanel();
        DrawingPanel drawingBoard = new DrawingPanel(gallery);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);//add() is almost the same

//        TopPanel theMenu = new TopPanel(drawingBoard);
//        GalleryPanel gallery = new GalleryPanel();
        CtrlPanel controlPanel = new CtrlPanel(drawingBoard, gallery);

        mainPanel.add(controlPanel,BorderLayout.NORTH);
        mainPanel.add(drawingBoard, BorderLayout.CENTER);
        mainPanel.add(gallery, BorderLayout.WEST);

        setVisible(true);
    }
}

//class PanelHolder extends JPanel {
//
//    public PanelHolder(){
//        super();
//    }
//}

//class TopPanel extends JPanel{
//
//    private GalleryPanel gallery;
//    private CtrlPanel controlPanel;
////    private JButton clear;
//
//    public TopPanel(DrawingPanel canvas){
//        super();
//        setLayout(new FlowLayout());
////        gallery = new GalleryPanel(canvas);
//        controlPanel = new CtrlPanel(canvas, gallery);
////        chooseColor = new JButton("Colors");
////        clear = new JButton("Clear Canvas");
//
////        add(clear);
////        add(chooseColor);
//        add(controlPanel);
////        add(gallery);
//    }
//}
