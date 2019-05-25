
package elements;

import primitives.Color;

public abstract class Light {
    //color
    private Color _color;

    //full constructor
    public Light(Color color) {
        this._color=color;
    }

    //empty constructor
    public Light(){
        _color=new Color();
    }

    //getters and setters
    public Color get_color() {
        return _color;
    }

    public void setColor(Color c) {
        _color=c;
    }

    //find intensity
    public abstract Color getIntensity();
}
