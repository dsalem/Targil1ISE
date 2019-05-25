package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

//class radial geometry
public class RadialGeometry extends Geometry{
    //Radius of geometry
    private double _radius;

    //full constructor
    public RadialGeometry(double _radius) {
        this._radius = _radius;
    }
    //copy constructor
    public RadialGeometry(RadialGeometry r) {
        this(r.get_radius());
    }
    //empty constructor
    public RadialGeometry() {
        this._radius = 0;
    }

    //getters and setters
    public double get_radius() {
        return _radius;
    }

    public void set_radius(double _radius) {
        this._radius = _radius;
    }

    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }

    @Override
    public List<Point3D> findIntersections(Ray r) {
        return null;
    }
}

