/**
 * @author Jawaad Ahmar
 * this class represents the location (x, y) of a pel
 */

public class Location {

    // instance variables for class Location
    private int x;
    private int y;
    
    // constructor for class Location
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // accessor method returns x value for location
    public int getx() {
        return x;
    }

    // accesssor method returns y value for location
    public int gety() {
        return y;
    }

    // compareTo method returns -1 if other location is greater, returns 1 if this location is greater
    public int compareTo(Location p) {
    
        if (this.gety() > p.gety()) {
            return 1;
        } else if ((this.gety() == p.gety()) && (this.getx() > p.getx())) {
            return 1;
        } else if ((this.gety() == p.gety()) && (this.getx() == p.getx())) {
            return 0;
        } else if (this.gety() < p.gety()) {
            return -1; 
        } else if ((this.gety() == p.gety()) && (this.getx() < p.getx())) {
            return -1;
        }
        return 2;
    }
}
