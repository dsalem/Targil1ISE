package geometries;

import primitives.Point3D;
import primitives.Vector;
//represents a plane in space
public class Plane {
    private Point3D _p;
    private Vector _normal;// the normal vector from the point to create a 90 degree angle and therefore represent a plane

    //constructor
    public Plane(Point3D _p, Vector _normal) {
        this._p = _p;
        this._normal = _normal;
    }

    //copy constructor
    public Plane(Plane p) {
        this._p = p._p;
        this._normal = p._normal;
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
}
