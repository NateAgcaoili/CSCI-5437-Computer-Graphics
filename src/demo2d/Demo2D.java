package demo2d;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class Demo2D extends JPanel {
    Shape shape;

    public Demo2D() {
        shape = createShape();
    }

    public Shape createShape() {
        /*Shape s1 = new Ellipse2D.Double(20, 30, 100, 120);
        Shape s2 = new Rectangle2D.Double(50, 50, 100, 100);
        Area a1 = new Area(s1);
        Area a2 = new Area(s2);
        a1.exclusiveOr(a2);
        return a1;*/
        Path2D p = new Path2D.Double();
        p.moveTo(10, 10);
        p.lineTo(120, 200);
        p.lineTo(50, 150);
        p.closePath();
        p.moveTo(200, 100);
        p.lineTo(300, 300);
        p.lineTo(250, 150);
        p.closePath();
        return p;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g; //casting
        g2.setColor(Color.BLUE);
        g2.fill(shape);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame(); //main display frame
        frame.getContentPane().add(new Demo2D());
        frame.setBounds(100, 100 , 500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
