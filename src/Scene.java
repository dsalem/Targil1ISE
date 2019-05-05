package scene;

import elements.AmbientLight;
import elements.Camera;
import geometries.Geometry;
import primitives.Color;

import java.util.Iterator;
import java.util.List;

//class scene
public class Scene {

//scene name
    private String _sceneName;
//background color
    private Color _background;
//ambient light
    private AmbientLight _ambientLight;
//list of geometries
    private List<Geometry> _geometries;
//camera
    private Camera _camera;
//screen distance
    private double _screenDistance;

    //full constructor
    public Scene(String name,Color back,AmbientLight amb,List<Geometry> l,Camera c,double dist){
        _sceneName=name;
        _background=back;
        _ambientLight=amb;
        _geometries=l;
        _camera=c;
        _screenDistance=dist;
    }

    //copy constructor
    public Scene(Scene s){
        this(s._sceneName,s._background,new AmbientLight(s._ambientLight), s._geometries, new Camera(s._camera),s._screenDistance);
    }

    //getters and setters
    public String getName() {return _sceneName;}
    public Color getColor() {return _background;}
    public AmbientLight getAmbientLight() {return _ambientLight;}
    public List<Geometry> getList() {return _geometries;}
    public Camera getCamera() {return _camera;}
    public double getDistance() {return _screenDistance;}


    //get iterator for geometries
    public Iterator<Geometry> getGeometriesIterator(){
        return _geometries.iterator();
    }

    //add a geometry
    public void addGeometry(Geometry g){
        _geometries.add(g);
    }
}
