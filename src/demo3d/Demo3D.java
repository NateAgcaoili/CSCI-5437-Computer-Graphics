
package demo3d;

/**
 *
 * @author Hong Zhang
 */
import javax.vecmath.*;
import java.awt.*;
import java.awt.event.*;
import javax.media.j3d.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import java.applet.*;
import com.sun.j3d.utils.applet.MainFrame;

public class Demo3D extends Applet {

    public static void main(String[] args) {
        System.setProperty("sun.awt.noerasebackground", "true"); 
        new MainFrame(new Demo3D(), 640, 480);
    }

    public void init() {
        // create canvas
        GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
        Canvas3D cv = new Canvas3D(gc);
        setLayout(new BorderLayout());
        add(cv, BorderLayout.CENTER);
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
        Alpha alpha = new Alpha(-1, 4000);
        RotationInterpolator rotator = new RotationInterpolator(alpha, spin);
        BoundingSphere bounds = new BoundingSphere();
        rotator.setSchedulingBounds(bounds);
        spin.addChild(rotator);
        return root;
    }
    
    private Geometry createGeometry() {
        TriangleArray geom = new TriangleArray(9, GeometryArray.COORDINATES);
        Point3d[] coords = {new Point3d(0,0,0),
            new Point3d(-0.9,-0.7,0),
            new Point3d(-0.3,0.8,0),
            new Point3d(0,0,0),
            new Point3d(0.4,-0.5,0.5),
            new Point3d(0.8,0.5,0.6)};
            
        geom.setCoordinates(0, coords);
        geom.setCoordinate(6, coords[0]);
        geom.setCoordinate(7, coords[2]);
        geom.setCoordinate(8, coords[5]);
        return geom;
    }
}
