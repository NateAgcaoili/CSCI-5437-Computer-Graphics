
package demo3d;

/**
 *
 * @author Hong Zhang
 */

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.Point3d;
import javax.vecmath.Quat4d;
import javax.vecmath.Vector3d;
import java.applet.Applet;
import java.awt.*;

public class Demo3DJFrame extends JFrame {

    public static void main(String[] args) {
        System.setProperty("sun.awt.noerasebackground", "true"); 
        //new MainFrame(new Demo3DJFrame(), 640, 480);
        JFrame frame = new Demo3DJFrame();
        frame.setBounds(100, 100, 640, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public Demo3DJFrame() {
        // create canvas
        GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
        Canvas3D cv = new Canvas3D(gc);
        Container cp = this.getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(cv, BorderLayout.CENTER);
        BranchGroup bg = createSceneGraph();
        bg.compile();
        SimpleUniverse su = new SimpleUniverse(cv);
        su.getViewingPlatform().setNominalViewingTransform();
        su.addBranchGraph(bg);
//        cv.getView().setMinimumFrameCycleTime(20);
    }

    private BranchGroup createSceneGraph() {
        BranchGroup root = new BranchGroup();
        // transform to rotate the object
        TransformGroup spin = new TransformGroup();
        spin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        root.addChild(spin);
        //object
        Appearance ap = new Appearance();
        PolygonAttributes pa = new PolygonAttributes();
        pa.setPolygonMode(PolygonAttributes.POLYGON_LINE); // PolygonAttributes.CULL_NONE, 0);
        pa.setCullFace(PolygonAttributes.CULL_NONE);
        ap.setPolygonAttributes(pa);
        Shape3D shape = new Shape3D(createGeometry(), ap);
        // transform for a better view
        Transform3D tr = new Transform3D();
        tr.set(new Quat4d(), new Vector3d(0,-0.2, 0), 0.25);
        TransformGroup tg = new TransformGroup(tr);
        spin.addChild(tg);
        tg.addChild(shape);
        // animation - rotation
        /*Alpha alpha = new Alpha(-1, 4000);
        RotationInterpolator rotator = new RotationInterpolator(alpha, spin);
        BoundingSphere bounds = new BoundingSphere();
        rotator.setSchedulingBounds(bounds);
        spin.addChild(rotator);*/
        return root;
    }
    
    private Geometry createGeometry() {
        /*QuadArray geom = new QuadArray(4, GeometryArray.COORDINATES);
        geom.setCoordinate(0, new Point3d(0, 0,0));
        geom.setCoordinate(1, new Point3d(0, 1,0));
        geom.setCoordinate(2, new Point3d(1, 0,0));
        geom.setCoordinate(3, new Point3d(1, 1,0));*/
        IndexedTriangleArray geom = new IndexedTriangleArray(5, GeometryArray.COORDINATES, 9);
        Point3d[] coords = {new Point3d(0,0,0),
            new Point3d(-0.9,-0.7,0),
            new Point3d(-0.3,0.8,0),
            new Point3d(0.4,-0.5,0.5),
            new Point3d(0.8,0.5,0.6)};

        geom.setCoordinates(0, coords);
        geom.setCoordinateIndex(0, 0);
        geom.setCoordinateIndex(1, 1);
        geom.setCoordinateIndex(2, 2);
        geom.setCoordinateIndex(3, 0);
        geom.setCoordinateIndex(4, 3);
        geom.setCoordinateIndex(5, 4);
        geom.setCoordinateIndex(6, 0);
        geom.setCoordinateIndex(7, 2);
        geom.setCoordinateIndex(8, 4);
        /*geom.setCoordinate(6, coords[0]);
        geom.setCoordinate(7, coords[2]);
        geom.setCoordinate(8, coords[5]);*/
        /*Point3d[] coords = {new Point3d(0,0,1),
                new Point3d(-1,0,0),
                new Point3d(0,-1,0),
                new Point3d(1,0,0),
                new Point3d(0,1,0),
                new Point3d(0,0,-1)};
        geom.setCoordinates(0, coords);
        geom.setCoordinate(7, coords[0]);*/
        return geom;
    }
}
