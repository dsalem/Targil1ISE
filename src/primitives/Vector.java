package primitives;

public class Vector {
    private Point3D _head;

    //constructor
    public Vector(Point3D _head) {
        this._head = _head;
    }

    //copy constructor
    public Vector(Vector v) {
        _head = v._head;
    }

    //empty constructor
    public Vector() {
        _head = new Point3D();
    }

    //getters and setters
    public Point3D get_head() {
        return _head;
    }

    public void set_head(Point3D _head) {
        this._head = _head;
    }
}
