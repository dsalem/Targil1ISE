package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;
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

    @Override
    public Vector getNormal(Point3D p) {


        Vector cheatV = new Vector(_center);
        Vector myVector=new Vector(p.subtract(cheatV));
        myVector.normalize();
        return myVector;
    }

    @Override
    public List<Point3D> findIntersections(Ray r) {
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
        tm = L.dotProduct(r.get_direction());
        d = sqrt((L.length() * L.length()) - (tm * tm));
        d= (double)Math.round(d * 100000d) / 100000d; //round the number to 5 numbers after the decimal point
       //if no intersections
        if (d > get_radius())
            return l;
        else {
            //Calculate the intersection points
            th = sqrt(get_radius() * get_radius() - (d * d));
            t1 = tm - th;
            t2 = tm + th;
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
