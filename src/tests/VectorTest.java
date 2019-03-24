package tests;

import org.junit.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;

public class VectorTest {

    Coordinate x = new Coordinate(1);
    Coordinate y = new Coordinate(2);
    Coordinate z = new Coordinate(3);
    Coordinate x1 = new Coordinate(0);
    Coordinate y1 = new Coordinate(0);
    Coordinate z1 = new Coordinate(1);
    Point3D p3 = new Point3D(x1,y1,z1);
    Point3D p1 = new Point3D(x,y,z);
    Point3D p2 = new Point3D(x,y,z);
    Vector v1 = new Vector(p1);
    Vector v2 = new Vector(p2);
    Vector v3 = new Vector(p3);


    @Test
    public void add() {

        v1.add(v2);
        assertEquals("addition failed for x",v1.get_head().get_x().get_coordinate(),2,1e-10);
        assertEquals("addition failed for y",v1.get_head().get_y().get_coordinate(),4,1e-10);
        assertEquals("addition failed for z",v1.get_head().get_z().get_coordinate(),6,1e-10);
    }

    @Test
    public void subtract() {

        v1.subtract(v2);
        assertEquals("subtraction failed for x",v1.get_head().get_x().get_coordinate(),0,1e-10);
        assertEquals("subtraction failed for y",v1.get_head().get_y().get_coordinate(),0,1e-10);
        assertEquals("subtraction failed for z",v1.get_head().get_z().get_coordinate(),0,1e-10);
    }

    @Test
    public void crossProduct() {
       Vector v4 =  v1.crossProduct(v1);
        assertEquals("crossProduct failed for x",v4.get_head().get_x().get_coordinate(),0,1e-10);
        assertEquals("crossProduct failed for y",v4.get_head().get_y().get_coordinate(),0,1e-10);
        assertEquals("crossProduct failed for z",v4.get_head().get_z().get_coordinate(),0,1e-10);
    }

    @Test
    public void length() {
        assertEquals("length failed for v1",v3.length(),1,1e-10);
    }

    @Test
    public void normalize() {
        v1.normalize();
        assertEquals("length failed for v1",v1.length(),1,1e-10);
    }

    @Test
    public void scaling() {
        v1.scaling(2);
        assertEquals("scaling failed for x",v1.get_head().get_x().get_coordinate(),2,1e-10);
        assertEquals("scaling failed for y",v1.get_head().get_y().get_coordinate(),4,1e-10);
        assertEquals("scaling failed for z",v1.get_head().get_z().get_coordinate(),6,1e-10);


    }

    @Test
    public void dotProduct() {
        v1.dotProduct(v2);
        assertEquals("dot product failed for equal vectors ",v1.dotProduct(v2),v1.length()*v1.length(),1e-10);

    }
}