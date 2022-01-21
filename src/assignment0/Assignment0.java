package assignment0;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Assignment0 extends JFrame {

    MyPanel mypanel = new MyPanel();
    public Assignment0() {
        Container cp = this.getContentPane();
        cp.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,3));
        JButton button = new JButton("Click me");
        /*button.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                mypanel.setBackground(Color.BLUE);
                System.out.println("button clicked");
            }
        });*/
        button.addActionListener(e-> {
            mypanel.x += 10;
            mypanel.y += 10;
            mypanel.repaint();
            int r = (int) (Math.random()*256);
            int g = (int) (Math.random()*256);
            int b = (int) (Math.random()*256);
            mypanel.color = new Color(r,g,b);
            //mypanel.setBackground(Color.BLUE);
            System.out.println("button clicked");
        });
        panel.add(button);
        panel.add(new JLabel("2"));
        panel.add(new JLabel("3"));
        panel.add(new JLabel("4"));
        panel.add(new JLabel("5"));
        panel.add(new TextField());

        cp.add(panel, BorderLayout.SOUTH);
        JLabel label = new JLabel("a label");
        cp.add(label, BorderLayout.NORTH);

        cp.add(new JLabel("West"), BorderLayout.WEST);
        cp.add(new JLabel("East"), BorderLayout.EAST);

        //mypanel.setBackground(Color.red);
        cp.add(mypanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        JFrame frame = new Assignment0(); //JFrame
        frame.setBounds(100, 200, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //if you close the window, you will exit the program
        frame.setVisible(true);
    }
}
