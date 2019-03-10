package geometries;

import primitives.Point3D;

//represents a triangle in space
public class Triangle {
    private Point3D _p1;
    private Point3D _p2;
    private Point3D _p3;

    //constructor
    public Triangle(Point3D _p1, Point3D _p2, Point3D _p3) {
        this._p1 = _p1;
        this._p2 = _p2;
        this._p3 = _p3;
    }

    //copy constructor
    public Triangle(Triangle t) {
        _p1 = t._p1;
        _p2 = t._p2;
        _p3 = t._p3;
    }
//empty constructor
    public Triangle() {
        this._p1 = new Point3D();
        this._p2 = new Point3D();
        this._p3 = new Point3D();
    }

    //getters and setters
    public Point3D get_p1() {
        return _p1;
    }

    public void set_p1(Point3D _p1) {
        this._p1 = _p1;
    }

    public Point3D get_p2() {
        return _p2;
    }

    public void set_p2(Point3D _p2) {
        this._p2 = _p2;
    }

    public Point3D get_p3() {
        return _p3;
    }

    public void set_p3(Point3D _p3) {
        this._p3 = _p3;
    }
}
