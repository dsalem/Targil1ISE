package geometries;

public class RadialGeometry {
 private double _radius;

    public RadialGeometry(double _radius) {
        this._radius = _radius;
    }
    public RadialGeometry(RadialGeometry r) {
        this._radius = r._radius;
    }
    public RadialGeometry() {
        this._radius = 0;
    }

//getters and setters
    public double get_radius() {
        return _radius;
    }

    public void set_radius(double _radius) {
        this._radius = _radius;
    }
}
