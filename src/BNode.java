/**
 * @author Jawaad Ahmar
 * this class represents the nodes of the binary search tree
 * each node will store an object of the class Pel
 */

public class BNode {

    // instance variables for class BNode
    private Pel value;
    private BNode leftChild;
    private BNode rightChild;
    private BNode parent;

    // constructor for class BNode
    public BNode(Pel value, BNode left, BNode right, BNode parent) {
        this.value = value;
        this.leftChild = left;
        this.rightChild = right; 
        this.parent = parent;
    }

    // accessor method returns parent of a node
    public BNode parent() {
        return parent;
    }

    // mutator method sets parent of a node
    public void setParent(BNode newParent) {
        parent = newParent;
    }

    // mutator method sets left child of a node
    public void setLeftChild(BNode p) {
        leftChild = p;
    }

    // mutator method sets right child of a node
    public void setRightChild(BNode p) {
        rightChild = p;
    }

    // mutator method sets the content of a node
    public void setContent(Pel value) {
        this.value = value;
    }

    // boolean method checks if a node is a leaf
    public boolean isLeaf() {
        if (this.getData() == null) {
            return true;
        }
        return false;
    }

    // accessor method returns data value of a node
    public Pel getData() {
        return value;
    }

    // accessor method returns left child of a node 
    public BNode leftChild() {
        return leftChild;
    }

    // accessor method returns right child of a node 
    public BNode rightChild() {
        return rightChild;
    }
}


