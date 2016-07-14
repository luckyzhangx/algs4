import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by luckyzhang on 2016/7/14.
 */
public class LinkedListQueueImpl<Item> implements Iterable<Item>{
    private Node first;
    private Node last;
    private int size;

    public LinkedListQueueImpl(){
        size = 0;
    }

    private class Node{
        Item item;
        Node next;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public void enqueue(Item item){
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()) first = last;
        else oldLast.next = last;
        size++;
    }

    public Item dequeue(){
        if(isEmpty()) throw new NoSuchElementException("queue overflow");
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last=null;
        size--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        class QueueIterator implements Iterator<Item>{
            Node index = first;
            @Override
            public boolean hasNext() {
                return index != null;
            }

            @Override
            public Item next() {
                if(!hasNext()) throw new NoSuchElementException();
                Item item = index.item;
                index = index.next;
                return item;
            }

            @Override
            public void remove() {

            }
        }
        return new QueueIterator();
    }

    public static void main(String[] args) {
        LinkedListQueueImpl<String> q = new LinkedListQueueImpl();
        Scanner s = new Scanner(System.in);
        while (s.hasNext()) {
            String item = s.next();
            if (item.equals("exit")) break;
            if (!item.equals("-")) q.enqueue(item);
            else if(!q.isEmpty()) System.out.println(q.dequeue());
        }
        System.out.println("print left queue:");
        for (String str : q){
            System.out.println(str);
        }
    }
}
