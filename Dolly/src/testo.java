/**
 * Created by SPAS on 12/03/2017.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class testo {

    public static void main(String[] args){
        RFrame bf = new RFrame("Overriding update");
        bf.init();
    }

}

class RFrame extends Frame {

    int count = 20;

    public RFrame(String title){
        super(title);
    }

    public void init(){

        Button b = new Button("Hit me");
        setLayout(new BorderLayout());
        add(b, BorderLayout.SOUTH);
        b.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        repaint();
                    }
                }
        );

        setSize(200,300);
        setVisible(true);
    }

    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.blue);
        g.fillRect(0,count,200,20);
    }

    public void update(Graphics g){
        g.setColor(Color.red);
        g.fillRect(0,count,200,20);
        count = count + 20;
    }
}
