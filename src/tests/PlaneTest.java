package tests;

import geometries.Plane;
import org.junit.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlaneTest {

    Plane plane1 = new Plane(new Point3D(new Coordinate(0), new Coordinate(0), new Coordinate(-1)), new Vector(new Point3D(new Coordinate(0), new Coordinate(0), new Coordinate(1))));
    Plane plane2 = new Plane(new Point3D(new Coordinate(0), new Coordinate(0), new Coordinate(-1)), new Vector(new Point3D(new Coordinate(0), new Coordinate(1), new Coordinate(0))));
    Plane plane3 = new Plane(new Point3D(new Coordinate(0), new Coordinate(1), new Coordinate(-1)), new Vector(new Point3D(new Coordinate(0), new Coordinate(1), new Coordinate(0))));
    Plane plane4 = new Plane(new Point3D(new Coordinate(0), new Coordinate(1), new Coordinate(5)), new Vector(new Point3D(new Coordinate(0), new Coordinate(1), new Coordinate(0))));

    Coordinate x0 = new Coordinate(0);
    Coordinate y0 = new Coordinate(0);
    Coordinate z0 = new Coordinate(0);
    Coordinate z1 = new Coordinate(-1);


    Point3D p0 = new Point3D(x0, y0, z0);
    Point3D p1 = new Point3D(x0, y0, z1);
    Vector v001 = new Vector(p1);
    Ray ray1 = new Ray(p0, v001);

    @Test
    public void findIntersections() {
        //if plane is perpendicular to camera
        ArrayList<Point3D> l = new ArrayList<Point3D>(plane1.findIntersections(ray1));
        assertEquals("find intersection failed for plane perpendicular to ray", l.size(), 1, 1e-10);
        //if plane is before the camera
        ArrayList<Point3D> l4 = new ArrayList<Point3D>(plane4.findIntersections(ray1));
        assertEquals("find intersection failed for plane perpendicular to ray", l4.size(), 0, 1e-10);
        //if plane is parallel to camera
        ArrayList<Point3D> l2 = new ArrayList<Point3D>(plane2.findIntersections(ray1));
        assertEquals("find intersection failed for plane parallel", l2.size(), 0, 1e-10);

        ArrayList<Point3D> l3 = new ArrayList<Point3D>(plane3.findIntersections(ray1));
        assertEquals("find intersection failed for plane parallel", l3.size(), 0, 1e-10);
    }
}