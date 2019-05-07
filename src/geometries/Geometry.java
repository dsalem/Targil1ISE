package geometries;

import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public abstract class Geometry {

private Material _material;

   public abstract Vector getNormal(Point3D p);
   public abstract List<Point3D> findIntersections(Ray r);
}
