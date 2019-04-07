package elements;

import primitives.Point3D;
import primitives.Vector;

public class Camera {
    private Point3D _P0;
    private Vector _vUp;
    private Vector _vTo;
    private Vector _vRight;

    public Camera(Point3D _P0, Vector _vUp, Vector _vTo, Vector _vRight) {
        this._P0 = _P0;
        this._vUp = _vUp;
        this._vTo = _vTo;
        this._vRight = _vRight;
    }
}
