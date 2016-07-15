import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Created by luckyzhang on 2016/7/16.
 */
public class SelectionImpl extends Sort {
    public  void sort (Comparable[] a) {
        int size = a.length;
        for (int i=0; i<size; i++) {
            int min = i;
            for (int j = i + 1; j < size; j++)
                if (less(a[j], a[min])) min = j;
            exchange(a, i, min);
        }
    }



    public static void main(String[] args) throws IOException {
        Reader reader = new FileReader(args[0]);
        Scanner scanner = new Scanner(reader);
        String content = scanner.useDelimiter("\\Z").next();
        String[] a = content.split("\\s");
        new SelectionImpl().sort(a);
        System.out.println(a + " is sorted?" + SelectionImpl.isSorted(a));
        for (String str: a) System.out.println(str);
    }
}