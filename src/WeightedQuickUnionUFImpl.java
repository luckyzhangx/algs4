import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Created by luckyzhang on 2016/7/15.
 */
public class WeightedQuickUnionUFImpl {
    private int id[];
    private int size[];
    private int count;//number of components.

    WeightedQuickUnionUFImpl(int size) {
        id = new int[size];
        this.size = new int[size];
        count = size;
        for (int i = 0; i < size; i++) {
            id[i] = i;
            this.size[i] = 1;
        }
    }

    public int count() {
        return count;
    }

    public int find(int p) {
        while (p != id[p]) p = id[p];
        return p;
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        if (isConnected(p, q)) return;
        int rootP = find(p);
        int rootQ = find(q);
        if (size[rootP] > size[rootQ]) {
            id[rootQ] = id[rootP];
            size[rootP] = size[rootP] + size[rootQ];
        } else {
            id[rootP] = id[rootQ];
            size[rootQ] = size[rootP] + size[rootQ];
        }
        count--;
    }

    public static void main(String[] args) throws IOException {
        String filePath = args[0];
        Path path = FileSystems.getDefault().getPath(filePath);
        Scanner s = new Scanner(path);
        int size = s.nextInt();
        System.out.println("size:" + size);
        WeightedQuickUnionUFImpl union = new WeightedQuickUnionUFImpl(size);
        while (s.hasNext()) {
            int p = s.nextInt();
            int q = s.nextInt();
            if (union.isConnected(p, q)) continue;
            union.union(p, q);
            System.out.println(p + " " + q);
        }
        System.out.println(union.count() + "components");
    }
}
