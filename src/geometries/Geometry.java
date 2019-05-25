package geometries;

import primitives.*;

import java.util.List;



//abstract class geometry
public abstract class Geometry {
    //field material
    private Material _material=new Material(0,0,0,new Color());

    //getters and setters
    public Material get_material() { return _material; }
    public void set_material(Material _material) {
        this._material = _material;
    }

    //return normal
    public abstract Vector getNormal(Point3D p);
    //return list of intersections
    public abstract List<Point3D> findIntersections(Ray r);
}
