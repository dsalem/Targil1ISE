package renderer;

import primitives.Color;
import primitives.Point3D;
import scene.Scene;

//class render
public class Render {
    //scene member
    private Scene _scene;
    //image writer member
    private ImageWriter _imageWriter;

    //full constructor
    public Render(Scene s,ImageWriter i){
        _scene=s;
        _imageWriter=i;
    }

    //copy constructor
    public Render(Render r){
        this(new Scene(r._scene),new ImageWriter(r._imageWriter));
    }

    //getters/setters
    public Scene getScene(){return _scene; }
    public ImageWriter getImageWriter() {return _imageWriter;}
    public void setScene(Scene s){_scene =s;}
    public void setImageWriter(ImageWriter i){_imageWriter=i;}

    //calculate color
    private Color calcColor(Point3D p){
        return _scene.getAmbientLight().getIntensity();
    }


}
