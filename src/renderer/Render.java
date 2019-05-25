package renderer;

import elements.LightSource;
import geometries.Geometry;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

    //return list of intersections
    private Map<Geometry, List<Point3D>> getSceneRayIntersections(Ray ray) {

        //initialise iterator and list
        Iterator<Geometry> geometries = _scene.getGeometriesIterator();
        Map<Geometry, List<Point3D>> intersectionPoints = new HashMap<Geometry, List<Point3D>>();

        //while list is not empty
        while (geometries.hasNext()) {
            Geometry geometry = geometries.next();
            //add each point to the original list
            List<Point3D> geometryIntersectionPoints = geometry.findIntersections(ray);
            //  add geometryIntersectionPoints to intersectionPoints
            intersectionPoints.put(geometry, geometryIntersectionPoints);
        }
        //return list of intersection points
        return intersectionPoints;

    }

    //find closest point to camera
    private Map<Geometry, Point3D> getClosestPoint(Map<Geometry, List<Point3D>> intersectionPoints) {

        //initialise values for min distance
        double distance = Double.MAX_VALUE;
        Point3D P0 = _scene.getCamera().getP0();
        Map<Geometry, Point3D> minDistancePoint = new HashMap<Geometry, Point3D>();

        //find min distance point in list
        for (Map.Entry<Geometry, List<Point3D>> entry :
                intersectionPoints.entrySet()) {
            //for every point in list
            for (Point3D point : entry.getValue())
                //if point is closest, it becomes the new point
                if (P0.distance(point) < distance) {
                    minDistancePoint.clear();
                    minDistancePoint.put(entry.getKey(), new Point3D(point));
                    distance = P0.distance(point);
                }
        }
        //return min point
        return minDistancePoint;
    }

    //render the image
    public void renderImage() {
        //for every pixel in view plane
        for (int i = 0; i < _imageWriter.getNx(); i++)
            for (int j = 0; j < _imageWriter.getNy(); j++) {
                //form a ray for every pixel
                Ray ray = _scene.getCamera().constructRayThroughPixel
                        (_imageWriter.getNx(), _imageWriter.getNy(), j, i,
                                _scene.getDistance(), _imageWriter.getWidth(),
                                _imageWriter.getHeight());
                //if list of points is empty
                Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(ray);
                if (intersectionPoints.isEmpty()) {
                    //set color to background color
                    _imageWriter.writePixel(j, i, _scene.getBackground().getColor());
                }
                //if there is a geometry
                else {
                    Map<Geometry, Point3D> closestPoint = getClosestPoint(intersectionPoints);
                    for (Map.Entry<Geometry, List<Point3D>> entry :
                            intersectionPoints.entrySet()) {
                        //for every point in list
                        for (Point3D point : entry.getValue())
                            //color with geometry color
                            _imageWriter.writePixel(j, i, calcColor(entry.getKey(), point));
                    }
                }
            }
    }

    //calculate color
    private Color calcColor(Geometry geometry, Point3D p) {
        //copy point p
        Point3D copy = new Point3D(p);
        //initialise emmission and ambient light
        Color emissionLight = geometry.get_material().get_emission();
        Color ambientLight = _scene.getAmbientLight().getIntensity();
        //create iterator for Lightsource list
        Iterator<LightSource> lights = _scene.getLightsIterator();
        //instantiate colors
        Color diffuseLight = new Color(0,0,0);
        Color specularLight = new Color(0,0,0);
        //form new vector for function
        Point3D temp = new Point3D(copy.subtract(getScene().getCamera().getP0()));
        Vector specular = new Vector(temp);
        specular.normalize();
        //For all the different light sources
        while(lights.hasNext()){
            LightSource currentLight = lights.next();
            //add diffuse light
            diffuseLight.add( calcDiffusiveComp(geometry.get_material().get_Kd(),
                    geometry.getNormal(p),currentLight.getL(p),currentLight.getIntensity(p)));
            //add specular light
            specularLight.add(calcSpecularComp(geometry.get_material().get_Ks(),specular,
                    geometry.getNormal(p),currentLight.getL(p),geometry.get_material().get_nShininess(),
                    currentLight.getIntensity(p)));
        }
        //add all the light sources
        return (ambientLight.add(emissionLight,diffuseLight,specularLight));

    }


    //HELPER FUNCTIONS
    //calculate diffusive light
    private Color calcDiffusiveComp(double Kd, Vector normal, Vector l, Color intensity){
        //obtain dot product of normal with L
        double dotp = normal.dotProduct(l);
        if (dotp>1)
            dotp=1;
        if(dotp<0)
            dotp=0;
        //multiply dot product by Kd
        dotp*=Kd;
        //scale intensity by new dot product
        return intensity.scale(dotp);
    }

    //calculate specular light
    private Color calcSpecularComp(double Ks,Vector direction,Vector normal,Vector l,double shininess,Color intensity){
        //dot product of direction and reflected ray : V.R
        direction.normalize();
        double dotp = Math.max(0,direction.dotProduct(calcR(l,normal)));
        //exponent by n
        double exponent = Math.pow(dotp,shininess);
        //scale by Ks
        exponent*=Ks;
        //scale intensity with exponent
        return intensity.scale(exponent);
    }

    //calculate R
    private Vector calcR(Vector l, Vector normal){
        Vector copyl = new Vector(l);
        Vector copyl2 = new Vector(l);
        Vector copyNormal = new Vector(normal);
        //obtain dot product of direction and normal to calculate reflected ray
        double dotProduct = copyl.dotProduct(normal);
        //scale normal with result
        copyNormal.scaling(dotProduct);
        copyNormal.scaling(2);
        //return scale of direction with result
        copyl2.subtract(copyNormal);
        copyl2.normalize();
        return copyl2;
    }
}
