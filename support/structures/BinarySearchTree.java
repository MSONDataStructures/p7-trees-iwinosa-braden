package structures;

public class BinarySearchTreeImpl<T extends Comparable<? super T>> implements BinarySearchTree<T> {

    // Node class representing a node in the Binary Search Tree
    private static class Node<T> {
        T value;
        Node<T> left, right;

        Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    // The root of the tree
    private Node<T> root;

    // Size of the tree
    private int size;

    public BinarySearchTreeImpl() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public BinarySearchTree<T> add(T toAdd) {
        if (toAdd == null) {
            throw new NullPointerException("Cannot add null value to the tree");
        }
        root = addRecursive(root, toAdd);
        size++;
        return this;
    }

    private Node<T> addRecursive(Node<T> current, T toAdd) {
        if (current == null) {
            return new Node<>(toAdd);
        }

        int comparison = toAdd.compareTo(current.value);
        if (comparison < 0) {
            current.left = addRecursive(current.left, toAdd);
        } else {
            current.right = addRecursive(current.right, toAdd);
        }

        return current;
    }

    @Override
    public boolean contains(T toFind) {
        if (toFind == null) {
            throw new NullPointerException("Cannot search for null value in the tree");
        }
        return containsRecursive(root, toFind);
    }

    private boolean containsRecursive(Node<T> current, T toFind) {
        if (current == null) {
            return false;
        }

        int comparison = toFind.compareTo(current.value);
        if (comparison == 0) {
            return true;
        } else if (comparison < 0) {
            return containsRecursive(current.left, toFind);
        } else {
            return containsRecursive(current.right, toFind);
        }
    }

    @Override
    public boolean remove(T toRemove) {
        if (toRemove == null) {
            throw new NullPointerException("Cannot remove null value from the tree");
        }

        boolean[] wasRemoved = new boolean[1];
        root = removeRecursive(root, toRemove, wasRemoved);
        if (wasRemoved[0]) {
            size--;
        }
        return wasRemoved[0];
    }

    private Node<T> removeRecursive(Node<T> current, T toRemove, boolean[] wasRemoved) {
        if (current == null) {
            return null;
        }

        int comparison = toRemove.compareTo(current.value);
        if (comparison < 0) {
            current.left = removeRecursive(current.left, toRemove, wasRemoved);
        } else if (comparison > 0) {
            current.right = removeRecursive(current.right, toRemove, wasRemoved);
        } else {
            // Node to remove found
            wasRemoved[0] = true;

            if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            }

            // Node has two children, find the smallest node in the right subtree
            current.value = getMinimumRecursive(current.right);
            current.right = removeRecursive(current.right, current.value, wasRemoved);
        }
        return current;
    }

    private T getMinimumRecursive(Node<T> current) {
        return current.left == null ? current.value : getMinimumRecursive(current.left);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T getMinimum() {
        if (isEmpty()) {
            throw new IllegalStateException("The tree is empty");
        }
        return getMinimumRecursive(root);
    }

    @Override
    public T getMaximum() {
        if (isEmpty()) {
            throw new IllegalStateException("The tree is empty");
        }
        return getMaximumRecursive(root);
    }

    private T getMaximumRecursive(Node<T> current) {
        return current.right == null ? current.value : getMaximumRecursive(current.right);
    }

    @Override
    public BinaryTreeNode<T> getRoot() {
        if (isEmpty()) {
            throw new IllegalStateException("The tree is empty");
        }
        return new BinaryTreeNode<>(root.value);  // Assuming BinaryTreeNode has a constructor for value
    }
}
