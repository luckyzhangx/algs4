import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Created by luckyzhang on 2016/7/15.
 */
public class QuickFindUFImpl {
    private int id[];
    private int count;

    QuickFindUFImpl(int size){
        id = new int[size];
        count = size;
        for (int i=0; i < size; i++){
            id[i] = i;
        }
    }

    public int find(int p){
        return id[p];
    }

    public void Union(int p, int q){
        int pID = find(p);
        int qID = find(q);
        if (pID == qID) return;

        for (int i = 0; i < id.length; i++)
            if (id[i] == pID) id[i] = qID;
        count--;
    }

    public boolean isConnected(int p, int q){
        if (find(p) == find(q))
            return true;
        return false;
    }

    public int count(){ return count; }

    public static void main(String[] args) throws IOException{
        String filePath = args[0];
        Path path = FileSystems.getDefault().getPath(filePath);
        Scanner s = new Scanner(path);
        int size = s.nextInt();
        System.out.println("size:" + size);
        QuickFindUFImpl union = new QuickFindUFImpl(size);
        while(s.hasNext()){
            int p = s.nextInt();
            int q = s.nextInt();
            if (union.isConnected(p,q)) continue;
            union.Union(p,q);
            System.out.println(p + " " + q);
        }
        System.out.println(union.count() + "components");
    }
}