package tests;

import elements.*;
import geometries.Geometry;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

import java.util.ArrayList;
import java.util.List;

public class RenderTest {
    @Test
    public void basicRendering() {

        //initialise camera and scene
        Camera camera = new Camera();
        Color background = new Color(100, 100, 100);
        AmbientLight ambientLight = new AmbientLight(new Color(255, 255, 255), 1);
        List<Geometry> geometries = new ArrayList<Geometry>();
        List<LightSource> lights = new ArrayList<LightSource>();
        // Set the scene
        Scene scene = new Scene("Test scene", background, ambientLight, geometries, lights, camera, 100);
        //Add the geometries
        //Add sphere in centre
        geometries.add(new Sphere(50, new Point3D(new Coordinate(0), new Coordinate(0), new Coordinate(150))));

        //top right triangle
        geometries.add(new Triangle(new Point3D(new Coordinate(100), new Coordinate(0), new Coordinate(149)),
                new Point3D(new Coordinate(0), new Coordinate(100), new Coordinate(149)),
                new Point3D(new Coordinate(100), new Coordinate(100), new Coordinate(149))));

        //bottom right triangle
        geometries.add(new Triangle(new Point3D(new Coordinate(100), new Coordinate(0), new Coordinate(149)),
                new Point3D(new Coordinate(0), new Coordinate(-100), new Coordinate(149)),
                new Point3D(new Coordinate(100), new Coordinate(-100), new Coordinate(149))));

        //top left triangle
        geometries.add(new Triangle(new Point3D(new Coordinate(-100), new Coordinate(0), new Coordinate(149)),
                new Point3D(new Coordinate(0), new Coordinate(100), new Coordinate(149)),
                new Point3D(new Coordinate(-100), new Coordinate(100), new Coordinate(149))));

        //bottom left triangle
        geometries.add(new Triangle(new Point3D(new Coordinate(-100), new Coordinate(0), new Coordinate(149)),
                new Point3D(new Coordinate(0), new Coordinate(-100), new Coordinate(149)),
                new Point3D(new Coordinate(-100), new Coordinate(-100), new Coordinate(149))));

        //Render the image
        ImageWriter imageWriter = new ImageWriter("test0", 500, 500, 500, 500);
        Render render = new Render(scene, imageWriter);

        render.renderImage();
        render.getImageWriter().writeToimage();
    }

    @Test
    //Color test
    public void colorRendering() {
        //initialise camera and scene
        Camera camera = new Camera();
        Color background = new Color(100, 100, 100);
        AmbientLight ambientLight = new AmbientLight(new Color(50, 50, 50), 1);
        List<Geometry> geometries = new ArrayList<Geometry>();
        List<LightSource> lights = new ArrayList<LightSource>();
        // Set the scene
        Scene scene = new Scene("Test scene", background, ambientLight, geometries, lights, camera, 100);
        //Add the geometries
        //Add sphere in centre
        Sphere sphere = new Sphere(50, new Point3D(new Coordinate(0), new Coordinate(0), new Coordinate(150)));
        geometries.add(sphere);
        //initialise materials for 3 colors
        Material red = new Material(1, 1, 1, new Color(255, 0, 0));
        Material green = new Material(1, 1, 1, new Color(0, 255, 0));
        Material blue = new Material(1, 1, 1, new Color(0, 0, 255));

        //bottom left triangle
        Triangle bottomLeft = new Triangle(new Point3D(new Coordinate(100), new Coordinate(0), new Coordinate(149)),
                new Point3D(new Coordinate(0), new Coordinate(100), new Coordinate(149)),
                new Point3D(new Coordinate(100), new Coordinate(100), new Coordinate(149)));
        //set material red
        bottomLeft.set_material(red);
        geometries.add(bottomLeft);

        //top left triangle
        Triangle topLeft = new Triangle(new Point3D(new Coordinate(100), new Coordinate(0), new Coordinate(149)),
                new Point3D(new Coordinate(0), new Coordinate(-100), new Coordinate(149)),
                new Point3D(new Coordinate(100), new Coordinate(-100), new Coordinate(149)));
        //set material green
        topLeft.set_material(green);
        geometries.add(topLeft);

        //bottom right triangle
        Triangle bottomRight = new Triangle(new Point3D(new Coordinate(-100), new Coordinate(0), new Coordinate(149)),
                new Point3D(new Coordinate(0), new Coordinate(100), new Coordinate(149)),
                new Point3D(new Coordinate(-100), new Coordinate(100), new Coordinate(149)));
        //set material blue
        bottomRight.set_material(blue);
        geometries.add(bottomRight);

        //top right triangle
        geometries.add(new Triangle(new Point3D(new Coordinate(-100), new Coordinate(0), new Coordinate(149)),
                new Point3D(new Coordinate(0), new Coordinate(-100), new Coordinate(149)),
                new Point3D(new Coordinate(-100), new Coordinate(-100), new Coordinate(149))));

        //Render the image
        ImageWriter imageWriter = new ImageWriter("colortest", 500, 500, 500, 500);
        Render render = new Render(scene, imageWriter);

        render.renderImage();
        render.getImageWriter().writeToimage();
    }

    @Test
    //Phong Test
    public void SphereSpotLightTest() {
        //initialise camera and scene
        Camera camera = new Camera();
        Color background = new Color(100, 100, 100);
        AmbientLight ambientLight = new AmbientLight(new Color(50, 50, 50), 1);
        List<Geometry> geometries = new ArrayList<Geometry>();
        List<LightSource> lights = new ArrayList<LightSource>();
        // Set the scene
        Material blue2 = new Material(1, 1, 4, new Color(0, 0, 100));
        Material blue = new Material(1, 1, 20, new Color(0, 0, 100));
        Scene scene = new Scene("Test scene", background, ambientLight, geometries, lights, camera, 200);
        //add blue sphere in center
        Sphere sphere = new Sphere(500, new Point3D(new Coordinate(0), new Coordinate(0), new Coordinate(-1000)));
        sphere.set_material(blue);
        scene.addGeometry(sphere);

        //add spot light source
        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(new Coordinate(-200), new Coordinate(-200), new Coordinate(-150)),
               0.1, 0.00001, 0.000005, new Vector(new Point3D(new Coordinate(2),new Coordinate(2),new Coordinate(-3)))));

        //scene.addLight(new PointLight(new Color(255, 100, 100), new Point3D(new Coordinate(-200), new Coordinate(-200), new Coordinate(-100)),0.1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("Spot test sphere", 500, 500, 500, 500);
        //render image
        Render render = new Render(scene,imageWriter);
        render.renderImage();
        render.getImageWriter().writeToimage();
    }

    @Test
    //Phong Test
    public void SpherePointLightTest() {
        //initialise camera and scene
        Camera camera = new Camera();
        Color background = new Color(100, 100, 100);
        AmbientLight ambientLight = new AmbientLight(new Color(50, 50, 50), 1);
        List<Geometry> geometries = new ArrayList<Geometry>();
        List<LightSource> lights = new ArrayList<LightSource>();
        // Set the scene
        Material blue = new Material(1, 1, 20, new Color(0, 0, 100));
        Scene scene = new Scene("Test scene", background, ambientLight, geometries, lights, camera, 100);
        //add blue sphere in center
        Sphere sphere = new Sphere(800, new Point3D(new Coordinate(0), new Coordinate(0), new Coordinate(-1000)));
        sphere.set_material(blue);
        scene.addGeometry(sphere);

        //scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(new Coordinate(-200), new Coordinate(-200), new Coordinate(-100)),
          //      0, 0.00001, 0.000005, new Vector(new Point3D(new Coordinate(2),new Coordinate(2),new Coordinate(-3)))));

        //add point light source
        scene.addLight(new PointLight(new Color(255, 100, 100), new Point3D(new Coordinate(-200), new Coordinate(-200), new Coordinate(-100)),0, 0.00001, 0.000005));
        ImageWriter imageWriter = new ImageWriter("Point Test Sphere", 500, 500, 500, 500);

        //render image
        Render render = new Render(scene,imageWriter);
        render.renderImage();
        render.getImageWriter().writeToimage();
    }

    @Test
    public void TrianglepointLightTest(){
        //initialise camera and scene
        Camera camera = new Camera();
        Color background = new Color(100, 100, 100);
        AmbientLight ambientLight = new AmbientLight(new Color(50, 50, 50), 1);
        List<Geometry> geometries = new ArrayList<Geometry>();
        List<LightSource> lights = new ArrayList<LightSource>();
        // Set the scene
        Scene scene = new Scene("Test scene", background, ambientLight, geometries, lights, camera, 100);
        Material blue = new Material(1, 1, 20, new Color(0, 0, 100));
        Material none = new Material(1, 1, 0, new Color(0, 0, 0));

        //add triangle one
       Triangle triangle = new Triangle(new Point3D(new Coordinate(3500), new Coordinate(3500), new Coordinate(-2000)),
                new Point3D(new Coordinate(-3500), new Coordinate(-3500), new Coordinate(-1000)),
                new Point3D(new Coordinate(3500), new Coordinate(-3500), new Coordinate(-1000)));

       //add triangle two
        Triangle triangle2 = new Triangle(new Point3D(new Coordinate(3500), new Coordinate(3500), new Coordinate(-2000)),
                new Point3D(new Coordinate(-3500), new Coordinate(3500), new Coordinate(-2000)),
                new Point3D(new Coordinate(-3500), new Coordinate(-3500), new Coordinate(-1000)));
        //set materials
        triangle.set_material(none);
        triangle2.set_material(none);
        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);
        //add point light source
        scene.addLight(new PointLight(new Color(255,100,100), new Point3D(new Coordinate(-200),new Coordinate(200), new Coordinate(-100)),
                0, 0.000001, 0.0000005));
        //render image
        ImageWriter imageWriter = new ImageWriter("Point Test Triangle", 500, 500, 500, 500);
        Render render = new Render(scene, imageWriter);
        render.renderImage();
        imageWriter.writeToimage();
    }

    @Test
    public void TrianglespotLightTest(){
        //initialise camera and scene
        Camera camera = new Camera();
        Color background = new Color(100, 100, 100);
        AmbientLight ambientLight = new AmbientLight(new Color(50, 50, 50), 1);
        List<Geometry> geometries = new ArrayList<Geometry>();
        List<LightSource> lights = new ArrayList<LightSource>();
        // Set the scene
        Scene scene = new Scene("Test scene", background, ambientLight, geometries, lights, camera, 100);
        Material none = new Material(1, 1, 0, new Color(0, 0, 0));
        //add triangle one
        Triangle triangle = new Triangle(new Point3D(new Coordinate(3500), new Coordinate(3500), new Coordinate(-2000)),
                new Point3D(new Coordinate(-3500), new Coordinate(-3500), new Coordinate(-1000)),
                new Point3D(new Coordinate(3500), new Coordinate(-3500), new Coordinate(-1000)));
        //add triangle two
        Triangle triangle2 = new Triangle(new Point3D(new Coordinate(3500), new Coordinate(3500), new Coordinate(-2000)),
                new Point3D(new Coordinate(-3500), new Coordinate(3500), new Coordinate(-2000)),
                new Point3D(new Coordinate(-3500), new Coordinate(-3500), new Coordinate(-1000)));
        //set materials
        triangle.set_material(none);
        triangle2.set_material(none);
        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);
        //add spot light source
        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(new Coordinate(-200),new Coordinate(200),new Coordinate(-100) ),
                0, 0.000001, 0.0000005, new Vector(new Point3D(new Coordinate(-2),new Coordinate(-2),new Coordinate(-3)))));
        //render image
        ImageWriter imageWriter = new ImageWriter("Spot Test Triangle", 500, 500, 500, 500);
        Render render = new Render(scene, imageWriter);
        render.renderImage();
        imageWriter.writeToimage();
    }

    @Test
    public void Shadowtest() {
        //initialise camera and scene
        Camera camera = new Camera();
        Color background = new Color(100, 100, 100);
        AmbientLight ambientLight = new AmbientLight(new Color(50, 50, 50), 1);
        List<Geometry> geometries = new ArrayList<Geometry>();
        List<LightSource> lights = new ArrayList<LightSource>();
        // Set the scene
        Material blue = new Material(1, 1, 20, new Color(0, 0, 100));
        Material blue1 = new Material(1, 1, 4, new Color(0, 0, 100));
        Scene scene = new Scene("Test scene", background, ambientLight, geometries, lights, camera, 200);
        //add blue sphere in center
        Sphere sphere = new Sphere(500, new Point3D(new Coordinate(0), new Coordinate(0), new Coordinate(-1000)));
        sphere.set_material(blue);
        scene.addGeometry(sphere);

        //add triangle to block sphere
        Triangle triangle = new Triangle(new Point3D(new Coordinate(-125), new Coordinate(-225), new Coordinate(-260)),
                new Point3D(new Coordinate(-225), new Coordinate(-125), new Coordinate(-260)),
                new Point3D(new Coordinate(-225), new Coordinate(-225), new Coordinate(-270)));
        triangle.set_material(blue1);
        scene.addGeometry(triangle);
        //add spot light source
        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(new Coordinate(-200), new Coordinate(-200), new Coordinate(-150)),
                0.1, 0.00001, 0.000005, new Vector(new Point3D(new Coordinate(2),new Coordinate(2),new Coordinate(-3)))));

        //scene.addLight(new PointLight(new Color(255, 100, 100), new Point3D(new Coordinate(-200), new Coordinate(-200), new Coordinate(-100)),0.1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("shadowTest", 500, 500, 500, 500);
        //render image
        Render render = new Render(scene,imageWriter);
        render.renderImage();
        render.getImageWriter().writeToimage();
    }

    @Test
    public void triangle_hide_sphere(){
        Sphere middle = new Sphere(49,new Point3D(new Coordinate(0), new Coordinate(0), new Coordinate(-50))
        );
        Material blue = new Material(1.5, 1, 20, new Color(0, 20, 100));
        middle.set_material(blue);

        Triangle triangle = new Triangle(new Point3D(new Coordinate(-1),new Coordinate(1),new Coordinate(-0.99)),
                new Point3D(new Coordinate(-3),new Coordinate(5),new Coordinate(-0.99))
                ,new Point3D(new Coordinate(1),new Coordinate(5),new Coordinate(-0.99)));
        Material red = new Material(1,1,20, new Color(255,0,0));



        List<Geometry> geometries = new ArrayList<Geometry>();
        List<LightSource> lights = new ArrayList<LightSource>();

        Camera camera = new Camera(new Point3D(new Coordinate(0), new Coordinate(0), new Coordinate(0)),
                new Vector(new Point3D(new Coordinate(0),new Coordinate(-1),new Coordinate(0))),
                new Vector(new Point3D(new Coordinate(0),new Coordinate(0),new Coordinate(-1))),
                new Vector(new Point3D(new Coordinate(1),new Coordinate(0),new Coordinate(0))));

        Scene myScene = new Scene("sphere and triangle",new Color(0, 0, 0),new AmbientLight(new Color(20, 20, 20), 0.1),geometries
                ,lights,camera,50);

        PointLight myPointLight = new PointLight(new Color (255,255,255),
                new Point3D(new Coordinate(-2),new Coordinate(1),new Coordinate(5)),
                1, 0.01, 0.025 // 1, 0.01, 0.1,
        );
        myScene.addLight(myPointLight);
        //    myScene.addGeometry(triangle);
        myScene.addGeometry(middle);

        ImageWriter sceneWriter = new ImageWriter("sphere and triangle",640,640,640,640);
        Render myRender = new Render(myScene,sceneWriter);

        myRender.renderImage();
        //   myRender.renderPixel(350,500);
        //myRender.printGrid(50);
        sceneWriter.writeToimage();
    }
}
