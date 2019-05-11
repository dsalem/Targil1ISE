package elements;


import primitives.Color;

//class Ambient lighting
public class AmbientLight extends Light{
    //color of the lighting
    private Color _color;
    //measure of intensity
    private double Ka;

    //full constructor
    public AmbientLight(Color c,double d){
        _color=c;
        Ka =d;
    }

    //copy constructor
    public AmbientLight(AmbientLight a){
        this(a._color,a.Ka);
    }

    //empty constructor
    public AmbientLight(){
        _color.setColor(0,0,0);
        Ka=0;
    }

    //getters/setters
    public Color getColor(){
        return _color;
    }

    public double getKa(){
        return Ka;
    }

    public void setColor(Color c) {
        _color=c;
    }

    public void setKa(double d){
        Ka=d;
    }

    //find intensity
    public Color getIntensity(){
        return _color.scale(getKa());
    }
}
