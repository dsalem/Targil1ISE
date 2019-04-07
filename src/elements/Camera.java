
package elements;


import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
//class camera
public class Camera {
//data fields for camera
    Point3D _P0;
    Vector _vUp;
    Vector _vTo;
    Vector _vRight;
//full constructor
public Camera(Point3D p,Vector vup,Vector vto,Vector vright){
    _P0=p;
    _vUp=vup;
    _vTo=vto;
    _vRight=vright;
}
//copy constructor
public Camera(Camera c){
    this(new Point3D(c.getP0()),new Vector(c.getVup()),new Vector(c.getVto()),new Vector(c.getVright()));
}
//empty constructor - default values of our camera
public Camera(){
    _P0=new Point3D();
    _vUp=new Vector(new Point3D(new Coordinate(0),new Coordinate(0),new Coordinate(-1)));
    _vTo=new Vector(new Point3D(new Coordinate(0),new Coordinate(1),new Coordinate(0)));
    _vRight=new Vector(new Point3D(new Coordinate(1),new Coordinate(0),new Coordinate(0)));
}
//getters and setters
public Point3D getP0(){
    return _P0;
}
public Vector getVup(){
    return _vUp;
}
public Vector getVto(){
    return _vTo;
}
public Vector getVright(){
    return _vRight;
}
public void setPoint(Point3D p){
    _P0=p;
}
public void setVup(Vector v){
    _vUp=v;
}
public void setVto(Vector v){
    _vTo=v;
}
public void setVright(Vector v){
    _vRight=v;
}
//method to find the ray shot from the camera through the pixel
public Ray constructRayThroughPixel(int Nx, int Ny,double x, double y,
        double screenDistance, double screenWidth, double screenHeight){

//copy vTo, vRight and vUp so they don't change when scaled
Vector cpyVto=new Vector(getVto());
Vector cpyVup=new Vector(getVup());
Vector cpyVright=new Vector(getVright());
//obtain image centre Pc
    Point3D Pc=new Point3D(getP0().add(cpyVto.scalingV(screenDistance)));
   //obtain ratios of Rx and Ry
   double Rx=screenWidth/Nx;
   double Ry=screenHeight/Ny;
   //obtain vector to go right/left from point
   double scaleRight=((x-(Nx/2))*Rx)+(Rx/2);
   //obtain vector to go up/down from point
   double scaleUp=((y-(Ny/2))*Ry)+(Ry/2);
    //obtain point i,j from origin of screen
   Pc.add((cpyVright.scalingV(scaleRight)).subtract(cpyVup.scalingV(scaleUp)));
    //obtain vector i,j from the camera
   Vector pixel=new Vector(Pc.subtract(getP0()));
   //normalize vector
   pixel.normalize();
   //construct ray of pixel
   Ray r=new Ray(getP0(),pixel);
   return r;
}

