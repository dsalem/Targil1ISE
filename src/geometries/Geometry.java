package geometries;

import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;



//interface geometry
public abstract class Geometry {

    //field material
    private Material _material;

    //return normal
    public abstract Vector getNormal(Point3D p);
    //return list of intersections
    public abstract List<Point3D> findIntersections(Ray r);
}
