import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class FirstGui {

    static class sFrame extends JFrame {

        private int count = 30;

        public sFrame(String title){
            super(title);
        }

        void myInit(){
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(720, 480);
            this.setVisible(true);

            JPanel thePanel = new JPanel();
            JButton buttonOne = new JButton("first of many");
            JButton buttonTwo = new JButton("2");
            JButton buttonThree = new JButton("3");
            JButton buttonFour = new JButton("4");
            JButton buttonFive = new JButton("5");
            JButton buttonSix = new JButton("6");
            JButton buttonSeven = new JButton("7");
            JButton buttonEight = new JButton("8");
            JButton buttonNine = new JButton("Big nine button");
            JButton buttonTen = new JButton("10");
            JButton buttonEleven = new JButton("11");


            JCheckBox red = new JCheckBox("my RED");
            JCheckBox bold = new JCheckBox( "my BOLD");




            thePanel.setLayout(new BorderLayout());

            JPanel colorPanel = new JPanel();

            colorPanel.add(red);
            colorPanel.add(bold);

            red.addItemListener(new myItemListener(colorPanel));
            bold.addItemListener(new myItemListener(colorPanel));


            JPanel left = new JPanel();
            JButton myTrigger = new JButton("MOOOVE");
            left.add(myTrigger);
//            left.paint();
            myTrigger.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    repaint();
                }
            }
            );

            JPanel up = new JPanel();
            JPanel down = new JPanel();


            thePanel.add(up,BorderLayout.NORTH);
            thePanel.add(down,BorderLayout.SOUTH);
            thePanel.add(colorPanel,BorderLayout.CENTER);
            thePanel.add(left,BorderLayout.WEST);


            up.add(buttonOne);
            up.add(buttonTwo);
            up.add(buttonThree);
            up.add(buttonFour);
            up.add(buttonFive);
            up.add(buttonSix);
            up.add(buttonSeven);
            up.add(buttonEight);
            up.add(buttonNine);
            up.add(buttonTen);
            up.add(buttonEleven);

            buttonOne.addActionListener(new ListenerIGuess());

//        thePanel.add(buttonOne, BorderLayout.NORTH);
//        thePanel.add(buttonTwo, BorderLayout.SOUTH);
//        thePanel.add(buttonThree, BorderLayout.WEST);
//        thePanel.add(buttonFour, BorderLayout.EAST);
//        thePanel.add(buttonFive, BorderLayout.CENTER);

            for (int i = 0; i < 20; i++) {
                down.add(new JButton("buttonNumber: " + i));
            }

//        thePanel.setLayout(new BoxLayout(thePanel,BoxLayout.PAGE_AXIS));
//
//        JButton b1 = new JButton("Dr Snuffleupagus");
//
//        JButton b2 = new JButton("Elmo");
//        b2.setAlignmentX(Component.RIGHT_ALIGNMENT);
//        JButton b3 = new JButton("Oscar the Grouch");
//        b3.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        thePanel.add(b1);
//        thePanel.add(Box.createVerticalGlue());
//        thePanel.add(b2);
//        thePanel.add(Box.createRigidArea( new Dimension(0,30)));
//        thePanel.add(b3);

            this.setContentPane(thePanel);
            this.pack();
        }

        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(Color.black);
            g.fillRect(10, 100,50,50);
        }

        public void update(Graphics g) {
            g.setColor(Color.YELLOW);
            g.drawRect( 15,count,75,75);
            count = count + 20;
        }
    }


    public static void main(String[] args) {

        sFrame window = new sFrame("aint nobody have time for a title");
        window.myInit();

    }
}