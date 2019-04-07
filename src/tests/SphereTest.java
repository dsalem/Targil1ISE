package tests;

import geometries.RadialGeometry;
import geometries.Sphere;
import org.junit.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SphereTest {
    Coordinate x0 = new Coordinate(0);
    Coordinate x1 = new Coordinate(-1);
    Coordinate y0 = new Coordinate(0);
    Coordinate y1 = new Coordinate(-1);
    Coordinate y2 = new Coordinate(-2);
    Coordinate z3 = new Coordinate(-3);
    Coordinate z1 = new Coordinate(-1);
    Coordinate z0 = new Coordinate(0);

    Point3D pc1 = new Point3D(x0, y0, z3);
    Point3D pc2 = new Point3D(x0, y1, z3);
    Point3D pc3 = new Point3D(x0, y2, z3);

    Point3D p0 = new Point3D(x0, y0, z0);
    Point3D p1 = new Point3D(x0, y0, z1);

    Vector v001 = new Vector(p1);

    RadialGeometry r1 = new RadialGeometry(1);

    Ray ray1 = new Ray(p0, v001);

    Sphere s1 = new Sphere(r1, pc1);
    Sphere s2 = new Sphere(r1, pc2);
    Sphere s3 = new Sphere(r1, pc3);




    @Test
    public void findIntersections() {

        ArrayList<Point3D> l = new ArrayList<Point3D>(s1.findIntersections(ray1));
        System.out.print(l);
        assertEquals("find intersection failed for sphere with radius 1 and 2 intersections", l.size(), 2, 1e-10);
        assertEquals("find intersection failed for sphere with radius 1 and 2 intersections", l.get(0).get_z().get_coordinate(), -2, 1e-10);
        assertEquals("find intersection failed for sphere with radius 1 and 2 intersections", l.get(1).get_z().get_coordinate(), -4, 1e-10);

        ArrayList<Point3D> l2 = new ArrayList<Point3D>(s2.findIntersections(ray1));
        System.out.print(l2);
        assertEquals("find intersection failed for sphere with 1 intersection", l2.size(), 1, 1e-10);
        assertEquals("find intersection failed for sphere with 1 intersection", l2.get(0).get_y().get_coordinate(), 0, 1e-10);

        ArrayList<Point3D> l3 = new ArrayList<Point3D>(s3.findIntersections(ray1));
        assertEquals("find intersection failed for sphere with radius 1 and 2 intersections", l3.size(), 0, 1e-10);
    }
}