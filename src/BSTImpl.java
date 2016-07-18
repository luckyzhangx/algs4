import java.util.Iterator;
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

    public boolean isEmpty() {
        return size(root) == 0;
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

    public void delMin() {
        if (isEmpty()) return;
        root = delMin(root);
    }

    private Node delMin(Node node) {
        if (node == null) return node;
        if (node.left == null) return node.right;
        node.left = delMin(node.left);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void delMax() {
        if (isEmpty()) return;
        root = delMax(root);
    }

    private Node delMax(Node node) {
        if (node.right == null) return node.left;
        node.right = delMax(node.right);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node node, int k) {
//        position from 1;
        if (node == null) return null;
        int t = size(node.left);
        if (t >= k) return select(node.left, k);
        else if (t + 1 < k) return select(node.right, k - t - 1);
        else return node;
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node node, Key key) {
        if (node == null) return 0;
        int t = key.compareTo(node.key);
        if (t == 0) return size(node.left) + 1;
        else if (t > 0) return size(node.left) + 1 + rank(node.right, key);
        else return rank(node.left, key);
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node node) {
        if (node == null) return node;
        if (node.left != null) return min(node.left);
        return node;
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node node) {
        if (node.right != null) return max(node.right);
        return node;
    }

    public Key floor(Key key) {
        return floor(root, key).key;
    }

    private Node floor(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node;
        if (cmp < 0) return floor(node.left, key);
        Node t = floor(node.right, key);
        if (t != null) return t;
        return node;
    }

    public Key ceiling(Key key) {
        return ceiling(root, key).key;
    }

    private Node ceiling(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node;
        if (cmp > 0) return ceiling(node.right, key);
        Node t = ceiling(node.left, key);
        if (t != null) return t;
        return node;
    }

    public void delKey(Key key) {
        root = delKey(root, key);
    }

    private Node delKey(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = delKey(node.left, key);
        if (cmp > 0) node.right = delKey(node.right, key);
        if (cmp == 0) {
            Node min = min(node.right);
            Node t = delMin(node.right);
            if (min != null) {
                min.left = node.left;
                min.right = t;
            }
            return min;
        }
        return node;
    }

    public int layer() {
        return layer(root);
    }

    private int layer(Node node) {
        if (node == null) return 0;
        int left = layer(node.left);
        int right = layer(node.right);
        return Math.max(left, right) + 1;
    }

    /*                                                         init position    gap gap2next
    *               - - - - - - - + - - - - - - -       8                               4
    *               - - - + - - - - - - - + - - -       4                       7      2
    *               - + - - - + - - - + - - - + -       2                       3      1
    *               + - + - + - + - + - + - + - +       1                       1
    *               1 2   4        8
    * */

    private class PrintNode {
        Node node;
        int position;
        int gap;

        PrintNode(Node node, int position, int gap) {
            this.node = node;
            this.position = position;
            this.gap = gap;
        }
    }

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(Node node) {
        if (node == null) return;
        printInOrder(node.left);
        System.out.printf(node.key + " ");
        printInOrder(node.right);
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("Binary Search Tree is empty");
            return;
        }
        int n = layer();
        int position = (int) Math.pow(2, n - 1);
        int offset = 0;
        int gap = position / 2;//gap to next layer.
        LinkedListQueueImpl<PrintNode> queue = new LinkedListQueueImpl<>();
        PrintNode printNode = new PrintNode(root, position, gap);
        queue.enqueue(printNode);
        while (!queue.isEmpty()) {
            PrintNode p = queue.dequeue();
            boolean isSameLine = (p.gap == gap);
            if (isSameLine) {
                int space = p.position - offset - 1;
                System.out.printf("%" + (space + 1) + "s", p.node.key);
                offset = p.position;
            } else {
                gap = gap / 2;
                System.out.printf("\n");
                int space = p.position - 1;
                System.out.printf("%" + (space + 1) + "s", p.node.key);
                offset = p.position;
            }
            if (p.node.left != null) queue.enqueue(new PrintNode(p.node.left, p.position - p.gap, p.gap / 2));
            if (p.node.right != null) queue.enqueue(new PrintNode(p.node.right, p.position + p.gap, p.gap / 2));
        }
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key low, Key high) {
        LinkedListQueueImpl<Key> queue = new LinkedListQueueImpl<>();
        keys(root, queue, low, high);
        return queue;
    }

    public void keys(Node node, LinkedListQueueImpl queue, Key low, Key high) {
        if(node == null) return;
        int cmplow = low.compareTo(node.key);
        int cmphigh = high.compareTo(node.key);
        if (cmplow < 0) keys(node.left, queue, low, high);
        if (cmplow <= 0 && cmphigh >= 0) queue.enqueue(node.key);
        if (cmphigh > 0) keys(node.right, queue, low, high);
    }

    public Iterable<Key> keys(int low, int high) {
        if (low <= 0 || high > size()) {
            System.out.println("illegal range! check the indices");
            return null;
        }
        LinkedListQueueImpl queue = new LinkedListQueueImpl<Key>();
        keys(root, queue, low, high);
        return queue;
    }

    private void keys(Node node, LinkedListQueueImpl queue, int low, int high) {
        if (node == null) return;
        int l = size(node.left);
        int r = size(node.right);
        if (l >= low) {
            if (l >= high) {
                keys(node.left, queue, low, high);
            } else
                keys(node.left, queue, low, l);
        }
        if (l + 1 >= low && l + 1 <= high) queue.enqueue(node.key);
        if (l + 1 < high) {
            if (low <= l + 1)
                keys(node.right, queue, 1, high - (l + 1));
            else
                keys(node.right, queue, low - (l + 1), high - (l + 1));
        }
    }

    public static void main(String[] args) {
        BSTImpl bst = new BSTImpl<String, Integer>();
//        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner("H D L B F J N A C E G I K M O exit");
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        while (scanner.hasNext()) {
            String token = scanner.next();
            if (token.equals("exit")) {
                System.out.println("exit");
                break;
            }
            bst.put(token, i++);
        }
//        System.out.println("layers: " + bst.layer());
//        bst.print();
        bst.printInOrder();
        while (scanner.hasNext()) {
            String command = scanner.next();
            if (command.equals("min")) {
                System.out.println("min: " + bst.min());
            } else if (command.equals("max")) {
                System.out.println("max: " + bst.max());
            } else if (command.equals("print")) {
                bst.print();
            } else if (command.equals("printInOrder")) {
                bst.printInOrder();
            } else if (command.equals("delMin")) {
                bst.delMin();
            } else if (command.equals("delMax")) {
                bst.delMax();
            } else if (command.equals("del")) {
                String token = scanner.next();
                bst.delKey(token);
            } else if (command.equals("keys")) {
                int low = scanner.nextInt();
                int high = scanner.nextInt();
                Iterable<String> iterable = bst.keys(low, high);
                for (String string: iterable)
                    System.out.println(string);
            } else {
                if (command.equals("exit")) {
                    break;
                } else {
                    System.out.println("no command for this.");
                }
            }
        }
        Iterable<String> keys = bst.keys("h", "n");
        System.out.println("print keys:");
        for (String string: keys)
            System.out.printf(string + " ");
//        System.out.println("min: " + bst.min());
//        System.out.println("max: " + bst.max());
//        String str;
//        if ((str = (String) bst.floor("J")) != null)
//            System.out.println("floor J: " + str);
//        if ((str = (String) bst.ceiling("J")) != null)
//            System.out.println("ceiling J: " + str);
//        if ((str = (String) bst.select(5)) != null)
//            System.out.println(5 + ": " + str);
//        System.out.println("rank I:" + bst.rank("I"));
    }
}