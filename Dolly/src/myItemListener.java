import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by SPAS on 12/03/2017.
 */
public class myItemListener implements ItemListener {

    public JPanel theColorPanel;

    public myItemListener(JPanel theColorPanel){
        this.theColorPanel = theColorPanel;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED) {
            theColorPanel.setBackground(Color.red);
        }
        else
            theColorPanel.setBackground(Color.blue);
    }
}
