package elements;

import primitives.Color;

public abstract class Light {
    //color
    private Color _color;

    public Light(Color color) {
        this._color=color;
    }

    public Light(){
        _color.setColor();
    }

    //getters and setters
    public Color get_color() {
        return _color;
    }

    public void setColor(Color c) {
        _color=c;
    }

    //find intensity
    public Color getIntensity(){return null;}
}
