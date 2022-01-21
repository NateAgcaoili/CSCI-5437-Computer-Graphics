package assignment0;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyPanel extends JPanel {
    int x = 50;
    int y = 100;
    Color color = Color.LIGHT_GRAY;

    public MyPanel() {
        this.addMouseListener(new MouseAdapter() {
           @Override
            public void mouseClicked(MouseEvent e) {
               int xm = e.getX();
               int ym = e.getY();
               x = xm-40;
               y = ym-20;
               repaint();
           }
        });
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(color);
        g.fillOval(x,y,80,40);


    }
}
