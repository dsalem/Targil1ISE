package primitives;

//point3d represents a point in space
public class Point3D extends Point2D {
    private Coordinate _z;

    //constructor
    public Point3D(Coordinate _x, Coordinate _y, Coordinate _z) {
        super(_x, _y);
        this._z = _z;
    }

    //empty constructor
    public Point3D() {
        super();
        this._z = new Coordinate();
    }

    //copy constructor
    public Point3D(Point3D p) {
        set_x(p.get_x());
        set_y(p.get_y());
        p._z = _z;
    }

    //getters and setters
    public Coordinate get_z() {
        return _z;
    }

    public void set_z(Coordinate _z) {
        this._z = _z;
    }
}
