package elements;

import primitives.Color;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Vector;

public class PointLight extends Light implements LightSource{
    //position of light
    private Point3D _position;
    //constants of direction
    private double _Kc, _Kl, _Kq;


    //full constructor
    public PointLight(Color color, Point3D _position, double _Kc, double _Kl, double _Kq) {
        super(color);
        this._position = _position;
        this._Kc = _Kc;
        this._Kl = _Kl;
        this._Kq = _Kq;
    }

    //copy constructor
    public PointLight(PointLight p){
        this(p.get_color(),new Point3D(p.get_position()),p.get_Kc(),p.get_Kl(),p.get_Kq());
    }

    //empty constructor
    public PointLight() {
        super();
        this._position = new Point3D(new Coordinate(0), new Coordinate(0), new Coordinate(0));
        this._Kc = 0;
        this._Kl = 0;
        this._Kq = 0;
    }


    //getters and setters
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

   //override get intensity
    @Override
    public Color getIntensity() {
        return null;
    }

    //override get intensity with point
    @Override
    public Color getIntensity(Point3D p) {
        //copy color to keep variable the same
        Color copy = new Color(get_color());
        //find distance between light and geometry
        double d = p.distance(_position);
        //calculate intensity IL=I0/(Kc+Kl(d)+Kq(d^2)
        double denominator = (get_Kc() + (get_Kl()*d) + (get_Kq()*(d*d)));
        //scale color by denominator to return intensity
        return copy.scale(1/denominator);
    }

    //override get L vector
    @Override
    public Vector getL(Point3D p) {
        //copy point p
        Point3D copy = new Point3D(p);
        //return vector l from light source to object
        Vector l = new Vector(copy.subtract(get_position()));
        l.normalize();
        return l;
    }
}
