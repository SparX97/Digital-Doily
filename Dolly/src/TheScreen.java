import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.List;

public class TheScreen {

    public static void main(String[] args) {
        MouseTrailFrame frame = new MouseTrailFrame();
        frame.init();
    }

}

class MouseTrailFrame extends JFrame {
    public MouseTrailFrame() {
        super("Mouse Trail Demo");
    }

    public void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);

        MouseTrailPanel panel = new MouseTrailPanel();
        setContentPane(panel);

        setVisible(true);
    }
}

class Trail {

    private final int TRAIL_LENGTH = 50;

    private LinkedList<Point> points = new LinkedList<Point>();

    private boolean mousePressed = false;

    public void addPoint(Point p) {
        points.add(p);
        if (points.size() > TRAIL_LENGTH) {
            points.remove();
        }
    }

    public void mousePressed() {
        this.mousePressed = true;
    }

    public void mouseReleased() {
        this.mousePressed = false;
    }

    public boolean isMousePressed() {
        return this.mousePressed;
    }

    public List<Point> getTrail() {
        return points;
    }

    public void clear() {
        points.clear();
    }

}

class MouseTrailPanel extends JPanel {

    private final int CIRCLE_DIAMETER = 10;

    private Trail trail;

    public MouseTrailPanel() {
        super();
        trail = new Trail();
        MouseTrailListener listener = new MouseTrailListener(trail, this);
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        List<Point> points = trail.getTrail();

        int red, green, blue;

        if (trail.isMousePressed()) {
            red = 255;
            green = 0;
            blue = 0;
        } else {
            red = 255;
            green = 255;
            blue = 255;
        }

        for (int i = 0; i < points.size(); i++) {
            Point point = points.get(i);
            double proportion = i / (double) points.size();
            Color c = new Color((int) (red * (1 - proportion)),
                    (int) (green * (1 - proportion)),
                    (int) (blue * (1 - proportion)));
            g.setColor(c);
            g.fillOval((int) point.getX(), (int) point.getY(), CIRCLE_DIAMETER,
                    CIRCLE_DIAMETER);
        }

    }



}

class MouseTrailListener implements MouseMotionListener, MouseListener {

    private Trail trail;
    private MouseTrailPanel panel;

    public MouseTrailListener(Trail trail, MouseTrailPanel panel) {
        this.trail = trail;
        this.panel = panel;
    }

    private void addPoint(MouseEvent e) {
        trail.addPoint(new Point(e.getX(), e.getY()));
        panel.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        addPoint(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        addPoint(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        trail.mousePressed();
        panel.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        trail.mouseReleased();
        panel.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        trail.clear();
        panel.repaint();
    }

}
