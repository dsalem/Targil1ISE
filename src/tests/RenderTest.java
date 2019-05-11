package tests;

import elements.AmbientLight;
import org.junit.Test;

import elements.Camera;
import geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

import java.util.ArrayList;
import java.util.List;

public class RenderTest {
    @Test
    public void basicRendering(){

        //initialise camera and scene
        Camera camera=new Camera();
        Color background = new Color(100, 100, 100);
        AmbientLight ambientLight = new AmbientLight(new Color(255,255,255),1);
        List<Geometry> geometries= new ArrayList<Geometry>();
        // Set the scene
        Scene scene = new Scene("Test scene",background,ambientLight,geometries,camera,100);
        //Add the geometries
        //Add sphere in centre
        geometries.add(new Sphere( 50, new Point3D(new Coordinate(0), new Coordinate(0), new Coordinate(150))));

        //top right triangle
        geometries.add(new Triangle(new Point3D( new Coordinate(100), new Coordinate(0), new Coordinate(149)),
                new Point3D( new Coordinate(0), new Coordinate(100), new Coordinate(149)),
                new Point3D( new Coordinate(100),new Coordinate(100), new Coordinate(149))));

        //top left triangle
        geometries.add(new Triangle(new Point3D( new Coordinate(100), new Coordinate(0), new Coordinate(149)),
                new Point3D( new Coordinate(0), new Coordinate(-100), new Coordinate(149)),
                new Point3D( new Coordinate(100),new Coordinate(-100), new Coordinate(149))));

        //bottom right triangle
        geometries.add(new Triangle(new Point3D( new Coordinate(-100), new Coordinate(0), new Coordinate(149)),
                new Point3D( new Coordinate(0), new Coordinate(100), new Coordinate(149)),
                new Point3D( new Coordinate(-100),new Coordinate(100), new Coordinate(149))));

        //bottom left triangle
        geometries.add(new Triangle(new Point3D( new Coordinate(-100), new Coordinate(0), new Coordinate(149)),
                new Point3D( new Coordinate(0), new Coordinate(-100), new Coordinate(149)),
                new Point3D( new Coordinate(-100),new Coordinate(-100), new Coordinate(149))));

        //Render the image
        ImageWriter imageWriter = new ImageWriter("test0", 500, 500, 500, 500);
        Render render = new Render(scene, imageWriter);

        render.renderImage();
        render.getImageWriter().writeToimage();
    }
}
