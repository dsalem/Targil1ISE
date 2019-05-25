package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

//represents a plane in space
public class Plane extends Geometry{
    //Point representing plane in space
    private Point3D _p;
    //Normal to the point of the plane
    private Vector _normal;

    //full constructor
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

    //override getNormal function
    @Override
    public Vector getNormal(Point3D p) {
        return _normal;
    }

    //override findIntersections function
    @Override
    public List<Point3D> findIntersections(Ray r) {
        //create new list
        List l = new ArrayList();
        //copy variables so they don't change
        Point3D temp =  new Point3D(r.get_p00());
        Point3D temp2 =  new Point3D(r.get_p00());
        Vector cpyNormal= new Vector(_normal);
        Vector cpyDirection = new Vector(r.get_direction());
        //calculate dot product of normal to plane and ray
        double NDotV = (r.get_direction().dotProduct(get_normal()));
        //create vector of P0-Q0
        Vector tempV = new Vector(temp.subtract(get_p()));
        //if plane is parallel to ray...
        if (NDotV == 0)
            return l;
        //scale vector and normal
        tempV.scaling(1/NDotV);
        cpyNormal.scaling(-1);
        //obtain dot product of normal and new vector
        double t = cpyNormal.dotProduct(tempV);
        //scale direction vector by result
        cpyDirection.scaling(t);
        //obtain point of intersection
        Point3D P = new Point3D(temp2.add(cpyDirection));
        //add point to list and return list
        l.add(P);
        return l;
    }
}
