package primitives;

public class Point2D {
    private Coordinate _x;
    private Coordinate _y;

    //constructor
    public Point2D(Coordinate _x, Coordinate _y) {
        this._x = _x;
        this._y = _y;
    }

    //copy constructor
    public Point2D(Point2D p) {
        _x = p._x;
        _y = p._y;
    }

    //empty constructor
    public Point2D() {
        _x = new Coordinate();
        _y = new Coordinate();
    }


    //getters and setters
    public Coordinate get_x() {
        return _x;
    }

    public void set_x(Coordinate _x) {
        this._x = _x;
    }

    public Coordinate get_y() {
        return _y;
    }

    public void set_y(Coordinate _y) {
        this._y = _y;
    }


    public int compareTo (Point2D p){
      if(  _x.compareTo(p.get_x()) == 1 &&  _y.compareTo(p.get_y())==1)
          return 1;
      return 0;
    }
}
