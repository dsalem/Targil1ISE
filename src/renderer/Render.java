package renderer;

import elements.LightSource;
import geometries.FlatGeometry;
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

    //set recursion level
    double RECURSION_LEVEL = 2;

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
            if (!geometryIntersectionPoints.isEmpty())
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
                    for (Map.Entry<Geometry, Point3D> entry :
                            closestPoint.entrySet()) {
                        //for every point in list
                        // for (Point3D point : entry.getValue())
                        //color with geometry color
                        _imageWriter.writePixel(j, i, calcColor(entry.getKey(), entry.getValue(),ray,0));
                    }
                }
            }
    }

    //calculate color with level
    private Color calcColor(Geometry geometry, Point3D p, Ray inRay,int level) {
        //base case for recursion
          if (level == RECURSION_LEVEL)
            return new Color(0, 0, 0);
        //copy point p
        Point3D copy = new Point3D(p);
        //initialise emmission and ambient light
        Color emissionLight = geometry.get_material().get_emission();
        Color ambientLight = _scene.getAmbientLight().getIntensity();
        //create iterator for Lightsource list
        Iterator<LightSource> lights = _scene.getLightsIterator();
        //instantiate colors
        Color diffuseLight = new Color(0, 0, 0);
        Color specularLight = new Color(0, 0, 0);
        //form new vector for function
        Point3D temp = new Point3D(copy.subtract(getScene().getCamera().getP0()));
        Vector specular = new Vector(temp);
        specular.normalize();
        //For all the different light sources
        while (lights.hasNext()) {
            LightSource currentLight = lights.next();
            //if not a shadow pixel
            if (!occluded(currentLight, p, geometry)) {
                if (Math.signum(currentLight.getL(p).dotProduct(geometry.getNormal(p))) == Math.signum(new Vector(p.subtract(getScene().getCamera().getP0())).dotProduct(geometry.getNormal(p)))) {
                    //add diffuse light
                    diffuseLight.add(calcDiffusiveComp(geometry.get_material().get_Kd(),
                            geometry.getNormal(p), currentLight.getL(p), currentLight.getIntensity(p)));
                    //add specular light
                    specularLight.add(calcSpecularComp(geometry.get_material().get_Ks(), specular,
                            geometry.getNormal(p), currentLight.getL(p), geometry.get_material().get_nShininess(),
                            currentLight.getIntensity(p)));
                }
            }
        }


        //Recursive call for reflected ray
        Ray reflectedRay = new Ray(constructReflectedRay(geometry.getNormal(p), p, inRay));
        Map<Geometry, List<Point3D>> reflectedList = (getSceneRayIntersections(reflectedRay));
        Map<Geometry,Point3D> reflectedEntry=getClosestPoint(reflectedList);
        Color reflectedColor = new Color();
        for (Map.Entry<Geometry, Point3D> entry : reflectedEntry.entrySet())
            reflectedColor = calcColor(entry.getKey(), entry.getValue(), reflectedRay, level + 1);
        double Kr = geometry.get_material().get_Kr();
        Color reflectedLight = new Color(reflectedColor.scale(Kr));

        //Recursive call for refracted ray
        Ray refractedRay = new Ray(constructRefractedRay(geometry.getNormal(p), p, inRay));
        Map<Geometry, List<Point3D>> refractedList = getSceneRayIntersections(refractedRay);
        Map<Geometry,Point3D> refractedEntry = getClosestPoint(refractedList);
        Color refractedColor = new Color();
        for (Map.Entry<Geometry, Point3D> entry : refractedEntry.entrySet())
            refractedColor = calcColor(entry.getKey(), entry.getValue(), refractedRay, level + 1);
        double Kt = geometry.get_material().get_Kt();
        Color refractedLight = new Color(refractedColor.scale(Kt));

        //add all the light sources
        return (ambientLight.add(emissionLight, diffuseLight, specularLight, reflectedLight, refractedLight));

    }

    //calculate color with no level
    private Color calcColor(Geometry geometry, Point3D p, Ray inRay) {
        //TODO = BUUUUUG
        return calcColor(geometry, p, inRay, 0);
    }



    //HELPER FUNCTIONS
    //calculate diffusive light
    private Color calcDiffusiveComp(double Kd, Vector normal, Vector l, Color intensity) {
        //obtain dot product of normal with L
        double dotp = normal.dotProduct(l);
        if (dotp < 0)
            dotp *= -1;
        //multiply dot product by Kd
        dotp *= Kd;
        //scale intensity by new dot product
        return intensity.scale(dotp);
    }

    //calculate specular light
    private Color calcSpecularComp(double Ks, Vector direction, Vector normal, Vector l, int shininess, Color
            intensity) {
        //dot product of direction and reflected ray : V.R
        direction.scaling(-1);
        double dotp = direction.dotProduct(calcR(l, normal));
        //exponent by n
        double exponent = Math.pow(dotp, shininess);
        //scale by Ks
        exponent *= Ks;
        //scale intensity with exponent
        return intensity.scale(exponent);
    }

    //calculate R
    private Vector calcR(Vector l, Vector normal) {
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

    //check if shadow pixel
    private boolean occluded(LightSource light, Point3D point, Geometry geometry) {
        //Obtain vector from point to light source
        Vector lightDirection = light.getL(point);
        lightDirection.scaling(-1);
        //Obtain vector of normal to point * 2
        Point3D geometryPoint = new Point3D(point);
        Vector epsVector = new Vector(geometry.getNormal(point));
        epsVector.scaling(2);
        //Form new point above geometry
        geometryPoint.add(epsVector);
        //Form new ray from point to light source
        Ray lightRay = new Ray(geometryPoint, lightDirection);
        //Obtain points of intersection
        Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(lightRay);
        //if intersection is a flat geometry - remove from list
        if (geometry instanceof FlatGeometry)
            intersectionPoints.remove(geometry);
        //return true if point exists/if shadow pixel
        if(intersectionPoints.isEmpty())
            return false;

        else {
            for (Map.Entry<Geometry, List<Point3D>> entry : intersectionPoints.entrySet()) {
                if (entry.getKey().get_material().get_Kt() == 0)
                    return true;
                return false;
            }
        }

        //TODO
        return intersectionPoints.containsKey(geometry.get_material().get_Kt() == 0);
    }

    //construct reflected ray
    private Ray constructReflectedRay(Vector normal, Point3D p, Ray inRay){
        //copy values
        Vector cpyNormal = new Vector(normal);
        Ray cpyRay = new Ray(inRay);
        //obtain dot product of ray from camera and normal
        double dotProduct = normal.dotProduct(inRay.get_direction());
        //scale by 2
        dotProduct*=2;
        //scale normal by result
        cpyNormal.scaling(dotProduct);
        //subtract normal from ray direction
        cpyRay.get_direction().subtract(cpyNormal);
        return cpyRay;
    }


    //construct refracted ray
    private Ray constructRefractedRay(Vector normal, Point3D p, Ray inRay) {
    Ray ray = new Ray(p,inRay.get_direction());
    return ray;
    }

}
