import javax.swing.*;

/**
 * Created by SPAS on 17/03/2017.
 */
public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        DoilieFrame myFrame = new DoilieFrame("Doilie");
                        myFrame.initialise();
                    }
                }
        );
    }
}
