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
        _direction.normalize();
        this._direction = _direction;
    }

    //constructor
    public SpotLight(Vector _direction) {
        _direction.normalize();
        this._direction = _direction;
    }

    //copy constructor
    public SpotLight(SpotLight s){
        this(s.get_color(),new Point3D(s.get_position()),s.get_Kc(),s.get_Kl(),s.get_Kq(),new Vector(s.get_direction()));
    }
    //getters and setters
    public Vector get_direction() {
        _direction.normalize();
        return _direction;
    }

    public void set_direction(Vector _direction) {
        _direction.normalize();
        this._direction = _direction;
    }

    //override getIntensity
    @Override
    public Color getIntensity(Point3D p) {
        //copy color to maintain variable
        Color copy = new Color(get_color());
        //find distance between light and geometry
        double d = p.distance(get_position());
        //obtain dot product of D and L
        double dotProduct = Math.max(0,get_direction().dotProduct(getL(p)));
        //obtain denominator of function IL=[I0(D.L)/Kc+Kl(d)+Kq(d^2)]
        double denominator = (get_Kc()+(get_Kl()*d) + (get_Kq()*(d*d)));
        //scale color by dot product
        copy.scale(dotProduct);
        //scale color by denominator to obtain intensity
        return copy.scale(1/denominator);
    }

    //override get L function
    @Override
    public Vector getL(Point3D p){
       return super.getL(p);
    }
}
