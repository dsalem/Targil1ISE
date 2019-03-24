package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

//represents a sphere in
public class Sphere extends RadialGeometry implements Geometry {
    private Point3D _center;

//constructor

    public Sphere(double _radius, Point3D _center) {
        super(_radius);
        this._center = _center;
    }

    public Sphere(RadialGeometry r, Point3D _center) {
        super(r);
        this._center = _center;
    }


    //copy constructor
    public Sphere(Sphere s) {
        this._center = s._center;
        set_radius(s.get_radius());
    }

    //empty constructor
    public Sphere() {
        super();
        this._center = new Point3D();
    }

    //getters and setters
    public Point3D get_center() {
        return _center;
    }

    public void set_center(Point3D _center) {
        this._center = _center;
    }

    @Override
    public Vector getNormal(Point3D p) {


        Vector cheatV = new Vector(_center);
        p.subtract(cheatV);
        Vector myVector = new Vector(p);
        myVector.normalize();
        return myVector;
    }

    @Override
    public List<Point3D> findIntersections(Ray r) {
        List l = new ArrayList();
        double th = 0;
        double t1 = 0;
        double t2 = 0;
        Point3D cpyPoint1 = new Point3D(r.get_p00());
        Point3D cpyPoint2 = new Point3D(r.get_p00());
        Vector L = new Vector(_center.pointSubtract(r.get_p00()));
        double tm = L.dotProduct(r.get_direction());
        double d = sqrt((L.length() * L.length()) - (tm * tm));
        if (d > get_radius())
            return l;
        else {
            th = sqrt(get_radius() * get_radius() - (d * d));
            t1 = tm - th;
            t2 = tm + th;
            cpyPoint1.add(r.get_direction().scalingV(t1));
            cpyPoint2.add(r.get_direction().scalingV(t2));
            l.add(cpyPoint1);
            if (cpyPoint1.compareTo(cpyPoint2) == -1)
                l.add(cpyPoint2);
            return l;

        }
    }


}
