package assignment1;

import javax.swing.*;
import java.awt.*;

public class Assignment1 extends JFrame {
    AssignmentOnePanel shape = new AssignmentOnePanel();

    public Assignment1() {
        Container cp = this.getContentPane();
        cp.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        //Initializing buttons
        JRadioButton redRadio = new JRadioButton("Red");
        JRadioButton greenRadio = new JRadioButton("Green");
        JRadioButton blueRadio = new JRadioButton("Blue");
        ButtonGroup radios = new ButtonGroup();
        radios.add(redRadio);
        radios.add(greenRadio);
        radios.add(blueRadio);
        redRadio.setSelected(true);
        JButton leftButton = new JButton("  <  ");
        JButton rightButton = new JButton("  >  ");
        JButton upButton = new JButton("  ^  ");
        JButton downButton = new JButton("  v  ");

        //Setting button functions
        redRadio.addActionListener(e-> {
            shape.currentColor = 0;
            shape.repaint();
        });

        greenRadio.addActionListener(e-> {
            shape.currentColor = 1;
            shape.repaint();
        });

        blueRadio.addActionListener(e-> {
            shape.currentColor = 2;
            shape.repaint();
        });

        leftButton.addActionListener(e-> {
            shape.x -= 5;
            shape.repaint();
        });

        rightButton.addActionListener(e-> {
            shape.x += 5;
            shape.repaint();
        });

        upButton.addActionListener(e-> {
            shape.y -= 5;
            shape.repaint();
        });

        downButton.addActionListener(e-> {
            shape.y += 5;
            shape.repaint();
        });

        //Composing GUI components
        panel.add(Box.createRigidArea(new Dimension(0,50)));
        panel.add(redRadio);
        panel.add(greenRadio);
        panel.add(blueRadio);
        panel.add(Box.createRigidArea(new Dimension(0,50)));
        panel.add(leftButton);
        panel.add(rightButton);
        panel.add(upButton);
        panel.add(downButton);
        panel.setMinimumSize(new Dimension(100, 0));
        cp.add(panel, BorderLayout.EAST);
        cp.add(shape, BorderLayout.CENTER);
    }

    //Main method
    public static void main(String[] args) {
        JFrame frame = new Assignment1();
        frame.setTitle("Nate Agcaoili");
        frame.setBounds(100, 200, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
