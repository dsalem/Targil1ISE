package elements;

import primitives.Color;
import primitives.Coordinate;
import primitives.Point3D;

public class PointLight extends Light {
    //position of light
    private Point3D _position;
    //constants of direction
    private double _Kc, _Kl, _Kq;


    public PointLight(Color color, Point3D _position, double _Kc, double _Kl, double _Kq) {
        super(color);
        this._position = _position;
        this._Kc = _Kc;
        this._Kl = _Kl;
        this._Kq = _Kq;
    }

    public PointLight() {
        super();
        this._position = new Point3D(new Coordinate(0), new Coordinate(0), new Coordinate(0));
        this._Kc = 0;
        this._Kl = 0;
        this._Kq = 0;
    }

    public Point3D get_position() {
        return _position;
    }

    public void set_position(Point3D _position) {
        this._position = _position;
    }

    public double get_Kc() {
        return _Kc;
    }

    public void set_Kc(double _Kc) {
        this._Kc = _Kc;
    }

    public double get_Kl() {
        return _Kl;
    }

    public void set_Kl(double _Kl) {
        this._Kl = _Kl;
    }

    public double get_Kq() {
        return _Kq;
    }

    public void set_Kq(double _Kq) {
        this._Kq = _Kq;
    }

    @Override
    public Color getIntensity() {
        double d = 1;
        double temp = get_Kc()+get_Kl()*d + get_Kq()*(d*d);
        return get_color().scale(1/temp);
    }
}
