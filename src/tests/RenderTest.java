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

        Camera camera = new Camera(new Point3D(new Coordinate(0), new Coordinate(0), new Coordinate(0)),
                new Vector(new Point3D(new Coordinate(0), new Coordinate(-1), new Coordinate(0))),
                new Vector(new Point3D(new Coordinate(0), new Coordinate(0), new Coordinate(1)));


     //   scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
      //  scene.setDistance(100);
        Color background = new Color(0, 0, 0);
        AmbientLight ambientLight = new AmbientLight();
        List<Geometry> geometries= new ArrayList<Geometry>();
       // scene.setGeometries(geometries);
        Scene scene = new Scene("Test scene",background,ambientLight,geometries,camera,100);
        geometries.add(new Sphere(new Color(100, 100,100 ), 50, new Point3D(0, 0, 150)));

        geometries.add(new Triangle(new Color(100, 100,100 ), new Point3D( 100, 0, 149),
                new Point3D(  0, 100, 149),
                new Point3D( 100, 100, 149)));

        geometries.add(new Triangle(new Color(100, 100,100 ), new Point3D( 100, 0, 149),
                new Point3D(  0, -100, 149),
                new Point3D( 100,-100, 149)));

        geometries.add(new Triangle(new Color(100, 100,100 ), new Point3D(-100, 0, 149),
                new Point3D(  0, 100, 149),
                new Point3D(-100, 100, 149)));

        geometries.add(new Triangle(new Color(100, 100,100 ), new Point3D(-100, 0, 149),
                new Point3D(  0,  -100, 149),
                new Point3D(-100, -100, 149)));

        ImageWriter imageWriter = new ImageWriter("test0", 500, 500, 500, 500);
        Render render = new Render(scene, imageWriter);

        render.renderImage();
    //    render.printGrid(50);
        render.getImageWriter().writeToimage();
    }
}