package geometries;

import primitives.Point3D;
//represents a sphere in
public class Sphere extends RadialGeometry{
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
}
