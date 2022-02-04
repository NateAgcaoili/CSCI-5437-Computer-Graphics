package demo2d;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

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
        Path2D p = new Path2D.Double(Path2D.WIND_NON_ZERO);
        double t = 0;
        p.moveTo(280, 100);
        int n = 200;
        for (int i = 1; i < n; i++) {
            t += 2 * Math.PI / n;
            double x = 200 + 80 * Math.cos(t);
            double y = 100 + 100 * Math.sin(t);
            p.lineTo(x, y);
        }
        p.closePath();

        p.moveTo(10, 10);
        p.lineTo(50, 150);
        p.lineTo(230, 200);
        //p.quadTo(200, 100, 50, 200);

        p.closePath();

        /*p.moveTo(200, 100);
        p.curveTo(200, 200, 50, 300, 100, 150);
        //p.lineTo(300, 300);
        p.lineTo(250, 150);*/
        return p;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g; //casting
        //g2.setColor(new Color(0.5f, 0.7f, 0.2f));
        g2.setPaint(new Color(0.5f, 0.7f, 0.2f));
        AffineTransform tx = new AffineTransform();
        tx.setToRotation(Math.PI/6); //rotates 30 degrees
        tx.translate(50, 50);
        g2.setTransform(tx);
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
