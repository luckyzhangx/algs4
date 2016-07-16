import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

/**
 * Created by luckyzhang on 2016/7/16.
 */
public class QuickImpl extends Sort {
    @Override
    public void sort(Comparable[] a) {
        sort(a, 0, a.length-1);
    }

    private void sort(Comparable[] a, int low, int high) {
        if (low >= high) return;
        int j = partition(a, low, high);
        sort(a, low, j-1);
        sort(a, j+1, high);
    }

    private int partition(Comparable[] a, int low, int high) {
        int i = low;
        int j = high+1;
        while (i < j){
            while (less(a[++i], a[low])) if (i == high) break;
            while (less(a[low], a[--j])) if (j == low) break;
            if (i >= j) break;
            exchange(a, i, j);
        }
        exchange(a, low, j);
        return j;
    }

    public static void main(String[] args) throws IOException {
        Reader reader = new FileReader(args[0]);
        Scanner scanner = new Scanner(reader);
        String content = scanner.useDelimiter("\\A").next();
        String[] a = content.split("\\s");
        new QuickImpl().sort(a);
        System.out.println(a + " is sorted?" + Sort.isSorted(a));
        for (String str: a) System.out.println(str);
    }
}
