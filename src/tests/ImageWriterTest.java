package tests;

import org.junit.Test;
import primitives.Color;
import renderer.ImageWriter;

import static org.junit.Assert.*;

public class ImageWriterTest {

    @Test

    public void writeToimage() {
        ImageWriter myImageWriter = new ImageWriter("grid test",500,500,500,500);
        for (int i = 0; i<=499;i++){
            for (int j = 499; j>=0;j--) {
                if (i % 50 == 0 || j % 50 == 0)
                    myImageWriter.writePixel(i, j, java.awt.Color.WHITE);
                else
                    myImageWriter.writePixel(i, j, java.awt.Color.BLACK);


            }
        }
        myImageWriter.writeToimage();
    }
}