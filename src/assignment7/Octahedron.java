package assignment7;

import javax.media.j3d.GeometryArray;
import javax.media.j3d.IndexedTriangleArray;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;

/**
 *
 * @author 
 */
public class Octahedron extends IndexedTriangleArray {

    public Octahedron() {
        super(6, GeometryArray.COORDINATES, 24); // 6 vertices, 3*8 indices
        //Defining coordinates
        Point3d[] coords = {
                new Point3d(0,0,1),
                new Point3d(-1,0,0),
                new Point3d(0,-1,0),
                new Point3d(1,0,0),
                new Point3d(0,1,0),
                new Point3d(0,0,-1)
        };

        //Setting coordinates of Octahedron
        setCoordinates(0, coords);
        setCoordinateIndex(0, 0);
        setCoordinateIndex(1, 1);
        setCoordinateIndex(2, 2);
        setCoordinateIndex(3, 0);
        setCoordinateIndex(4, 2);
        setCoordinateIndex(5, 3);
        setCoordinateIndex(6, 0);
        setCoordinateIndex(7, 3);
        setCoordinateIndex(8, 4);
        setCoordinateIndex(9, 5);
        setCoordinateIndex(10, 1);
        setCoordinateIndex(11, 2);
        setCoordinateIndex(12, 5);
        setCoordinateIndex(13, 2);
        setCoordinateIndex(14, 3);
        setCoordinateIndex(15, 5);
        setCoordinateIndex(16, 3);
        setCoordinateIndex(17, 4);
        setCoordinateIndex(15, 5);
        setCoordinateIndex(16, 4);
        setCoordinateIndex(17, 1);
    }
}
