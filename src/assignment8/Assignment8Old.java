package assignment8;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;
import java.applet.Applet;
import java.awt.*;

public class Assignment8Old extends Applet {
    public static void main(String[] args) {
        new MainFrame(new Assignment8Old(), 640, 480);
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
        TransformGroup spin = new TransformGroup();
        spin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        root.addChild(spin);
        //object
        Appearance ap = new Appearance();
        ap.setMaterial(new Material());
        PolygonAttributes pa = new PolygonAttributes();
        pa.setBackFaceNormalFlip(true);
        pa.setCullFace(PolygonAttributes.CULL_NONE);
        ap.setPolygonAttributes(pa);
        Shape3D shape = new Shape3D(createMobius(), ap);
        //transformation
        Transform3D tr = new Transform3D();
        tr.setScale(0.4);
        TransformGroup tg = new TransformGroup(tr);
        spin.addChild(tg);
        tg.addChild(shape);
        Alpha alpha = new Alpha(-1, 10000);
        RotationInterpolator rotator = new RotationInterpolator(alpha, spin);
        BoundingSphere bounds = new BoundingSphere();
        bounds.setRadius(100);
        rotator.setSchedulingBounds(bounds);
        spin.addChild(rotator);
        //light
        PointLight light = new PointLight(new Color3f(Color.white),
                new Point3f(0.5f,0.5f,1f),
                new Point3f(1f,0.2f,0f));
        light.setInfluencingBounds(bounds);
        root.addChild(light);
        //background
        Background background = new Background(1.0f, 1.0f, 1.0f);
        background.setApplicationBounds(bounds);
        root.addChild(background);
        return root;
    }

    private Geometry createMobius(){
        int m = 100; //number of row points
        int n = 100; //number of col points
        int p = 4*((m-1)*(n-1)); //faces * points per face

        IndexedQuadArray iqa = new IndexedQuadArray(m*n,
                GeometryArray.COORDINATES, p);
        Point3f[] vertices = new Point3f[m*n];
        int count = 0;

        //Create vertices
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                float u = (float)(i * (2 * (Math.PI)) / (m - 1));
                float v = (float)(-0.3 + (j * (0.6/(n-1))));
                float x = (float)((1 + v * Math.cos(u / 2)) * Math.cos(u));
                float y = (float)((1 + v * Math.cos(u / 2)) * Math.sin(u));
                float z = (float)(v * Math.sin(u / 2));
                vertices[count]=new Point3f(x,y,z);
                count++;
            }//close nested for loop
        }//close for loop

        iqa.setCoordinates(0, vertices);
        count = 0;

        //set count for coordinates
        for(int i = 0; i < m-1; i++){
            for(int j = 0; j < n-1; j++){
                iqa.setCoordinateIndex(count++, i*m+j);
                iqa.setCoordinateIndex(count++, i*m+j+1);
                iqa.setCoordinateIndex(count++, (i+1)*m+j+1);
                iqa.setCoordinateIndex(count++, (i+1)*m+j);
            }
        }

        //create geometry info and generate normals for shape
        GeometryInfo gi = new GeometryInfo(iqa);
        NormalGenerator ng = new NormalGenerator();
        ng.generateNormals(gi);
        return gi.getGeometryArray();
    }
}
