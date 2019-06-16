package primitives;
public class Material {

    //first attenuation factor
    private double _Kd;
    //second attenuation factor
    private double _Ks;
    //shininess
    private int _nShininess;
    //color
    private Color _emission = new Color();
    //constant Kr
    private double _Kr;
    //constant Kt
    private double _Kt;

    //full constructor
    public Material(double _Kd, double _Ks, int _nShininess, Color _emission, double _Kr, double _Kt) {
        this._Kd = _Kd;
        this._Ks = _Ks;
        this._nShininess = _nShininess;
        this._emission = _emission;
        this._Kr = _Kr;
        this._Kt = _Kt;
    }

    //full constructor
    public Material(double _Kd, double _Ks, int _nShininess, Color emission) {
        this._Kd = _Kd;
        this._Ks = _Ks;
        this._nShininess = _nShininess;
        this._emission = emission;
    }

    //copy constructor
    public Material(Material m) {
        this(m.get_Kd(), m.get_Ks(), m.get_nShininess(), m.get_emission());
    }

    //getters and setters
    public double get_Kd() {
        return _Kd;
    }

    public void set_Kd(double _Kd) {
        this._Kd = _Kd;
    }

    public double get_Ks() {
        return _Ks;
    }

    public void set_Ks(double _Ks) {
        this._Ks = _Ks;
    }

    public void set_nShininess(int _nShininess) {
        this._nShininess = _nShininess;
    }

    public int get_nShininess() {
        return _nShininess;
    }

    public Color get_emission() {
        return _emission;
    }

    public void set_emission(Color _emission) {
        _emission.setColor(_emission);
    }


    public double get_Kr() {
        return _Kr;
    }

    public void set_Kr(double _Kr) {
        this._Kr = _Kr;
    }

    public double get_Kt() {
        return _Kt;
    }

    public void set_Kt(double _Kt) {
        this._Kt = _Kt;
    }
}
