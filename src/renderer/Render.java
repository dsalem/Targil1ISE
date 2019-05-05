package renderer;

import geometries.Geometry;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//class render
public class Render {
    //scene member
    private Scene _scene;
    //image writer member
    private ImageWriter _imageWriter;

    //full constructor
    public Render(Scene s, ImageWriter i) {
        _scene = s;
        _imageWriter = i;
    }

    //copy constructor
    public Render(Render r) {
        this(new Scene(r._scene), new ImageWriter(r._imageWriter));
    }

    //getters/setters
    public Scene getScene() {
        return _scene;
    }

    public ImageWriter getImageWriter() {
        return _imageWriter;
    }

    public void setScene(Scene s) {
        _scene = s;
    }

    public void setImageWriter(ImageWriter i) {
        _imageWriter = i;
    }

    //calculate color
    private Color calcColor(Point3D p) {
        return _scene.getAmbientLight().getIntensity();
    }

    //return list of intersections
    private List<Point3D> getSceneRayIntersections(Ray ray) {

        //initialise iterator and list
        Iterator<Geometry> geometries = _scene.getGeometriesIterator();
        List<Point3D> intersectionPoints = new ArrayList<Point3D>();

        //while list is not empty
        while (geometries.hasNext()) {
            Geometry geometry = geometries.next();
            //add each point to the original list
            List<Point3D> geometryIntersectionPoints = geometry.findIntersections(ray);
            for (Point3D intersection : geometryIntersectionPoints) {
                intersectionPoints.add(intersection);
            }
            //  add geometryIntersectionPoints to intersectionPoints
        }
        //return list of intersection points
        return intersectionPoints;

    }

    //find closest point to camera
    private Point3D getClosestPoint(List<Point3D> intersectionPoints) {

        //initialise values for min distance
        double distance = Double.MAX_VALUE;
        Point3D P0 = _scene.getCamera().getP0();
        Point3D minDistancePoint = null;

        //find min distance point in list
        for (Point3D point : intersectionPoints)
            if (P0.distance(point) < distance) {
                minDistancePoint = new Point3D(point);
                distance = P0.distance(point);
            }
        //return min point
        return minDistancePoint;
    }

    //render the image
    public void renderImage() {
        Point3D closestPoint=new Point3D();
        //for every pixel in view plane
        for (int i = 0; i < _imageWriter.getNx(); i++)
            for (int j = 0; j < _imageWriter.getNy(); j++) {
            //form a ray for every pixel
            Ray ray = _scene.getCamera().constructRayThroughPixel
                        (_imageWriter.getNx(), _imageWriter.getNy(),
                                _scene.getDistance(), _imageWriter.getWidth(),
                                _imageWriter.getHeight(), j, i);
            //if list of points is empty
            List<Point3D> intersectionPoints = getSceneRayIntersections(ray);
                if (intersectionPoints.isEmpty())
                    //set color to background color
                    _imageWriter.writePixel(j, i, _scene.getBackground().getColor());
                //if there is a geometry
                else
                    closestPoint = getClosestPoint(intersectionPoints);
                //color with geometry color
                _imageWriter.writePixel(j, i, calcColor(closestPoint));
            }


    }
}