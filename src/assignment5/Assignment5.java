package assignment5;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

public class Assignment5 extends JPanel{
    Shape eagle;
    Shape ellipse;
    Shape blueRectOne;
    Shape blueRectTwo;
    Shape goldRect;
    Color gold = new Color(212,175,55);
    AffineTransform tx = new AffineTransform();
    boolean isTimerActive;

    public Assignment5() {
        isTimerActive = true;
        eagle = new Eagle();
        ellipse = new Ellipse2D.Double(140, 225, 580, 580);
        blueRectOne = new Rectangle2D.Double(130, 225, 600, 250);
        blueRectTwo = new Rectangle2D.Double(700, 425, 100, 150);
        goldRect = new Rectangle2D.Double(130, 460, 600, 350);
        setPreferredSize(new Dimension(850, 1000));
        setBackground(Color.white);
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tx.rotate(0.0349066, 435, 515); //anchorx = ellipse width/2 + ellipse x origin, anchory = ellipse height/2 + ellipse y origin
                repaint();
            }
        });
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ev) { //starting/stopping rotation
                if (isTimerActive) {
                    isTimerActive = false;
                    timer.stop();
                } else {
                    isTimerActive = true;
                    timer.start();
                }
                repaint();
            }
        });
        timer.start();
    }

    public void saveImage(String filename) {
        BufferedImage image = new BufferedImage(850, 1000, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        paintComponent(g2);
        try{
            ImageIO.write(image, "png", new File(filename));
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setTransform(tx);
        g2.setClip(ellipse);
        g2.setPaint(Color.BLUE);
        g2.fill(blueRectOne);
        g2.setPaint(gold);
        g2.fill(goldRect);
        g2.setPaint(Color.BLUE);
        g2.fill(blueRectTwo);
        g2.setPaint(Color.WHITE);
        g2.fill(eagle);
    }

    public static void main(String s[]) {
        JFrame frame = new JFrame();
        Container cp = frame.getContentPane();
        cp.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Assignment5 panel = new Assignment5();;
        cp.add(panel, BorderLayout.CENTER);
        JButton saveButton = new JButton("Save Image");
        saveButton.addActionListener(e -> {panel.saveImage("eagle.png");});
        cp.add(saveButton, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
    }
}
