package primitives;

//coordinate class that represents a coordinate on a graph
public class Coordinate {
    //Coordinate on a plane represented by a double
    private double _coordinate;

    //full constructor
    public Coordinate(double _coordinate) {
        this._coordinate = _coordinate;
    }

    //empty constructor
    public Coordinate() {
        _coordinate = 0;
    }

    //copy constructor
    public Coordinate(Coordinate c) {
        this(c.get_coordinate());
    }
    //getters and setters
    public double get_coordinate() {
        return _coordinate;
    }

    public void set_coordinate(double _coordinate) {
        this._coordinate = _coordinate;
    }

    //comparison with other coordinate
    public int compareTo (Coordinate c){
        if (_coordinate == c._coordinate)
            return 1;
        return -1;
    }
    //add coordinates
    public Coordinate add (Coordinate c){
        _coordinate += c._coordinate;
        return this;
    }
    //subtract coordinates
    public Coordinate subtract (Coordinate c){
        _coordinate -= c._coordinate;
        return this;
    }
}
