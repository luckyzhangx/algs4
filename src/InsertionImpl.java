import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

/**
 * Created by luckyzhang on 2016/7/16.
 */
public class InsertionImpl extends Sort{
    @Override
    public void sort(Comparable[] a) {
        for (int i=0; i<a.length;i++) {
            for (int j=i; j>0 && less(a[j], a[j-1]); j--)
                exchange(a, j, j-1);
        }
    }
    public static void main(String[] args) throws IOException {
        Reader reader = new FileReader(args[0]);
        Scanner scanner = new Scanner(reader);
        String content = scanner.useDelimiter("\\Z").next();
        String[] a = content.split("\\s");
        new InsertionImpl().sort(a);
        System.out.println(a + " is sorted?" + Sort.isSorted(a));
        for (String str: a) System.out.println(str);
    }
}
