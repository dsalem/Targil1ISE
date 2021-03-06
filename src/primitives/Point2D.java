package primitives;
//Point2D class
public class Point2D {
    //Coordinate x of 2d point
    private Coordinate _x;
    //Coordinate y of 2d point
    private Coordinate _y;

    //full constructor
    public Point2D(Coordinate _x, Coordinate _y) {
        this._x = _x;
        this._y = _y;
    }

    //copy constructor
    public Point2D(Point2D p) {
        this(new Coordinate(p.get_x()),new Coordinate(p.get_y()));
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

    //comparison with other point2d
    public int compareTo (Point2D p){
        if(  _x.compareTo(p.get_x()) == 1 &&  _y.compareTo(p.get_y())==1)
            return 1;
        return 0;
    }
}

