/**
 * Created by luckyzhang on 2016/7/19.
 */
public class RedBlackBSTImpl <Key extends Comparable, Value>{
    private Node root;

    private final static boolean RED = true;
    private final static boolean BLACK = false;

    private class Node {
        Key key;
        Value value;
        Node left, right;
        int N;
        boolean color;

        Node(Key key, Value value, int N, boolean color) {
            this.key = key;
            this.value = value;
            this.N = N;
            this.color = color;
        }
    }

    private int size(Node node) {
        if (node == null) return 0;
        return node.N;
    }

    private boolean isRed(Node node) {
        if (node == null) return false;
        return RED == node.color;
    }

    private Node rotateLeft(Node h) {
        Node t = h.right;
        h.right = t.left;
        t.left = h;
        t.color = h.color;
        h.color = RED;
        t.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return t;
    }

    private Node rotateRight(Node h) {
        Node t = h.left;
        h.left = t.right;
        t.right = h;
        t.color = h.color;
        h.color = RED;
        t.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return h;
    }

    private void flipColors(Node  h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) return new Node(key, value, 1, RED);
        int cmp = key.compareTo(node.key);
        if (cmp > 0) node.right = put(node.right, key, value);
        else if (cmp < 0) node.left = put(node.left, key, value);
        else node.value = value;

        if (isRed(node.right) && !isRed(node.right)) node = rotateLeft(node);
//        node.left is not possible to be null.
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) flipColors(node);
        return null;
    }
}
