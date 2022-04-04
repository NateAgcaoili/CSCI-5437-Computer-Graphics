
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Geometry;
import javax.media.j3d.Material;
import javax.media.j3d.PointLight;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hzhang
 */
public class EllipticParabloid extends Applet {
    public static void main(String[] args) {
        new MainFrame(new EllipticParabloid(), 640, 480);
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
        Shape3D shape = new Shape3D(createGeometry(), ap);
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
    
    private Geometry createGeometry() {
        int m = 100;
        int n = 30;
        float vmin = 0f;
        float vmax = 1f;
        float umin = 0.0f;
        float umax = (float) (2*Math.PI);
        float du = (umax-umin)/m;
        float dv = (vmax-vmin)/n;
        
        Point3f[] verts = new Point3f[(m+1)*(n+1)];
        int count = 0;
        // vertices
        float u = umin;
        for (int i = 0; i <= m; i++) {
            float v = vmin;
            for (int j = 0; j <= n; j++) {
                float x = (float) (v*Math.cos(u));
                float y = (float) (v*Math.sin(u));
                float z = (float) (v*v);
                verts[count] = new Point3f(x, y, z);
               
                count++;
                v += dv;
            }
            u += du;
        }
        // indices
        int[] inds = new int[4*m*n];
        count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                inds[count++] = i*(n+1) + j;
                inds[count++] = (i+1)*(n+1) + j;
                inds[count++] = (i+1)*(n+1) + j+1;
                inds[count++] = i*(n+1) + j+1;
            }
        }
        
        GeometryInfo gi = new GeometryInfo(GeometryInfo.QUAD_ARRAY);
        gi.setCoordinates(verts);
        gi.setCoordinateIndices(inds);
        NormalGenerator ng = new NormalGenerator();
        ng.generateNormals(gi);
        return gi.getGeometryArray();
    }

}
