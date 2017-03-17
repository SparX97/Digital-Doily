import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by SPAS on 17/03/2017.
 */
public class Worker extends SwingWorker<Void, Void> {

    float red, green, blue;
    private DrawingPanel panelRef;

    public Worker(DrawingPanel canvas) {
        panelRef = canvas;
    }

    //returns calls until thread is canceled
    protected Void doInBackground() throws Exception {
        while (true) {
            process();
            Thread.sleep(50);
        }
    }

    //handles generating the random color
    protected void process() {
        Random rand = new Random();

        red = rand.nextFloat();
        green = rand.nextFloat();
        blue = rand.nextFloat();

        panelRef.setColor(new Color(red, green, blue));
    }
}
