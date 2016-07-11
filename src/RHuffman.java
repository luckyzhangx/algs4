import com.sun.istack.internal.NotNull;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.MinPQ;
import java.util.ArrayList;

public class RHuffman {

    // alphabet size of extended ASCII
    private static final int R = 256;

    // Do not instantiate.
    private RHuffman() {
    }

    private static class Node implements Comparable<Node> {
        private final char ch;
        private final int freq;
        private final ArrayList<Node> nodes;

        Node(char ch, int freq, ArrayList<Node> nodes){
            this.ch = ch;
            this.freq = freq;
            this.nodes = nodes;
        }

    // is the node a leaf node?
    private boolean isLeaf() {
        return nodes == null || nodes.size() == 0;
    }

    // compare, based on frequency
    @NotNull
    public int compareTo(Node that) {
        return this.freq - that.freq;
    }

}

    /**
     * Reads a sequence of 8-bit bytes from standard input; compresses them
     * using Huffman codes with an 8-bit alphabet; and writes the results
     * to standard output.
     */
    public static void compress(int N, String s) {

        char[] input = s.toCharArray();

        // tabulate frequency counts
        int[] freq = new int[R];
        for (int i = 0; i < input.length; i++)
            freq[input[i]]++;

        // build Huffman trie
        Node root = buildTrie(freq, N);

        // build code table
        String[] st = new String[R];
        buildCode(st, root, "");

        // print trie for decoder
        printTrie(st);

        System.out.println("Result:");
        for (int i = 0; i < input.length; i++) {
            String code = st[input[i]];
            System.out.println(code);
        }
    }

    // build the Huffman trie given frequencies
    private static Node buildTrie(int[] freq, int n) {
        // initialze priority queue with singleton trees
        MinPQ<Node> pq = new MinPQ<>();
        for (char i = 0; i < R; i++)
            if (freq[i] > 0)
                pq.insert(new Node(i, freq[i], null));

        while (pq.size() > 1) {
            ArrayList<Node> nodes = new ArrayList<>();
            int newfreq = 0;
            for (int i = 0; i < n && pq.size() > 0; i++){
                Node e = pq.delMin();
                nodes.add(e);
                newfreq += e.freq;
            }
            Node parent = new Node('\0', newfreq, nodes);
            pq.insert(parent);
        }
        return pq.delMin();
    }

    // write bitstring-encoded trie to standard output
    private static void printTrie(String[] st){
        System.out.println("Code:");
        class CharMap implements Comparable<CharMap>{
            char c;
            int code;

            CharMap(char c, int code){
                this.c = c;
                this.code = code;
            }

            @Override
            @NotNull
            public int compareTo(CharMap charMap) {
                return this.code - charMap.code;
            }
        }

        MinPQ<CharMap> pq = new MinPQ<>();
        for (int i = 0; i < R; i++ )
            if (st[i] != null)
                pq.insert(new CharMap((char)i, Integer.decode(st[i])));

        while (!pq.isEmpty()){
            CharMap charMap = pq.delMin();
            System.out.println(charMap.c + "\t" + charMap.code);
        }
    }

    // make a lookup table from symbols and their encodings
    private static void buildCode(String[] st, Node x, String s) {
        if (!x.isLeaf()) {
            int i = 0;
            for(Node e: x.nodes){
                buildCode(st, e, s + i);
                i++;
            }
        }
        else {
            st[x.ch] = s;
        }
    }

    public static void main(String[] args) {
        int N = Integer.decode(args[0]);
        compress(N, args[1]);
    }
}