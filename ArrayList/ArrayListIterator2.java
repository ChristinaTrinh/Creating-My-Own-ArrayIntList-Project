import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.*;

public class ArrayListIterator2<E> {
    private ArrayList<E> list;
    private int position;
    private boolean removeOK;
    private boolean keeptrack;

    public ArrayListIterator2(ArrayList<E> list) {
        this.list = list;
        position = 0;
        removeOK = false;
        keeptrack = false;
    }

    public boolean hasNext() {
        return position < list.size();
    }

    public E next() {
        if (!hasNext())
            throw new NoSuchElementException();
        E result = list.get(position);
        position++;
        removeOK = true;
        keeptrack = true;
        return result;
    }

    public E previous() {
        if (position - 1 < 0)
            throw new NoSuchElementException();
        E result = list.get(position - 1);
        position--;
        keeptrack = true;
        return result;
    }

    public boolean hasPrevious() {
        if (position - 1 < 0)
            return false;
        return true;
    }

    public int previousIndex() {
        return position - 1;
    }

    public int nextIndex() {
        return position + 1;
    }

    public void add(E e) {
        keeptrack = false;
        list.add(position, e);
    }

    public void remove() {
        if (!removeOK)
            throw new IllegalStateException();
        list.remove(position - 1);
        position--;
        removeOK = false;
        keeptrack = false;
    }

    public void set(E e) {
        if (!keeptrack)
            throw new IllegalStateException();
        list.set(position, e);
    }
}
