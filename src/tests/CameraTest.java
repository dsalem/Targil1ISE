package tests;

import org.junit.Test;
import primitives.*;
import elements.*;

import static java.lang.Math.sqrt;
import static org.junit.Assert.*;
//tests for camera
public class CameraTest {
//test construct ray function
    @Test
    public void constructRayThroughPixel() {
        Camera camera = new Camera();
        //test first ray for upper left pixel
        Ray ray1 = new Ray(camera.constructRayThroughPixel(3, 3, 0, 0, 1, 9, 9));
        assertEquals("constructRay failed for upper left pixel", ray1.get_direction().get_head().get_x().get_coordinate(), -3 / sqrt(19), 1e-10);
        assertEquals("constructRay failed for upper left pixel", ray1.get_direction().get_head().get_y().get_coordinate(), 3 / sqrt(19), 1e-10);
        assertEquals("constructRay failed for upper left pixel", ray1.get_direction().get_head().get_z().get_coordinate(), -1 / sqrt(19), 1e-10);

    //test second ray for central pixel
        Ray ray2=new Ray(camera.constructRayThroughPixel(3,3,1,1,1,9,9));
        assertEquals("constructRay failed for central pixel",ray2.get_direction().get_head().get_x().get_coordinate(),0,1e-10);
        assertEquals("constructRay failed for central pixel",ray2.get_direction().get_head().get_y().get_coordinate(),0,1e-10);
        assertEquals("constructRay failed for central pixel",ray2.get_direction().get_head().get_z().get_coordinate(),-1,1e-10);

    //test third ray for lower right pixel
        Ray ray3=new Ray(camera.constructRayThroughPixel(3,3,2,2,1,9,9));
        assertEquals("constructRay failed for central pixel",ray3.get_direction().get_head().get_x().get_coordinate(),3/sqrt(19),1e-10);
        assertEquals("constructRay failed for central pixel",ray3.get_direction().get_head().get_y().get_coordinate(),-3/sqrt(19),1e-10);
        assertEquals("constructRay failed for central pixel",ray3.get_direction().get_head().get_z().get_coordinate(),-1/sqrt(19),1e-10);
    }
}
