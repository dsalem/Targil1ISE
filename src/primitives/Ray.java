package primitives;
//ray class
public class Ray {
    //Point of beginning of ray
    private Point3D _p00;
    //Vector of direction of ray
    private Vector _direction;

    //full constructor
    public Ray(Point3D _p00, Vector _direction) {
        this._p00 = _p00;
        this._direction = _direction;
    }

    //copy constructor
    public Ray(Ray r) {
        this(new Point3D(r.get_p00()),new Vector(r.get_direction()));
    }

    //empty constructor
    public Ray() {
        _p00 = new Point3D();
        _direction = new Vector();
    }

    //getters and setters
    public Point3D get_p00() {
        return _p00;
    }

    public void set_p00(Point3D _p00) {
        this._p00 = _p00;
    }

    public Vector get_direction() {
        return _direction;
    }

    public void set_direction(Vector _direction) {
        this._direction = _direction;
    }
}
