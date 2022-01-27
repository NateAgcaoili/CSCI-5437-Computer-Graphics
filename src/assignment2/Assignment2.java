package assignment2;

/**
 *
 * @author Nate Agcaoili
 */

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class Assignment2 extends JPanel {

    public static Shape createYingYang() {
        //initializing basic shapes
        Shape outline = new Ellipse2D.Double(-102, -102, 204, 204);
        Shape outlineSubtract = new Ellipse2D.Double(-100, -100, 200, 200);
        Shape mainBlack = new Ellipse2D.Double(-100, -100, 200, 200);
        Shape mainBlackSubtract = new Rectangle2D.Double(-100,-100,100,200);
        Shape topWhite = new Ellipse2D.Double(-50,-100,100,100);
        Shape littleBlack = new Ellipse2D.Double(0,-55,10,10);
        Shape bottomBlack = new Ellipse2D.Double(-50,0,100,100);
        Shape littleWhite = new Ellipse2D.Double(0,50,10,10);

        //initializing areas
        Area a1 = new Area(outline);
        Area a2 = new Area(outlineSubtract);
        Area a3 = new Area(mainBlack);
        Area a4 = new Area(mainBlackSubtract);
        Area a5 = new Area(topWhite);
        Area a6 = new Area(bottomBlack);
        Area a7 = new Area(littleBlack);
        Area a8 = new Area(littleWhite);

        //performing constructive area functions
        a1.subtract(a2);
        a3.subtract(a4);
        a1.add(a3);
        a1.subtract(a5);
        a1.add(a6);
        a1.add(a7);
        a1.subtract(a8);

        //returning final product
        return a1;
    }

    public static Shape createSpirograph() {
        //initializing variables
        Path2D spgPath = new Path2D.Double(Path2D.WIND_EVEN_ODD);
        double r1 = 30;
        double r2 = 40;
        double p = 60;
        double x;
        double y;

        //moving path to center
        spgPath.moveTo(0, 0);

        //drawing the spirograph
        for(double t = 0.0; t <= 8*Math.PI; t+=0.1) {
            x = (r1 + r2) * Math.cos(t) - p * Math.cos(((r1 + r2) / r2) * t);
            y = (r1 + r2) * Math.sin(t) - p * Math.sin(((r1 + r2) / r2) * t);
            spgPath.lineTo(x, y);
        }

        //returning final product
        return spgPath;
    }
}
