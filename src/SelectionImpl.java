import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Created by luckyzhang on 2016/7/16.
 */
public class SelectionImpl {
    public static void sort (Comparable[] a) {
        int size = a.length;
        for (int i=0; i<size; i++) {
            int min = i;
            for (int j = i + 1; j < size; j++)
                if (less(a[j], a[min])) min = j;
            exchange(a, i, min);
        }
    }

    private static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

    private static void exchange(Comparable[] a, int i, int j){
        Comparable temp = a[i]; a[i] = a[j]; a[j] = temp;
    }

    public static boolean isSorted(Comparable[] a){
        for (int i = 0; i<a.length-1; i++)
            if (a[i].compareTo(a[i+1]) > 0) return false;
        return true;
    }

    public static void main(String[] args) throws IOException {
        Reader reader = new FileReader(args[0]);
        Scanner scanner = new Scanner(reader);
        String content = scanner.useDelimiter("\\Z").next();
        String[] a = content.split("\\s");
        SelectionImpl.sort(a);
        System.out.println(a + " is sorted?" + SelectionImpl.isSorted(a));
        for (String str: a) System.out.println(str);
    }
}