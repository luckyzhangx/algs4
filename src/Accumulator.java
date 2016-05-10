import edu.princeton.cs.algs4.StdDraw;

/**
 * Created by luckyzhang on 16-5-7.
 */
public class Accumulator {
    private double total;
    private int N;

    public Accumulator(int trials, double max) {
        StdDraw.setXscale(0, trials);
        StdDraw.setYscale(0, max);
        StdDraw.setPenRadius(.005);
    }

    public void addDataValue(double val){
        N++;
        total += val;
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(N, val);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(N, total/N);
    }

    public double mean(){
        return -0.0;
    }

    public String toString(){
        return "visual Accumulator";
    }
}
