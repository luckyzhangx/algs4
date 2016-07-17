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

    /*                                                         init position    gap gap2next
    *               - - - - - - - + - - - - - - -       8                               4
    *               - - - + - - - - - - - + - - -       4                       7      2
    *               - + - - - + - - - + - - - + -       2                       3      1
    *               + - + - + - + - + - + - + - +       1                       1
    *               1 2   4        8
    * */

    private class PrintNode{
        Node node;
        int position;
        int gap;

        PrintNode(Node node, int position, int gap){
            this.node = node;
            this.position=position;
            this.gap=gap;
        }
    }
    public void print() {
        int n = layer();
        int position = (int)Math.pow(2, n-1);
        int offset = 0;
        int gap = position/2;//gap to next layer.
        LinkedListQueueImpl<PrintNode> queue = new LinkedListQueueImpl<>();
        PrintNode printNode = new PrintNode(root, position, gap);
        queue.enqueue(printNode);
        while (!queue.isEmpty()) {
            PrintNode p = queue.dequeue();
            boolean isSameLine = (p.gap == gap);
            if (isSameLine) {
                int space = p.position-offset-1;
                System.out.printf("%" + (space+1) + "s", p.node.key);
                offset = p.position;
            } else {
                gap = gap/2;
                System.out.printf("\n");
                int space = p.position-1;
                System.out.printf("%" + (space+1) + "s", p.node.key);
                offset = p.position;
            }
            if (p.node.left !=null ) queue.enqueue(new PrintNode(p.node.left, p.position-p.gap, p.gap/2));
            if (p.node.right !=null ) queue.enqueue(new PrintNode(p.node.right, p.position+p.gap, p.gap/2));
        }
    }

    public static void main(String[] args) {
        BSTImpl bst = new BSTImpl();
//        Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner("H D L B F J N A C E G I K M O exit");
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
        bst.print();
    }
}