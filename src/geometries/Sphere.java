package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

//represents a sphere in space
public class Sphere extends RadialGeometry{
    //Point representing center of sphere
    private Point3D _center;

   //full constructor
    public Sphere(double _radius, Point3D _center) {
        super(_radius);
        this._center = _center;
    }
    //full constructor
    public Sphere(RadialGeometry r, Point3D _center) {
        super(r);
        this._center = _center;
    }

    //copy constructor
    public Sphere(Sphere s) {
        this(s.get_radius(),new Point3D(s.get_center()));
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

    //override getNormal function
    @Override
    public Vector getNormal(Point3D p) {
        //copy point p
        Point3D copy = new Point3D(p);
        //form vector from point3d
        Vector cheatV = new Vector(_center);
        //subtract the two vectors
        Vector myVector=new Vector(copy.subtract(cheatV));
        //normalize vector
        myVector.normalize();
        //return vector
        return myVector;
    }

    //override findIntersections function
    @Override
    public List<Point3D> findIntersections(Ray r) {
        //create new list
        List l = new ArrayList();
        double th,tm,t1,t2,d;
        //copy points so the originals don't get changed
        Point3D cpyPoint1 = new Point3D(r.get_p00());
        Point3D cpyPoint2 = new Point3D(r.get_p00());
        Point3D cpyCenter=new Point3D(get_center());
        Ray cpyRay1 = new Ray(r);
        Ray cpyRay2 = new Ray(r);

        //create vector of ray to center
        Vector L = new Vector(cpyCenter.subtract(r.get_p00()));
        //calculate dot product
        tm = L.dotProduct(r.get_direction());
        //calculate square root of length-dot product
        d = sqrt((L.length() * L.length()) - (tm * tm));
        //round the number to 5 numbers after the decimal point
        d= (double)Math.round(d * 100000d) / 100000d;
        //if no intersections
        if (d > get_radius())
            return l;
        else {
            //Calculate the intersection points
            th = sqrt(get_radius() * get_radius() - (d * d));
            t1 = tm - th;
            t2 = tm + th;
            //obtain the points of intersection
            cpyPoint1.add(cpyRay1.get_direction().scalingV(t1));
            cpyPoint2.add(cpyRay2.get_direction().scalingV(t2));
            //add first point to the list
            l.add(cpyPoint1);
            //If there are 2 intersection points
            if (cpyPoint1.compareTo(cpyPoint2) == 0)
                l.add(cpyPoint2);
            return l;

        }
    }


}
