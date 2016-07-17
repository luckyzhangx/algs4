import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

/**
 * Created by luckyzhang on 2016/7/17.
 */
public class HeapSortImpl extends Sort {
    public void sort(Comparable a[]) {
//        construct heap
        int size = a.length;
        for (int i = (size - 2) / 2; i >= 0; i--) {
            sink(a, i,a.length);
        }

//        get sort
        for (int i = a.length - 1; i > 0; i--) {
            exchange(a, 0, i);
            sink(a, 0, i);
        }
    }

    private void sink(Comparable a[], int i,int size) {
        int k = i;
        if (2 * k + 1 <= size - 1) {
            int j = 2 * k + 1;
            if (j + 1 <= size - 1 && less(a[j], a[j + 1])) j++;
            if (less(a[k], a[j])) {
                exchange(a, k, j);
                sink(a, j, size);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Reader reader = new FileReader(args[0]);
        Scanner scanner = new Scanner(reader);
        String content = scanner.useDelimiter("\\Z").next();
        String[] a = content.split("\\s");
        new HeapSortImpl().sort(a);
        System.out.println(a + " is sorted?" + Sort.isSorted(a));
        for (String str: a) System.out.println(str);
    }
}

