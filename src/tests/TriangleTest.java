package tests;

import geometries.Triangle;
import org.junit.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.*;

public class TriangleTest {

    Coordinate x = new Coordinate(1);
    Coordinate y = new Coordinate(2);
    Coordinate z = new Coordinate(3);
    Coordinate x1 = new Coordinate(0);
    Coordinate y1 = new Coordinate(0);
    Coordinate z1 = new Coordinate(1);
    Coordinate z0 = new Coordinate(0);
    Point3D p0 = new Point3D(x1,y1,z0);
    Point3D p3 = new Point3D(x1,y1,z1);
    Point3D p1 = new Point3D(x,y,z);
    Point3D p2 = new Point3D(x,y,z);
    Vector v1 = new Vector(p1);
    Vector v2 = new Vector(p2);
    Vector v3 = new Vector(p3);
    Ray r1 = new Ray(p0,v3);
  //  Triangle t1 = new Triangle()

    @Test
    public void findIntersections() {
        v1.add(v2);
        assertEquals("addition failed for x",v1.get_head().get_x().get_coordinate(),2,1e-10);
        assertEquals("addition failed for y",v1.get_head().get_y().get_coordinate(),4,1e-10);
        assertEquals("addition failed for z",v1.get_head().get_z().get_coordinate(),6,1e-10);

    }
}