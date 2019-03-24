package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

//represents a triangle in space
public class Triangle implements Geometry {
    private Point3D _p1;
    private Point3D _p2;
    private Point3D _p3;

    //constructor
    public Triangle(Point3D _p1, Point3D _p2, Point3D _p3) {
        this._p1 = _p1;
        this._p2 = _p2;
        this._p3 = _p3;
    }

    //copy constructor
    public Triangle(Triangle t) {
        _p1 = t._p1;
        _p2 = t._p2;
        _p3 = t._p3;
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

    @Override
    public Vector getNormal(Point3D p) {
        Point3D p1c = new Point3D(_p1);
        Point3D p2c = new Point3D(_p2);
        Point3D p3c = new Point3D(_p3);
        p2c.subtract(p1c);
        Vector vector1 = new Vector(p2c);
        p3c.subtract(p1c);
        Vector vector2 = new Vector(p3c);
        Vector crossProduct = vector1.crossProduct(vector2);
        crossProduct.normalize();
        return crossProduct;
    }

    @Override
    public List<Point3D> findIntersections(Ray r) {
        List l = new ArrayList();
        Point3D cpyP1 = new Point3D(_p1);
        Point3D cpyP2 = new Point3D(_p2);
        Point3D cpyP3 = new Point3D(_p3);
        Plane planeP = new Plane(_p1, getNormal(get_p1()));
        Point3D p = planeP.findIntersections(r).get(0);
        Point3D cpyP = new Point3D(p);
        cpyP1.subtract(r.get_p00());
        cpyP2.subtract(r.get_p00());
        cpyP3.subtract(r.get_p00());
        Vector v1 = new Vector(cpyP1);
        Vector v2 = new Vector(cpyP2);
        Vector v3 = new Vector(cpyP3);


        Vector cross1 = new Vector(v2.crossProduct(v1));
        Vector cross2 = new Vector(v3.crossProduct(v1));
        Vector cross3 = new Vector(v2.crossProduct(v3));
        cross1.scaling(1 / v2.crossProduct(v1).length());
        cross2.scaling(1 / v3.crossProduct(v1).length());
        cross3.scaling(1 / v2.crossProduct(v3).length());
        Vector N1 = new Vector(cross1);
        Vector N2 = new Vector(cross2);
        Vector N3 = new Vector(cross3);

        cpyP.subtract(r.get_p00());
        Vector v = new Vector(cpyP);
        if ((sign(v.dotProduct(N1)) == sign(v.dotProduct(N2)))&& sign(v.dotProduct(N1)) == sign(v.dotProduct(N3)))
            l.add(p);
        return l;
    }

    public int sign(double d) {
        if (d >= 0)
            return 1;
        return 0;
    }
}
