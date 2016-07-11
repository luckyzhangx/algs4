import edu.princeton.cs.algs4.*;

/**
 * Created by luckyzhang on 16-5-14.
 */
public class TestPath {
    public static void main(String[] args){
        Graph graph = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        BreadthFirstPaths bfp = new BreadthFirstPaths(graph, s);
        for (int v = 0; v < graph.V(); v++) {
            StdOut.print(s + "to" + v + ": ");
            if (bfp.hasPathTo(v))
                for (int x : bfp.pathTo(v))
                    if (x == s) StdOut.print(s);
                    else StdOut.print("-" + x);
            StdOut.println();
        }
    }
}
