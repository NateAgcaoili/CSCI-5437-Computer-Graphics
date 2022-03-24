package assignment6;

import java.awt.*;
import java.applet.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.applet.MainFrame;

public class Assignment6 extends Applet {
    public static void main(String[] args) {
        System.setProperty("sun.awt.noerasebackground", "true");
        new MainFrame(new Assignment6(), 640, 480);
    }

    public void init() {
        GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
        Canvas3D cv = new Canvas3D(gc);
        setLayout(new BorderLayout());
        add(cv, BorderLayout.CENTER);
        BranchGroup bg = createSceneGraph();
        bg.compile();
        SimpleUniverse su = new SimpleUniverse(cv);
        su.getViewingPlatform().setNominalViewingTransform();
        su.addBranchGraph(bg);
    }

    private BranchGroup createSceneGraph() {
        BranchGroup root = new BranchGroup();
        //Shape3D text declariations
        Appearance ap = new Appearance();
        ap.setMaterial(new Material());
        Font3D font = new Font3D(new Font("SansSerif", Font.PLAIN, 1),
                new FontExtrusion());
        //Firstname Shape3D object declaration and transformation
        Text3D fnameText = new Text3D(font, "Nate");
        Shape3D fnameShape = new Shape3D(fnameText, ap);
        Transform3D fnameTr = new Transform3D();
        fnameTr.setScale(0.5);
        fnameTr.setTranslation(new Vector3f(-0.55f, 0.1f, 0f));
        TransformGroup fnameTg = new TransformGroup(fnameTr);
        //Last name Shape3D object declaration and transformation
        Text3D lnameText = new Text3D(font, "Agcaoili");
        Shape3D lnameShape = new Shape3D(lnameText, ap);
        Transform3D lnameTr = new Transform3D();
        lnameTr.setScale(0.35);
        lnameTr.setTranslation(new Vector3f(-0.64f, -0.3f, 0f));
        TransformGroup lnameTg = new TransformGroup(lnameTr);
        //Applying transformations to Shape3D name objects
        fnameTg.addChild(fnameShape);
        lnameTg.addChild(lnameShape);
        //Adding transform groups to branch group
        root.addChild(fnameTg);
        root.addChild(lnameTg);
        //Setting lighting
        PointLight light = new PointLight(new Color3f(Color.white),
                new Point3f(1f,1f,1f),
                new Point3f(1f,0.1f,0f));
        BoundingSphere bounds = new BoundingSphere();
        light.setInfluencingBounds(bounds);
        root.addChild(light);
        //Setting background to blue
        Background background = new Background(new Color3f(0,0,1f));
        BoundingSphere backgroundSphere = new BoundingSphere(new Point3d(0,0,0), 100000);
        background.setApplicationBounds(backgroundSphere);
        root.addChild(background);
        return root;
    }
}
