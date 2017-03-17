import java.awt.*;

/**
 * Created by SPAS on 12/03/2017.
 */
//to draw continues strokes the program connects lines
public class Line {

    private int oldX;
    private int oldY;
    private int newX;
    private int newY;
    private Color lineColor;
    private int lineSize;


    public Line(int x1, int y1, int x2, int y2) {
        oldX = x1;
        oldY = y1;
        newX = x2;
        newY = y2;
    }

    public int getOldX() {
        return oldX;
    }

    public int getOldY() {
        return oldY;
    }

    public int getNewX() {
        return newX;
    }

    public int getNewY() {
        return newY;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color newColor) {
        lineColor = newColor;
    }

    public int getLineSize() {
        return lineSize;
    }

    public void setLineSize(int newSize) {
        lineSize = newSize;
    }
}
