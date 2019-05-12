package primitives;

public class Material {
    //first attenuation factor
    private double _Kd;
    //second attenuation factor
    private double _Ks;
    //shininess
    private int _nShininess;

    //full constructor
    public Material(double _Kd, double _Ks, int _nShininess) {
        this._Kd = _Kd;
        this._Ks = _Ks;
        this._nShininess = _nShininess;
    }

    //copy constructor
    public Material(Material m){
        this(m.get_Kd(),m.get_Ks(),m.get_nShininess());
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
}
