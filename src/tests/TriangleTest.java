package tests;

import geometries.Triangle;
import org.junit.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TriangleTest {
    Triangle t1 = new Triangle(new Point3D(new Coordinate(0), new Coordinate(0), new Coordinate(-3)),
                            new Point3D(new Coordinate(5),new Coordinate( -5), new Coordinate(-3)),
                            new Point3D(new Coordinate(-5),new Coordinate( -5),new Coordinate( -3)));

    Triangle t2 = new Triangle(new Point3D(new Coordinate(0), new Coordinate(10), new Coordinate(-3)),
            new Point3D(new Coordinate(-10),new Coordinate( -10), new Coordinate(-3)),
            new Point3D(new Coordinate(10),new Coordinate( -10),new Coordinate( -3)));

    Triangle t3 = new Triangle(new Point3D(new Coordinate(-9), new Coordinate(-10), new Coordinate(-3)),
            new Point3D(new Coordinate(-10),new Coordinate( -9), new Coordinate(-3)),
            new Point3D(new Coordinate(-11),new Coordinate( -11),new Coordinate( -3)));

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

        ArrayList<Point3D> l = new ArrayList<Point3D>(t1.findIntersections(ray1));
        assertEquals("find intersection failed for triangle perpendicular to ray", l.size(), 1, 1e-10);

           ArrayList<Point3D> l2 = new ArrayList<Point3D>(t2.findIntersections(ray1));
        assertEquals("find intersection failed for triangle perpendicular to ray through middle", l2.size(), 1, 1e-10);

        ArrayList<Point3D> l3 = new ArrayList<Point3D>(t3.findIntersections(ray1));
        assertEquals("find intersection failed for triangle way out of bounds", l3.size(), 0, 1e-10);

    }
}