/**
 * @author Jawaad Ahmar
 * this class represents the data items to be stored in the nodes of the binary search tree
 */

public class Pel {

    // instance variables for class Pel
    private Location locus;
    private int color;


    // constructor method for class Pel
    public Pel(Location p, int color) {
        this.locus = p;
        this.color = color;
    }

    // accessor method returns the location of Pel
    public Location getLocus() {
        return locus;
    }

    // accessor method returns for color value of pel
    public int getColor() {
        return color;
    }
}
