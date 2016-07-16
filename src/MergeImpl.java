import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

/**
 * Created by luckyzhang on 2016/7/16.
 */
public class MergeImpl extends Sort {

    private Comparable[] aux;
    @Override
    public void sort(Comparable[] a) {
        int size = a.length;
        aux = new Comparable[size];
        sort(a, 0, size-1);
    }

    public void sort(Comparable[] a, int low, int high){
        if (low>=high) return;
        int mid = (low + high)/2;
        sort(a, low, mid);
        sort(a, mid+1, high);
        merge(a, low, mid, high);
    }

    private void merge(Comparable[] a, int low, int mid, int high){
        int i = low;
        int j = mid+1;
        for (int k = low; k <= high; k++)
            aux[k] = a[k];

        for (int k = low; k <= high; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > high) a[k] = aux[i++];
            else if (less(aux[i], aux[j])) a[k] = aux[i++];
            else a[k] = aux[j++];
        }
    }

    public static void main(String[] args) throws IOException {
        Reader reader = new FileReader(args[0]);
        Scanner scanner = new Scanner(reader);
        String content = scanner.useDelimiter("\\Z").next();
        String[] a = content.split("\\s");
        new MergeImpl().sort(a);
        System.out.println(a + " is sorted?" + Sort.isSorted(a));
        for (String str: a) System.out.println(str);
    }
}
