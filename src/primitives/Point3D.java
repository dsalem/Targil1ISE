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
        _z = new Coordinate();
    }

    //copy constructor
    public Point3D(Point3D p) {
      this(new Coordinate(p.get_x()),new Coordinate(p.get_y()),new Coordinate(p.get_z()));
    }

    //getters and setters
    public Coordinate get_z() {
        return _z;
    }

    public void set_z(Coordinate _z) {
        this._z = _z;
    }


    public int compareTo(Point3D p) {
        if (get_x().compareTo(p.get_x()) == 1 && get_y().compareTo(p.get_y()) == 1 && get_z().compareTo(p.get_z()) == 1)
            return 1;
        return 0;
    }

    public Point3D add(Vector v) {
        get_x().add(v.get_head().get_x());
        get_y().add(v.get_head().get_y());
        get_z().add(v.get_head().get_z());
        
        return this;
    }

    public Point3D subtract(Vector v) {
        get_x().subtract(v.get_head().get_x());
        get_y().subtract(v.get_head().get_y());
        get_z().subtract(v.get_head().get_z());
        
        return this;
    }

    public Point3D subtract(Point3D p) {
        get_x().subtract(p.get_x());
        get_y().subtract(p.get_y());
        get_z().subtract(p.get_z());
                
        return this;
    }
}
