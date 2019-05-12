package tests;

import elements.Camera;
import geometries.Triangle;
import org.junit.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
//tests for triangle operations
public class TriangleTest {
    //instantiate objects for tests
    Triangle t1 = new Triangle(new Point3D(new Coordinate(0), new Coordinate(0), new Coordinate(-3)),
            new Point3D(new Coordinate(5),new Coordinate( -5), new Coordinate(-3)),
            new Point3D(new Coordinate(-5),new Coordinate( -5),new Coordinate( -3)));

    Triangle t2 = new Triangle(new Point3D(new Coordinate(0), new Coordinate(10), new Coordinate(-3)),
            new Point3D(new Coordinate(-10),new Coordinate( -10), new Coordinate(-3)),
            new Point3D(new Coordinate(10),new Coordinate( -10),new Coordinate( -3)));

    Triangle t3 = new Triangle(new Point3D(new Coordinate(-9), new Coordinate(-10), new Coordinate(-3)),
            new Point3D(new Coordinate(-10),new Coordinate( -9), new Coordinate(-3)),
            new Point3D(new Coordinate(-11),new Coordinate( -11),new Coordinate( -3)));

    Triangle t4 = new Triangle(new Point3D(new Coordinate(0), new Coordinate(-20), new Coordinate(-2)),
            new Point3D(new Coordinate(1),new Coordinate( 1), new Coordinate(-2)),
            new Point3D(new Coordinate(-1),new Coordinate( 1),new Coordinate( -2)));

    Coordinate x0 = new Coordinate(0);
    Coordinate y0 = new Coordinate(0);
    Coordinate z0 = new Coordinate(0);
    Coordinate z1 = new Coordinate(-1);


    Point3D p0 = new Point3D(x0, y0, z0);
    Point3D p1 = new Point3D(x0, y0, z1);
    Vector v001 = new Vector(p1);
    Ray ray1 = new Ray(p0, v001);
    Camera camera =new Camera();
    Ray ray2=new Ray(camera.constructRayThroughPixel(3,3,0,0,1,9,9));
    Ray ray3=new Ray(camera.constructRayThroughPixel(3,3,0,1,1,9,9));
    Ray ray4=new Ray(camera.constructRayThroughPixel(3,3,0,2,1,9,9));
    Ray ray5=new Ray(camera.constructRayThroughPixel(3,3,1,0,1,9,9));
    Ray ray6=new Ray(camera.constructRayThroughPixel(3,3,1,1,1,9,9));
    Ray ray7=new Ray(camera.constructRayThroughPixel(3,3,1,2,1,9,9));
    Ray ray8=new Ray(camera.constructRayThroughPixel(3,3,2,0,1,9,9));
    Ray ray9=new Ray(camera.constructRayThroughPixel(3,3,2,1,1,9,9));
    Ray ray10=new Ray(camera.constructRayThroughPixel(3,3,2,2,1,9,9));

    @Test
    public void findIntersections() {
        //triangle in front of camera - one intersection
        ArrayList<Point3D> l = new ArrayList<Point3D>(t1.findIntersections(ray1));
        assertEquals("find intersection failed for triangle perpendicular to ray", l.size(), 1, 1e-10);
        assertEquals("find intersection failed for triangle perpendicular to ray", l.get(0).get_x().get_coordinate(), 0, 1e-10);
        assertEquals("find intersection failed for triangle perpendicular to ray", l.get(0).get_y().get_coordinate(), 0, 1e-10);
        assertEquals("find intersection failed for triangle perpendicular to ray", l.get(0).get_z().get_coordinate(), -3, 1e-10);

        //triangle in front of camera - one intersection
        ArrayList<Point3D> l2 = new ArrayList<Point3D>(t2.findIntersections(ray1));
        assertEquals("find intersection failed for triangle perpendicular to ray through middle", l2.size(), 1, 1e-10);
        assertEquals("find intersection failed for triangle perpendicular to ray", l.get(0).get_x().get_coordinate(), 0, 1e-10);
        assertEquals("find intersection failed for triangle perpendicular to ray", l.get(0).get_y().get_coordinate(), 0, 1e-10);
        assertEquals("find intersection failed for triangle perpendicular to ray", l.get(0).get_z().get_coordinate(), -3, 1e-10);

        //triangle not in front of camera - no intersections
        ArrayList<Point3D> l3 = new ArrayList<Point3D>(t3.findIntersections(ray1));
        assertEquals("find intersection failed for triangle way out of bounds", l3.size(), 0, 1e-10);

        //shoot nine rays at a triangle with two intersections
        ArrayList<Point3D> l4 = new ArrayList<Point3D>(t4.findIntersections(ray2));
        ArrayList<Point3D> l5 = new ArrayList<Point3D>(t4.findIntersections(ray3));
        ArrayList<Point3D> l6 = new ArrayList<Point3D>(t4.findIntersections(ray4));
        ArrayList<Point3D> l7 = new ArrayList<Point3D>(t4.findIntersections(ray5));
        ArrayList<Point3D> l8 = new ArrayList<Point3D>(t4.findIntersections(ray6));
        ArrayList<Point3D> l9 = new ArrayList<Point3D>(t4.findIntersections(ray7));
        ArrayList<Point3D> l10 = new ArrayList<Point3D>(t4.findIntersections(ray8));
        ArrayList<Point3D> l11= new ArrayList<Point3D>(t4.findIntersections(ray9));
        ArrayList<Point3D> l12= new ArrayList<Point3D>(t4.findIntersections(ray10));
        int sum=l4.size()+l5.size()+l6.size()+l7.size()+l8.size()+l9.size()+l10.size()+l11.size()+l12.size();
        assertEquals("find intersection failed for triangle", sum, 2, 1e-10);







    }

}
