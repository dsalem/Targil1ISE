package primitives;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
//vector class
public class Vector {
    //Point representing head of vector
    private Point3D _head;

    // full constructor
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

    //comparison with other vector
    public int compareTo(Vector v) {
        if (get_head().compareTo(v.get_head()) == 1)
            return 1;
        return 0;

    }
    //add two vectors
    public Vector add(Vector v) {
        _head.add(v);
        return this;
    }

    //subtract two vectors
    public Vector subtract(Vector v) {
        _head.subtract(v);
        return this;
    }

    //obtain cross product of two vectors
    public Vector crossProduct(Vector v) {
        //initialise new vector for cross product
        Vector myVector = new Vector();
        //set x coordinate
        myVector.get_head().get_x().set_coordinate(get_head().get_y().get_coordinate() * v.get_head().get_z().get_coordinate()
                - get_head().get_z().get_coordinate() * v.get_head().get_y().get_coordinate());
        //set y coordinate
        myVector.get_head().get_y().set_coordinate(get_head().get_z().get_coordinate() * v.get_head().get_x().get_coordinate()
                - get_head().get_x().get_coordinate() * v.get_head().get_z().get_coordinate());
        //set z coordinate
        myVector.get_head().get_z().set_coordinate(get_head().get_x().get_coordinate() * v.get_head().get_y().get_coordinate()
                - get_head().get_y().get_coordinate() * v.get_head().get_x().get_coordinate());

        return myVector;

    }
    //return the length of vector
    public double length() {
        return sqrt(pow(_head.get_x().get_coordinate(),2) +
                pow(_head.get_y().get_coordinate(),2)+
                pow(_head.get_z().get_coordinate(),2));
    }
    //normalize vector to length 1
    public void normalize() {
        double length = length();
        //divide each coordinate by vector length
        _head.get_x().set_coordinate(_head.get_x().get_coordinate()/length);
        _head.get_y().set_coordinate(_head.get_y().get_coordinate()/length);
        _head.get_z().set_coordinate(_head.get_z().get_coordinate()/length);
    }
    //scale a vector
    public void scaling(double num) {
        //scale each coordinate by multiplication
        _head.get_x().set_coordinate(_head.get_x().get_coordinate()*num);
        _head.get_y().set_coordinate(_head.get_y().get_coordinate()*num);
        _head.get_z().set_coordinate(_head.get_z().get_coordinate()*num);
    }
    //scale a vector and returns a vector
    public Vector scalingV(double num) {
        //scale each coordinate by multiplication
        _head.get_x().set_coordinate(_head.get_x().get_coordinate()*num);
        _head.get_y().set_coordinate(_head.get_y().get_coordinate()*num);
        _head.get_z().set_coordinate(_head.get_z().get_coordinate()*num);
        return this;
    }


    // calculate the dot product of a vector as follows: u ⋅ v = u1v1 + u2v2 + u3v3
    public double dotProduct(Vector v) {
        //multiply x,y and z coordinates and add the result to obtain an integer
        double sum = _head.get_x().get_coordinate()*v.get_head().get_x().get_coordinate() +
                _head.get_y().get_coordinate()*v.get_head().get_y().get_coordinate()+
                _head.get_z().get_coordinate()*v.get_head().get_z().get_coordinate();
        return sum;
    }

}
