/**
 * Created by luckyzhang on 2016/7/14.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Try to implement a queue using a resizable array because the
 * resize part of queue is interesting than of stack and bag.
 */

public class ResizableArrayQueueImpl<Item> implements Iterable<Item> {

    private Item queue[];
    private int size;
    private int first;//refer to the first out item;
    private int last;//refer to the slot next item will stuff in;

    public ResizableArrayQueueImpl() {
        queue = (Item[]) new Object[2];
        size = 0;
        first = 0;
        last = 0;
    }

    public Item dequeue() {
        if(isEmpty()) throw new NoSuchElementException("Queue Overflow");
        Item item = queue[first];
        System.out.printf("deque:" + first);
        first++;
        if(first == queue.length) first = 0;
        size--;
        System.out.println("| size:" + size);
        return item;
    }

    public void enqueue(Item item) {
        if (size == queue.length) resize(2 * queue.length);
        System.out.printf("enque:"+last);
        queue[last++] = item;
        if(last == queue.length) last = 0;
        size++;
        System.out.println("| size:" + size);
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void resize(int size) {
        Item temp[] = (Item[]) new Object[size];
        for (int i = 0; i < this.size; i++) {
            temp[i] = queue[(first + i) % this.size];
        }
        queue = temp;
        first = 0;
        last = this.size;
        System.out.println("resize:" + size);
    }

    @Override
    public Iterator<Item> iterator() {
        class QueueIterator implements Iterator<Item>{
            int i = 0;
            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public Item next() {
                if(!hasNext()) throw new NoSuchElementException();
                Item item = queue[(first + i)%queue.length];
                i++;
                return item;
            }

            @Override
            public void remove() {

            }
        }
        return new QueueIterator();
    }

    public static void main(String[] args) {
        ResizableArrayQueueImpl<String> q = new ResizableArrayQueueImpl<>();
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