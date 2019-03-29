package primitives;

//coordinate class that represents a coordinate on a graph
public class Coordinate {
    private double _coordinate;


    //constructor
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

    public double get_coordinate() {
        return _coordinate;
    }

    public void set_coordinate(double _coordinate) {
        this._coordinate = _coordinate;
    }



   public int compareTo (Coordinate c){
        if (_coordinate == c._coordinate)
            return 1;
        return -1;
   }
public Coordinate add (Coordinate c){
        _coordinate += c._coordinate;
        return this;
}
public Coordinate subtract (Coordinate c){
    _coordinate -= c._coordinate;
    return this;
}
}
