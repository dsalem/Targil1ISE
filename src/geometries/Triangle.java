package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

//represents a triangle in space
public class Triangle extends Geometry {
    //Point one of triangle
    private Point3D _p1;
    //Point two of triangle
    private Point3D _p2;
    //Point three of triangle
    private Point3D _p3;

    //full constructor
    public Triangle(Point3D _p1, Point3D _p2, Point3D _p3) {
        this._p1 = _p1;
        this._p2 = _p2;
        this._p3 = _p3;
    }

    //copy constructor
    public Triangle(Triangle t) {
        this(new Point3D(t.get_p1()),new Point3D(t.get_p2()),new Point3D(t.get_p3()));
    }

    //empty constructor
    public Triangle() {
        this._p1 = new Point3D();
        this._p2 = new Point3D();
        this._p3 = new Point3D();
    }

    //getters and setters
    public Point3D get_p1() {
        return _p1;
    }

    public void set_p1(Point3D _p1) {
        this._p1 = _p1;
    }

    public Point3D get_p2() {
        return _p2;
    }

    public void set_p2(Point3D _p2) {
        this._p2 = _p2;
    }

    public Point3D get_p3() {
        return _p3;
    }

    public void set_p3(Point3D _p3) {
        this._p3 = _p3;
    }

    //override getNormal function
    @Override
    public Vector getNormal(Point3D p) {
        //copy points so they don't get changed
        Point3D p2c = new Point3D(_p2);
        Point3D p3c = new Point3D(_p3);
        //form two new vectors by subtracting the two points
        Vector vector1 = new Vector(p2c.subtract(get_p1()));
        Vector vector2 = new Vector(p3c.subtract(get_p1()));
        //obtain cross product of both vectors
        Vector crossProduct = vector1.crossProduct(vector2);
        //normalize vector
        crossProduct.normalize();
        return crossProduct;
    }

    //override findIntersections function
    @Override
    public List<Point3D> findIntersections(Ray r) {
        //create new list
        List l = new ArrayList();
        //copy points so they don't get changed
        Point3D cpyP1 = new Point3D(_p1);
        Point3D cpyP2 = new Point3D(_p2);
        Point3D cpyP3 = new Point3D(_p3);
        //form a new plane from point 1
        Plane planeP = new Plane(_p1, getNormal(get_p1()));
        //obtain intersection point from plane if exists
        Point3D p = planeP.findIntersections(r).get(0);
        //copy point
        Point3D cpyP = new Point3D(p);
        //form three new vectors of points to origin
        Vector v1 = new Vector(cpyP1.subtract(r.get_p00()));
        Vector v2 = new Vector(cpyP2.subtract(r.get_p00()));
        Vector v3 = new Vector(cpyP3.subtract(r.get_p00()));
        //obtain cross products accordingly
        Vector cross1 = new Vector(v1.crossProduct(v2));
        Vector cross2 = new Vector(v2.crossProduct(v3));
        Vector cross3 = new Vector(v3.crossProduct(v1));
        //scale the results - divide by length
        cross1.scaling(1 / cross1.length());
        cross2.scaling(1 / cross2.length());
        cross3.scaling(1 / cross3.length());
        //new vector of intersection point - origin
        Vector v = new Vector(cpyP.subtract(r.get_p00()));
        //if all signs are equal then it intersects triangle
        if ((sign(v.dotProduct(cross1)) == sign(v.dotProduct(cross2)))
                && sign(v.dotProduct(cross1)) == sign(v.dotProduct(cross3)))
            l.add(p);
        return l;
    }

    //helper function for sign
    public int sign(double d) {
        if (d >= 0)
            return 1;
        return 0;
    }
}
