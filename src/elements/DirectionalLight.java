package elements;

import primitives.Color;
import primitives.Vector;

public class DirectionalLight extends Light {

    //direction of light
    private Vector direction;

    public Vector getDirection() {
        return direction;
    }

    public DirectionalLight(Vector direction, Color color) {
        super(color);
        this.direction = direction;
    }
    public DirectionalLight(){
        super();
    }


    @Override
    public Color getIntensity() {

        return get_color();
    }
}
