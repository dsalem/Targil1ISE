package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

//represents a plane in space
public class Plane implements Geometry{
    private Point3D _p;
    private Vector _normal;// the normal vector from the point to create a 90 degree angle and therefore represent a plane

    //constructor
    public Plane(Point3D _p, Vector _normal) {
        this._p = _p;
        this._normal = _normal;
    }

    //copy constructor
    public Plane(Plane p) {
       this(new Point3D(p.get_p()),new Vector(p.get_normal()));
    }

    //empty constructor
    public Plane() {
        this._p = new Point3D();
        this._normal = new Vector();
    }

    //getters and setters
    public Point3D get_p() {
        return _p;
    }

    public void set_p(Point3D _p) {
        this._p = _p;
    }

    public Vector get_normal() {
        return _normal;
    }

    public void set_normal(Vector _normal) {
        this._normal = _normal;
    }

    @Override
    public Vector getNormal(Point3D p) {
        return _normal;
    }

    @Override
    public List<Point3D> findIntersections(Ray r) {
        List l = new ArrayList();                                       //initialize list
        Point3D temp =  new Point3D(r.get_p00());                       //copy points and vectors
        Point3D temp2 =  new Point3D(r.get_p00());
        Vector cpyNormal= new Vector(_normal);
        Vector cpyDirection = new Vector(r.get_direction());
        double NDotV = (r.get_direction().dotProduct(get_normal()));    // (N dotProduct V)
        Vector tempV = new Vector(temp.subtract(get_p()));              //(P0-Qo)           
        tempV.scaling(1/NDotV);                                         //(P0-Qo)/(N dotProduct V)
        cpyNormal.scaling(-1);                                          //-N
        double t = cpyNormal.dotProduct(tempV);                         //obtain scalar t
        cpyDirection.scaling(t);                                        //obtain vector of intersection
        Point3D P = new Point3D(temp2.add(cpyDirection));
        l.add(P);                                                       //add to list
        return l;
    }
}
