package elements;


import primitives.Color;

import static java.awt.Color.white;

//class Ambient lighting
public class AmbientLight extends Light {
    //color of the lighting
//    private Color _color;
    //measure of intensity
    private double Ka;

    //full constructor
    public AmbientLight(Color c,double d){
        setColor(c);
        Ka =d;
    }

    //copy constructor
    public AmbientLight(AmbientLight a){
        this(a.get_color(),a.Ka);
    }

    //empty constructor
    public AmbientLight(){
        setColor(new Color());
        Ka=0;
    }

    //getters/setters

    public double getKa(){
        return Ka;
    }

    public void setKa(double d){
        Ka=d;
    }

    //find intensity
    public Color getIntensity(){
        return get_color().scale(getKa());
    }
    }

