import javax.swing.*;
import java.awt.*;

/**
 * Created by SPAS on 12/03/2017.
 */
//handles panel creation and orientation
class DoilieFrame extends JFrame {

    public DoilieFrame(String title) {
        super(title);
    }

    public void initialise() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 1000);

        GalleryPanel gallery = new GalleryPanel();
        DrawingPanel drawingBoard = new DrawingPanel(gallery);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);

        CtrlPanel controlPanel = new CtrlPanel(drawingBoard, gallery);

        mainPanel.add(controlPanel, BorderLayout.NORTH);
        mainPanel.add(drawingBoard, BorderLayout.CENTER);
        mainPanel.add(gallery, BorderLayout.WEST);
        setVisible(true);
    }
}
