package elements;


import primitives.Color;

//class Ambient lighting
public class AmbientLight extends Light{
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
        get_color().setColor();
        Ka=0;
    }

    //getters/setters

    public double getKa(){
        return Ka;
    }

    public void setColor(Color c) {
        get_color().setColor(c);
    }

    public void setKa(double d){
        Ka=d;
    }

    //find intensity
    public Color getIntensity(){
        return get_color().scale(getKa());
    }
}
