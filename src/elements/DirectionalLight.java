package elements;

import primitives.Color;
import primitives.Vector;

public class DirectionalLight extends Light {



    //direction of light
    private Vector direction;

    //full constructor
    public DirectionalLight(Vector direction, Color color) {
        super(color);
        this.direction = direction;
    }

    //empty constructor
    public DirectionalLight(){
        super();
    }

    //getters and setters
    public Vector getDirection() {
        return direction;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    //override getIntensity
    @Override
    public Color getIntensity() {

        return get_color();
    }
}
