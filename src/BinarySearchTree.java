/**
 * @author Jawaad Ahmar
 * this class implements an ordered dictionary using a binary search tree
 * each node of the tree will store a Pel object
 */

public class BinarySearchTree implements BinarySearchTreeADT{
    
    // instance variables for class BinarySearchTree
    private BNode root;
    private Pel smallestVal;

    // constructor initializes root of the tree
    public BinarySearchTree() {
        root = new BNode(null, null, null, null);
    }

    // accessor method returns Pel object storing the given key
    public Pel get(BNode r, Location key) {

        if (r.isLeaf()) {
            return null;
        } else {
            if (r.getData().getLocus().compareTo(key) == 0) {
                return r.getData();
            } else if ((r.getData().getLocus().compareTo(key) == 1)) {
                return get(r.leftChild(),key);
            } else if ((r.getData().getLocus().compareTo(key) == -1)) {
                return get(r.rightChild(),key);
            } 
        }
        return null;
    }

    // mutator method inserts a node into BST, throws an exception if a duplicate key is found
    public void put(BNode r, Pel newData) throws DuplicatedKeyException {

        if (r.isLeaf()) {
            BNode left = new BNode(null, null, null, r);
            BNode right = new BNode(null, null, null, r);
            r.setLeftChild(left);
            r.setRightChild(right);
            r.setContent(newData);
        } else {
            if ((r.getData().getLocus().compareTo(newData.getLocus()) == 1)) {
                put(r.leftChild(), newData);
            } else if (r.getData().getLocus().compareTo(newData.getLocus()) == 0) {
                throw new DuplicatedKeyException("this key already exists");
            } else if ((r.getData().getLocus().compareTo(newData.getLocus()) == -1)) {
                put(r.rightChild(), newData);
            }
        } 
    }

    // mutator method removes a node from BST, throws an exception if key does not exist
    public void remove(BNode r, Location key) throws InexistentKeyException {

        if (r.isLeaf()) {
            throw new InexistentKeyException("key does not exist");
        } else {
            if (r.getData().getLocus().compareTo(key) == 0) {
                if (r.leftChild().isLeaf() && r.rightChild().isLeaf()) {
                    r.setLeftChild(null);
                    r.setRightChild(null);
                    r.setContent(null);
                } else if ((r.leftChild().isLeaf()) & !r.rightChild().isLeaf()) {
                    BNode right = r.rightChild();
                    BNode superParent = r.parent();
                    if (!superParent.isLeaf()) {
                        right.setParent(superParent);
                        if (superParent.leftChild().getData().getLocus().compareTo(r.getData().getLocus()) == 0) {
                            superParent.setLeftChild(right);
                        } else {
                            superParent.setRightChild(right);
                        }
                    } else {
                        root = right;
                    }
                } else if (r.rightChild().isLeaf() & !r.leftChild().isLeaf()) {
                    BNode left = r.leftChild();
                    BNode superParent = r.parent();
                    if (!superParent.isLeaf()) {
                        left.setParent(superParent);
                        if (superParent.leftChild().getData().getLocus().compareTo(r.getData().getLocus()) == 0) {
                            superParent.setLeftChild(left);
                        } else {
                            superParent.setRightChild(left);
                        }
                    } else {
                        root = left;
                    }
                } else {
                    try {
                        smallestVal = smallest(r.rightChild());

                    } catch (Exception e) {
                        System.out.println("tree is empty");
                    } 
                    remove(r,smallestVal.getLocus());
                    r.setContent(smallestVal);
                }
            } else if ((r.getData().getLocus().compareTo(key) == 1)) {
                remove(r.leftChild(),key);
            } else if ((r.getData().getLocus().compareTo(key) == -1)) {
                remove(r.rightChild(),key);
            } 
        }    
    }
    
    // accessor method returns the smallest Pel object larger than the given key
    public Pel successor(BNode r, Location key) {

        if (r.isLeaf()) {
            BNode parent = r.parent();
            while (parent.getData().getLocus().compareTo(key) == -1) {
                parent = parent.parent();
                if (parent == null) {
                    return null;
                }
            }
            return parent.getData();
        } else {
            if (r.getData().getLocus().compareTo(key) == -1) {
                return successor(r.rightChild(), key);
            } else if (r.getData().getLocus().compareTo(key) == 0) {
                if (!r.rightChild().isLeaf()) {
                    try {
                        return smallest(r.rightChild());
                    } catch (Exception e) {
                        return null;
                    }
                } else {
                    BNode parent = r.parent();
                    while (parent.getData().getLocus().compareTo(key) == -1) {
                        parent = parent.parent();
                        if (parent == null) {
                            return null;
                        }
                    }
                    return parent.getData();
                }
            } else if (r.getData().getLocus().compareTo(key) == 1) {
                return successor(r.leftChild(), key);
            }
        }
        return null;
    }
    
    //  accessor method returns the largest Pel object smaller than the given key
    public Pel predecessor(BNode r, Location key) {

        if (r.isLeaf()) {
            BNode parent = r.parent(); 
            while (parent.getData().getLocus().compareTo(key) == 1) {
                parent = parent.parent();
                if (parent == null) {
                    return null;
                }
            }
            return parent.getData();
        } else {
            if (r.getData().getLocus().compareTo(key) == -1) {
                return predecessor(r.rightChild(), key);
            } else if (r.getData().getLocus().compareTo(key) == 0) {
                if (!r.leftChild().isLeaf()) {
                    try {
                        return largest(r.leftChild());
                    } catch (Exception e) {
                        return null;
                    }
                } else {
                    BNode parent = r.parent();

                    while (parent.getData().getLocus().compareTo(key) == 1) {
                        parent = parent.parent();
                        if (parent == null) {
                            return null;
                        }
                    }
                    return parent.getData();
                }
            } else if (r.getData().getLocus().compareTo(key) == 1) {
                return predecessor(r.leftChild(), key);
            }
        }
        return null;
    }

    // accessor method returns the smallest Pel value in BST
    public Pel smallest(BNode r) throws EmptyTreeException {

        if (r.isLeaf()) {
            throw new EmptyTreeException("tree is empty");
        } else {
            BNode node = r;
            while (!node.isLeaf()) {
                node = node.leftChild();
            }
            return node.parent().getData();
        }
    }
    
    // accessor method returns the largest Pel value in BST
    public Pel largest(BNode r) throws EmptyTreeException {

        if (r.isLeaf()) {
            throw new EmptyTreeException("tree is empty");
        } else {
            BNode node = r;
            while (!node.isLeaf()) {
                node = node.rightChild();
            }
            return node.parent().getData();
        }
    }
    
    // accessor method returns the root of the tree
    public BNode getRoot() {
        return root;
    }
}
