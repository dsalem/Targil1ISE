package geometries;

import primitives.Point3D;
import primitives.Vector;
//represents a cylinder in space
public class Cylinder extends RadialGeometry implements Geometry{
    private Point3D _axisPoint;
    private Vector _axisDirection;

//constructor
    public Cylinder(double _radius, Point3D _axisPoint, Vector _axisDirection) {
        super(_radius);
        this._axisPoint = _axisPoint;
        this._axisDirection = _axisDirection;
    }
//constructor
    public Cylinder(RadialGeometry r, Point3D _axisPoint, Vector _axisDirection) {
        super(r);
        this._axisPoint = _axisPoint;
        this._axisDirection = _axisDirection;
    }
//copy constructor
    public Cylinder(Cylinder c){
        this._axisPoint = c._axisPoint;
        this._axisDirection = c._axisDirection;
        set_radius(c.get_radius());
    }
    //empty constructor
    public Cylinder(){
        super();
        this._axisPoint = new Point3D();
        this._axisDirection = new Vector();
    }
//getters and setters
    public Point3D get_axisPoint() {
        return _axisPoint;
    }

    public void set_axisPoint(Point3D _axisPoint) {
        this._axisPoint = _axisPoint;
    }

    public Vector get_axisDirection() {
        return _axisDirection;
    }

    public void set_axisDirection(Vector _axisDirection) {
        this._axisDirection = _axisDirection;
    }

    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }
}
