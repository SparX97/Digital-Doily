import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Hashtable;

/**
 * Created by SPAS on 12/03/2017.
 */
public class CtrlPanel extends JPanel {

    private static int myPen;
    private static Color penColor;
    private GalleryPanel gallery;
    private DrawingPanel drawingPane;
    private int sectors;

    public CtrlPanel(DrawingPanel canvas, GalleryPanel theGalleryPanel) {
        super();
//        setBackground(Color.BLACK);
        drawingPane = canvas;
        gallery = theGalleryPanel;
        myPen = drawingPane.getPen();
        penColor = drawingPane.getColor();
        sectors = drawingPane.getNumSectors();

        setLayout(new FlowLayout());

        // handles making evenly spaced out sectors
        JSlider sectorSlider = new JSlider(1, 60, sectors);
        sectorSlider.setBorder(BorderFactory.createTitledBorder("Sectors"));
        Hashtable<Integer, JLabel> myTable = new Hashtable();
        myTable.put(1, new JLabel("1"));
        myTable.put(6, new JLabel("6"));
        myTable.put(12, new JLabel("12"));
        myTable.put(18, new JLabel("18"));
        myTable.put(24, new JLabel("24"));
        myTable.put(30, new JLabel("30"));
        myTable.put(36, new JLabel("36"));
        myTable.put(45, new JLabel("45"));
        myTable.put(60, new JLabel("60"));
        sectorSlider.setLabelTable(myTable);
        sectorSlider.setPaintLabels(true);
        sectorSlider.addChangeListener(e -> {
            if (360 % sectorSlider.getValue() == 0)
                sectors = sectorSlider.getValue();
            drawingPane.setNumSectors(sectors);
        });

        JPanel checkBox = new JPanel();
        checkBox.setLayout(new GridLayout(2, 2));

        JCheckBox maxSectors = new JCheckBox("360 Sectors");
        maxSectors.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED)
                drawingPane.setNumSectors(360);
            else
                drawingPane.setNumSectors(sectorSlider.getValue());
        });
        checkBox.add(maxSectors);

        JCheckBox reflect = new JCheckBox("Reflections");
        reflect.addItemListener(e -> {
            drawingPane.toggleReflections();
        });
        checkBox.add(reflect);

        JCheckBox randomColors = new JCheckBox("Crazy Colors");
        randomColors.addItemListener(e -> {
            drawingPane.toggleRandomColors();
        });
        checkBox.add(randomColors);

        JCheckBox sectorsCheck = new JCheckBox("Show Sectors");
        sectorsCheck.setSelected(true);
        sectorsCheck.addItemListener(e -> {
            drawingPane.toggleSectors();
        });
        checkBox.add(sectorsCheck);

        add(checkBox);

        JButton undo = new JButton("Undo Last Step");
        undo.addActionListener(e -> {
            drawingPane.undo();
        });
        add(undo);

        JButton clear = new JButton("Clear Display");
        clear.addActionListener(e -> {
            drawingPane.clearScreen();
        });
        add(clear);

        add(sectorSlider);

        JSlider penSlider = new JSlider(1, 20, myPen);
        penSlider.setBorder(BorderFactory.createTitledBorder("Pen Diamerter"));
        penSlider.addChangeListener(e -> {
            myPen = penSlider.getValue();
            drawingPane.setPenSize(myPen);
        });
        penSlider.setMajorTickSpacing(5);
        penSlider.setMinorTickSpacing(1);
        penSlider.setPaintTicks(true);
        add(penSlider);

        JButton colorChoose = new JButton("Choose Color");
        colorChoose.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(null, "Select Your New Color", penColor);
            drawingPane.setColor(newColor);
        });
        add(colorChoose);

        JButton save = new JButton("Save");
        save.addActionListener(e -> {
            drawingPane.saveImage();
        });
        add(save);

        JButton remove = new JButton("Remove");
        remove.addActionListener(e -> {
            gallery.removeSelected();
        });
        add(remove);
    }


}
