package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class SpotLight extends PointLight{
    //direction of light
    private Vector _direction;

    //full constructor
    public SpotLight(Color color, Point3D _position, double _Kc, double _Kl, double _Kq, Vector _direction) {
        super(color, _position, _Kc, _Kl, _Kq);
        this._direction = _direction;
    }

    //constructor
    public SpotLight(Vector _direction) {
        this._direction = _direction;
    }

    //copy constructor
    public SpotLight(SpotLight s){
        this(s.get_color(),new Point3D(s.get_position()),s.get_Kc(),s.get_Kl(),s.get_Kq(),new Vector(s.get_direction()));
    }
    //getters and setters
    public Vector get_direction() {
        return _direction;
    }

    public void set_direction(Vector _direction) {
        this._direction = _direction;
    }

    //override getIntensity
    public Color getIntensity() {
        double d = 1;
        //TODO fix this
        double dotProduct = get_direction().dotProduct(new Vector());

        double temp = get_Kc()+get_Kl()*d + get_Kq()*(d*d);
        return get_color().scale(1/temp);
    }
}
