/**
 * Created by luckyzhang on 2016/7/16.
 */
public abstract class Sort {
    public abstract void sort(Comparable a[]);

    protected static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

    protected static void exchange(Comparable[] a, int i, int j){
        Comparable temp = a[i]; a[i] = a[j]; a[j] = temp;
    }

    public static boolean isSorted(Comparable[] a){
        for (int i = 0; i<a.length-1; i++)
            if (a[i].compareTo(a[i+1]) > 0) return false;
        return true;
    }
}
