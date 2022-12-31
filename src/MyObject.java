/**
 * @author Jawaad Ahmar
 * this class represents a fixed, user, computer, or target object
 */

public class MyObject implements MyObjectADT {

    // instance variables for class MyObject
    private BinarySearchTree BST;
    private int identifier;
    private int width;
    private int height;
    private String type;
    private Location locus;

    private Pel smallest;
    
    // constructor initializes instance variables
    public MyObject(int id, int width, int height, String type, Location pos) {
        BST = new BinarySearchTree();
        this.identifier = id;
        this.width = width;
        this.height = height;
        this.type = type;
        this.locus = pos;
    }

    // mutator method sets the type of the object, types of myobjects are "fixed", "user", "computer", "target"
    public void setType(String type) {
        this.type = type;
    }

    // accessor method returns the width of the object
    public int getWidth() {
        return width;
    }

    // accessor method returns the height of the object
    public int getHeight() {
        return height;
    }

    // accessor method returns the type of the object
    public String getType() {
        return type;
    }

    // accessor method returns the identifier of the object
    public int getId() {
        return identifier;
    }

    // accessor method returns the location of the object
    public Location getLocus() {
        return locus;
    }

    // mutator method sets the location of the object
    public void setLocus(Location value) {
        this.locus = value;
    }

    // mutator method adds Pel object to BST if it doesn't alreay exist
    public void addPel(Pel pix) throws DuplicatedKeyException {
        
        if ((BST.get(BST.getRoot(), pix.getLocus()) != null)) {
            throw new DuplicatedKeyException("this key already exists");
        }

        BST.put(BST.getRoot(), pix);
    }


    // method returns true if this MyObject intersects the one specified in the parameter, it returns false otherwise
    public boolean intersects(MyObject otherObject) {
        
        try {
            smallest = BST.smallest(BST.getRoot());
        } catch (Exception e) {
            return false;
        }
    
        for (Pel node = smallest; node != null; node = BST.successor(BST.getRoot(), node.getLocus())) {
            int x = node.getLocus().getx() + this.getLocus().getx() - otherObject.getLocus().getx();
            int y = node.getLocus().gety() + this.getLocus().gety() - otherObject.getLocus().gety();
            Location loc = new Location(x, y);
            if (otherObject.findPel(loc)) {
                return true;
            }
        }
        return false;
    }

    // helper method checks if a location exists within a BST
    private boolean findPel(Location p) {
        if (BST.get(BST.getRoot(), p) != null) {
            return true;
        }
        return false;
    }

    
}