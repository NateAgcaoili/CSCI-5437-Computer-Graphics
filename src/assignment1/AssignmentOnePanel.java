package assignment1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AssignmentOnePanel extends JPanel {
    //Initializing variables
    int x = 50;
    int y = 50;
    int diamater = 50;
    Color[] colors = new Color[]{Color.RED, Color.GREEN, Color.BLUE};
    int currentColor = 0;

    //Object constructor
    public AssignmentOnePanel() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int xm = e.getX();
                int ym = e.getY();
                x = xm - diamater / 2;
                y = ym - diamater / 2;
                repaint();
            }
        });
    }

    //Painting shape
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(colors[currentColor]);
        g.fillOval(x, y, diamater, diamater);
        g.setColor(Color.BLACK);
        g.drawLine(x + 25, y - 10, x + 25, y + 60);
        g.drawLine(x - 10, y + 25, x + 60, y + 25);
        g.drawOval(x, y, diamater, diamater);
    }
}
