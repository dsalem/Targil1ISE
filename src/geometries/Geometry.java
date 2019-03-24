package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public interface Geometry {

   public Vector getNormal(Point3D p);
   public List<Point3D> findIntersections(Ray r);
}
