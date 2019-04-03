package primitives;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Vector {
    private Point3D _head;

    //constructor
    public Vector(Point3D _head) {
        this._head = _head;
    }

    //copy constructor
    public Vector(Vector v) {
        this(new Point3D(v.get_head()));
    }

    //empty constructor
    public Vector() {
        _head = new Point3D();
    }


    //getters and setters
    public Point3D get_head() {
        return _head;
    }

    public void set_head(Point3D _head) {
        this._head = _head;
    }


    public int compareTo(Vector v) {
        if (get_head().compareTo(v.get_head()) == 1)
            return 1;
        return 0;

    }

    public Vector add(Vector v) {
        _head.add(v);
        return this;
    }



    public Vector subtract(Vector v) {
        _head.subtract(v);
        return this;
    }

    //
    //
    //
    public Vector crossProduct(Vector v) {
        Vector myVector = new Vector();
        myVector.get_head().get_x().set_coordinate(get_head().get_y().get_coordinate() * v.get_head().get_z().get_coordinate()
                - get_head().get_z().get_coordinate() * v.get_head().get_y().get_coordinate());

        myVector.get_head().get_y().set_coordinate(get_head().get_z().get_coordinate() * v.get_head().get_x().get_coordinate()
                - get_head().get_x().get_coordinate() * v.get_head().get_z().get_coordinate());

        myVector.get_head().get_z().set_coordinate(get_head().get_x().get_coordinate() * v.get_head().get_y().get_coordinate()
                - get_head().get_y().get_coordinate() * v.get_head().get_x().get_coordinate());

        return myVector;

    }
//return the square root of x^2 + y^2 +z^2
    public double length() {
        return sqrt(pow(_head.get_x().get_coordinate(),2) +
                pow(_head.get_y().get_coordinate(),2)+
                pow(_head.get_z().get_coordinate(),2));
    }

    public void normalize() {
        double length = length();
        _head.get_x().set_coordinate(_head.get_x().get_coordinate()/length);
        _head.get_y().set_coordinate(_head.get_y().get_coordinate()/length);
        _head.get_z().set_coordinate(_head.get_z().get_coordinate()/length);

    }
    public void scaling(double num) {
        
        _head.get_x().set_coordinate(_head.get_x().get_coordinate()*num);
        _head.get_y().set_coordinate(_head.get_y().get_coordinate()*num);
        _head.get_z().set_coordinate(_head.get_z().get_coordinate()*num);

    }
    public Vector scalingV(double num) {
        _head.get_x().set_coordinate(_head.get_x().get_coordinate()*num);
        _head.get_y().set_coordinate(_head.get_y().get_coordinate()*num);
        _head.get_z().set_coordinate(_head.get_z().get_coordinate()*num);
        return this;

    }


    // calculate the dot product of a vector as follows: u ⋅ v = u1v1 + u2v2 + u3v3
    public double dotProduct(Vector v) {

        return  _head.get_x().get_coordinate()*v.get_head().get_x().get_coordinate() +
                _head.get_y().get_coordinate()*v.get_head().get_y().get_coordinate()+
                _head.get_z().get_coordinate()*v.get_head().get_z().get_coordinate();
    }

}
