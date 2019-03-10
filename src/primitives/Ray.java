package primitives;

public class Ray {
    private Point3D _p00;
    private Vector _direction;

    //constructor
    public Ray(Point3D _p00, Vector _direction) {
        this._p00 = _p00;
        this._direction = _direction;
    }

    //copy constructor
    public Ray(Ray r) {
        _p00 = r._p00;
        _direction = r._direction;
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
