package primitives;

import static java.awt.Color.white;
import java.awt.*;
//color class
public class Color {
    //color of object
    private java.awt.Color color;

//constructors
    public Color(){
        color=white;
    }

    public Color(double one,double two,double three){
        color=new Color(one,two,three);

    }
}
