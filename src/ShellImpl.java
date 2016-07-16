import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

/**
 * Created by luckyzhang on 2016/7/16.
 */
public class ShellImpl extends Sort {
    @Override
    public void sort(Comparable[] a) {

        int size = a.length;
        int H = 1;
        while (H < size/3) H = 3*H + 1;
        while (H > 0){
            for (int i = 0; i < size; i++) {
                Comparable temp = a[i];
                int j = i-H;
                for (; j>0 && less(temp, a[j]); j -= H)
                    a[j+H] = a[j];
                a[j+H] = temp;
            }
            H = H/3;
        }
    }
    public static void main(String[] args) throws IOException {
        Reader reader = new FileReader(args[0]);
        Scanner scanner = new Scanner(reader);
        String content = scanner.useDelimiter("\\Z").next();
        String[] a = content.split("\\s");
        new ShellImpl().sort(a);
        System.out.println(a + " is sorted?" + Sort.isSorted(a));
        for (String str: a) System.out.println(str);
    }
}
