/**
 * Created by luckyzhang on 2016/7/19.
 */
public class RedBlackBSTImpl <Key extends Comparable, Value>{
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

    private boolean isRed(Node node) {
        if (node == null) return false;
        return RED == node.color;
    }
}
