package primitives;

public class Material {
    //first attenuation factor
    private double _Kd;
    //second attenuation factor
    private double _Ks;
    //shininess


    public void set_nShininess(int _nShininess) {
        this._nShininess = _nShininess;
    }

    public Material(double _Kd, double _Ks, int _nShininess) {

        this._Kd = _Kd;
        this._Ks = _Ks;
        this._nShininess = _nShininess;
    }

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

    private int _nShininess;

    public int get_nShininess() {
        return _nShininess;
    }
}
