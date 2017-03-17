import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by SPAS on 17/03/2017.
 */
public class Worker extends SwingWorker<Void, Void> {

    private DrawingPanel panelRef;

    public Worker(DrawingPanel canvas) {
        panelRef = canvas;
    }

    protected Void doInBackground() throws Exception {
        while (true){

            process();
            Thread.sleep(50);
        }


    }

    protected void process(){

        Random rand = new Random();

        float red = rand.nextFloat();
        float green = rand.nextFloat();
        float blue = rand.nextFloat();

        panelRef.setColor(new Color(red, green, blue));

    }
}
