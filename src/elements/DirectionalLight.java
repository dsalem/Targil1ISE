package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource {
    //direction of light
    private Vector direction;

    //full constructor
    public DirectionalLight(Vector direction, Color color) {
        super(color);
        direction.normalize();
        this.direction = direction;
    }

    //empty constructor
    public DirectionalLight(){
        super();
    }

    //getters and setters
    public Vector getDirection() {
        direction.normalize();
        return direction;
    }

    public void setDirection(Vector direction) {
        direction.normalize();
        this.direction = direction;
    }

    //override getIntensity
    @Override
    public Color getIntensity() {
        return get_color();
    }

    //override getIntensity with point
    @Override
    public Color getIntensity(Point3D p) {
        return null;
    }

    //override get L vector
    @Override
    public Vector getL(Point3D p) {
        Vector copyDirection = new Vector(getDirection());
        copyDirection.normalize();
        return copyDirection;
    }
}
