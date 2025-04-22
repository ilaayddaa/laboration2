package lab2.ilayda;

public class BinarySearchTree<T extends Comparable<? super T>> implements SearchableDataStructure<T> {
    private BSTNode root;
    private int size;

    private class BSTNode {
        T item;
        BSTNode left, right;

        BSTNode(T item) {
            this.item = item;
        }
    }

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    @Override
    public void add(T item) {
        if (item == null) throw new IllegalArgumentException("Null items are not allowed");
        root = addRecursive(root, item);
    }

    private BSTNode addRecursive(BSTNode node, T item) {
        if (node == null) {
            size++;
            return new BSTNode(item);
        }
        int cmp = item.compareTo(node.item);
        if (cmp < 0) {
            node.left = addRecursive(node.left, item);
        } else if (cmp > 0) {
            node.right = addRecursive(node.right, item);
        } // om lika, ignorera dupliceringar
        return node;
    }

    @Override
    public boolean searchFor(T item) {
        return searchRecursive(root, item);
    }

    private boolean searchRecursive(BSTNode node, T item) {
        if (node == null) return false;
        int cmp = item.compareTo(node.item);
        if (cmp == 0) return true;
        else if (cmp < 0) return searchRecursive(node.left, item);
        else return searchRecursive(node.right, item);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean remove(T item) {
        if (item == null) return false;
        if (!searchFor(item)) return false;
        root = removeRecursive(root, item);
        size--;
        return true;
    }

    private BSTNode removeRecursive(BSTNode node, T item) {
        if (node == null) return null;

        int cmp = item.compareTo(node.item);
        if (cmp < 0) {
            node.left = removeRecursive(node.left, item);
        } else if (cmp > 0) {
            node.right = removeRecursive(node.right, item);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            BSTNode successor = findMin(node.right);
            node.item = successor.item;
            node.right = removeRecursive(node.right, successor.item);
        }
        return node;
    }

    private BSTNode findMin(BSTNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        inOrderTraversal(root, sb);
        return sb.toString().trim();
    }

    private void inOrderTraversal(BSTNode node, StringBuilder sb) {
        if (node == null) return;
        inOrderTraversal(node.left, sb);
        sb.append(node.item).append(" ");
        inOrderTraversal(node.right, sb);
    }
}
