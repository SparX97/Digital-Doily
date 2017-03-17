import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by SPAS on 17/03/2017.
 */
public class Worker extends SwingWorker<Void, Void> {

    private DrawingPanel panelRef;
    private Random rand;

    public Worker(DrawingPanel canvas) {
        panelRef = canvas;
        rand = new Random();
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
        float red = rand.nextFloat();
        float green = rand.nextFloat();
        float blue = rand.nextFloat();

        panelRef.setColor(new Color(red, green, blue));
    }
}
