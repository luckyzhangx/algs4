import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by luckyzhang on 16-5-7.
 */
public class TestVisualAccumulator {
    public static void main(String[] args){
        int T = Integer.parseInt(args[0]);
        Accumulator a = new Accumulator(T, 1.0);
        for (int t = 0; t < T; t++)
            a.addDataValue(StdRandom.random());
        StdOut.println(a);
    }
}
