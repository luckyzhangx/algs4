import java.util.Scanner;

/**
 * Created by luckyzhang on 2016/7/17.
 */
public class BSTImpl<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int N;

        public Node(Key key, Value value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        return node.N;
    }

    public Value get(Key key) {
        return get(key, root);
    }

    private Value get(Key key, Node node) {
        if (node == null) return null;
        if (node.key.compareTo(key) == 0) return node.value;
        if (node.key.compareTo(key) < 0) return get(key, node.right);
        else return get(key, node.left);
    }

    public void put(Key key, Value value) {
        root = put(key, value, root);
    }

    private Node put(Key key, Value value, Node node) {
        if (node == null) return new Node(key, value, 1);
        if (node.key.compareTo(key) == 0) node.value = value;
        else if (node.key.compareTo(key) < 0) node.right = put(key, value, node.right);
        else node.left = put(key, value, node.left);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    public int layer(){
        return layer(root);
    }

    private int layer(Node node) {
        if (node == null) return 0;
        int left = layer(node.left);
        int right = layer(node.right);
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        BSTImpl bst = new BSTImpl();
        Scanner scanner = new Scanner(System.in);
        int i=0;
        while (scanner.hasNext()) {
            String token = scanner.next();
            System.out.println(token);
            if (token.equals("exit")){
                System.out.println("break");
                break;}
            bst.put(token, i++);
        }
        System.out.println("layers: " + bst.layer());
    }
}
