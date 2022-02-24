package demo2d;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Demo2D extends JPanel {
    Shape shape;
    AffineTransform tx = new AffineTransform();
    public Demo2D() {
        shape = createShape();
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //tx.translate(5,5);
                tx.rotate(Math.PI/60);
                repaint();
            }
        });
        timer.start();
    }

    public void saveImage(String filename) {
        BufferedImage image = new BufferedImage(512, 512, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        paintComponent(g2);
        try{
            ImageIO.write(image, "png", new File(filename));
        } catch (Exception e){
            e.printStackTrace();
        }

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
        //AffineTransform tx = new AffineTransform();
        //tx.setToScale(-1,1);//setToTranslation(150, 50);//setToRotation(Math.PI/6); //rotates 30 degrees
        //tx.rotate(Math.PI/6);
        //tx.translate(-150, -50);
        g2.setTransform(tx);
        g2.fill(shape);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame(); //main display frame
        Container cp = frame.getContentPane();
        cp.setLayout(new BorderLayout());
        Demo2D demo = new Demo2D();
        cp.add(demo, BorderLayout.CENTER);
        JButton but = new JButton("capture and save");
        cp.add(but, BorderLayout.NORTH);
        but.addActionListener(e -> {demo.saveImage("demo.png");});
        //frame.getContentPane().add();
        frame.setBounds(100, 100 , 500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
