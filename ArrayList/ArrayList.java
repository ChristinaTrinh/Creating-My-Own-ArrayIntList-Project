package CreatingMyOwnArrayIntListProject.ArrayList;

import java.util.Arrays;
import java.util.*;

public class ArrayList<E> {
    private E[] elementData;
    private int size;
    public static final int DEFAULT_CAPACITY = 100;

    // post: construct a new ArrayList with default capacity
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    // pre: capacity >= 0
    // post: construct a new ArrayList with given capacity
    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }
        elementData = (E[]) new Object[capacity];
        size = 0;
    }

    // post: construct a new ArrayIntList with an int array
    public ArrayList(E[] array) {
        elementData = Arrays.copyOf(array, array.length * 2);
        size = array.length;
    }

    // post: checks that the underlying array has the given capacity,
    // extends the capacity if it does not
    public void ensureCapacity(int capacity) {
        if (capacity > elementData.length) {
            int newCapacity = elementData.length * 2 + 1;
            if (capacity > newCapacity)
                newCapacity = capacity;
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    // post: throws an IndexOutOfBoundsException if the given index is not a legal
    // index of the current list
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index: " + index);
    }

    // post: return current number of element in list
    public int size() {
        return size;
    }

    // pre : 0 <= index < size()
    // post: return the elements at given index
    public E get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    // post: returns comma-separated, bracket version of list
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            String res = "[" + elementData[0];
            for (int i = 1; i < size; i++) {
                res += ", " + elementData[i];
            }
            return res + "]";
        }
    }

    // post: return the position of the first occurrence of given value
    // return -1 if not found
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    // post: return the position of the last occurrence of given value
    // return -1 if not found
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (elementData[i] == o)
                return i;
        }
        return -1;
    }

    // pre: size() < capacity
    // post: add value to the end of list
    public void add(E value) {
        ensureCapacity(size + 1);
        elementData[size] = value;
        size++;
    }

    // pre: size() < capacity && 0 <= index < size()
    // post: add value to the given index, move all subsequent value 1 value right
    public void add(int index, E value) {
        ensureCapacity(size + 1);
        checkIndex(index);
        for (int i = size; i >= index + 1; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = value;
        size++;
    }

    // pre: this.size + other.size < capacity
    // post: add all element in given list to this list
    public void addAll(int index, ArrayList<E> other) {
        ensureCapacity(size + other.size);
        for (int i = 0; i < other.size; i++) {
            add(index, other.elementData[i]);
            index++;
        }
    }

    // pre: 0 <= index < size()
    // post: remove value at given index, move all subsequent value 1 value left
    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size - 1] = null;
        size--;
    }

    public boolean remove(Object o) {
        if (this.indexOf(o) == -1)
            return false;
        else {
            this.remove(this.indexOf(o));
            return true;
        }
    }

    public void removeAll(ArrayList<E> list) {
        for (int i = 0; i < list.size(); i++) {
            while (this.indexOf(list.get(i)) != -1)
                this.remove(this.indexOf(list.get(i)));
        }
    }

    public void retainAll(ArrayList<E> list) {
        for (int i = 0; i < this.size(); i++) {
            if (!list.contains(elementData[i]))
                this.remove(i);
        }
    }

    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    // post: return whether or not list contain given value
    public boolean contains(E value) {
        return indexOf(value) >= 0;
    }

    public boolean containsAll(ArrayList<E> list) {
        for (int i = 0; i < list.size(); i++) {
            if (!this.contains(list.get(i)))
                return false;
        }
        return true;
    }

    // post: return whether or not list contain no element
    public boolean isEmpty() {
        return size == 0;
    }

    // pre: 0 <= index < size
    // post: change value of given index to given value
    public void set(int index, E value) {
        checkIndex(index);
        elementData[index] = value;
    }

    // post: clear all value in list
    public void clear() {
        for (int i = 0; i < size; i++)
            elementData[i] = null;
        size = 0;
    }

    public boolean equals(Object o) {
        return o.equals(elementData);
    }

    private class SubList extends ArrayList<E> implements RandomAccess {
        private final ArrayList<E> parent;
        private final int fromIndex;
        private final int toIndex;
        int size;

        public SubList(ArrayList<E> parent, int fromIndex, int toIndex) {
            this.parent = parent;
            this.fromIndex = fromIndex;
            this.size = toIndex - fromIndex;
            this.toIndex = toIndex;
        }

        public void set(int index, E e) {
            ArrayList.this.elementData[fromIndex + index] = e;
        }

        public E get(int index) {
            return ArrayList.this.elementData[fromIndex + index];
        }

        public int size() {
            return this.size;
        }

        public void add(int index, E e) {
            parent.add(fromIndex + index, e);
            this.size++;
        }

        public void remove(int index) {
            parent.remove(fromIndex + index);
            this.size--;
        }

        public String toString() {
            if (size == 0)
                return "[]";
            else {
                String res = "[" + parent.get(fromIndex);
                for (int i = fromIndex + 1; i < toIndex; i++) {
                    res += ", " + parent.get(i);
                }
                return res + "]";
            }
        }
    }

    public ArrayList<E> subList(int fromIndex, int toIndex) {
        return new SubList(this, fromIndex, toIndex);
    }
}
