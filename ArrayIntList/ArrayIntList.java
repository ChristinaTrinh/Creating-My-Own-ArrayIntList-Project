package CreatingMyOwnArrayIntListProject.ArrayIntList;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayIntList {
    private int[] elementData;
    private int size;
    public static final int DEFAULT_CAPACITY = 100;

    // post: construct a new ArrayIntList with default capacity
    public ArrayIntList() {
        this(DEFAULT_CAPACITY);
    }

    // pre: capacity >= 0
    // post: construct a new ArrayIntList with given capacity
    public ArrayIntList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }
        elementData = new int[capacity];
        size = 0;
    }

    // post: construct a new ArrayIntList with an int array
    public ArrayIntList(int[] array) {
        elementData = Arrays.copyOf(array, array.length * 2);
        size = array.length;
    }

    // post: return current number of element in list
    public int size() {
        return size;
    }

    // pre : 0 <= index < size()
    // post: return the elements at given index
    public int get(int index) {
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
    public int indexOf(int value) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == value) {
                return i;
            }
        }
        return -1;
    }

    // post: return the position of the last occurrence of given value
    // return -1 if not found
    public int lastIndexOf(int value) {
        for (int i = size - 1; i >= 0; i--) {
            if (elementData[i] == value)
                return i;
        }
        return -1;
    }

    // pre: size() < capacity
    // post: add value to the end of list
    public void add(int value) {
        checkCapacity(size + 1);
        elementData[size] = value;
        size++;
    }

    // pre: size() < capacity && 0 <= index < size()
    // post: add value to the given index, move all subsequent value 1 value right
    public void add(int index, int value) {
        checkCapacity(size + 1);
        checkIndex(index);
        for (int i = size; i >= index + 1; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = value;
        size++;
    }

    // pre: 0 <= index < size()
    // post: remove value at given index, move all subsequent value 1 value left
    public void remove(int index) {
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        size--;
    }

    // post: return whether or not list contain given value
    public boolean contains(int value) {
        return indexOf(value) >= 0;
    }

    // post: return whether or not list contain no element
    public boolean isEmpty() {
        return size == 0;
    }

    // pre: 0 <= index < size
    // post: change value of given index to given value
    public void set(int index, int value) {
        checkIndex(index);
        elementData[index] = value;
    }

    // post: clear all value in list
    public void clear() {
        size = 0;
    }

    // pre: this.size + other.size < capacity
    // post: add all element in given list to this list
    public void addAll(ArrayIntList other) {
        checkCapacity(size + other.size);
        for (int i = 0; i < other.size; i++) {
            add(other.elementData[i]);
        }
    }

    // post: checks that the underlying array has the given capacity,
    // throwing an IllegalStateException if it does not
    private void checkCapacity(int capacity) {
        if (capacity > elementData.length) {
            throw new IllegalStateException("exceeds list capacity");
        }
    }

    // post: throws an IndexOutOfBoundsException if the given index is not a legal
    // index of the current list
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index: " + index);
    }

    public int indexOfSubList(ArrayIntList that) {
        int count = 0;
        int index = -1;
        int indexOfThat = 0;
        for (int i = 0; i <= this.size; i++) {
            if (this.elementData[i] == that.elementData[indexOfThat] && index == -1) {
                index = i;
                count++;
                indexOfThat++;
            }

            else if (this.elementData[i] == that.elementData[indexOfThat]) {
                count++;
                indexOfThat++;
            }

            else if (this.elementData[i] != that.elementData[indexOfThat] && count != 0) {
                index = -1;
                count = 0;
                indexOfThat = 0;
            }
        }
        return index;
    }

    public void replaceAll(int target, int change) {
        if (size > 1) {
            for (int i = 0; i < size; i++) {
                if (this.elementData[i] == target)
                    this.elementData[i] = change;
            }
        }
    }

    public void reverse() {
        int[] arrayCopy = Arrays.copyOf(elementData, size);
        int index = 0;
        for (int i = size - 1; i >= 0; i--) {
            elementData[index] = arrayCopy[i];
            index++;
        }
    }

    public ArrayIntList runningTotal() {
        ArrayIntList list = new ArrayIntList(this.size);
        int sum = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= i; j++) {
                sum += elementData[j];
            }
            list.elementData[i] = sum;
            sum = 0;
        }
        return list;
    }

    public void fill(int value) {
        for (int i = 0; i < size; i++)
            this.set(i, value);
    }

    public boolean isPairwiseSorted() {
        if (size == 1 || size == 0)
            return true;

        else if (size % 2 == 0) {
            for (int i = 0; i < size - 1; i += 2) {
                if (elementData[i] > elementData[i + 1])
                    return false;
            }
        }

        else if (size % 2 != 0) {
            for (int i = 0; i < size - 2; i += 2) {
                if (elementData[i] > elementData[i + 1])
                    return false;
            }
        }
        return true;
    }

    public int count(int value) {
        int occurence = 0;
        for (int i = 0; i < size; i++) {
            if (elementData[i] == value)
                occurence++;
        }
        return occurence;
    }

    public int maxCount() {
        if (size == 0)
            return 0;

        int count1 = 0;
        int count2 = 1;
        int value = elementData[0];
        for (int i = 1; i < size; i++) {
            if (elementData[i] == value)
                count2++;
            else if (elementData[i] != value) {
                if (count1 < count2)
                    count1 = count2;
                count2 = 0;
                count2++;
                value = elementData[i];
            }
        }
        return count1;
    }

    public int longestSortedSequence() {
        int count = 1;
        int longest = 0;
        for (int i = 0; i < size - 1; i++) {
            if (elementData[i] <= elementData[i + 1])
                count++;
            else {
                if (longest < count)
                    longest = count;
                count = 1;
            }
        }
        return longest;
    }

    public int removeLast() {
        if (size == 0)
            throw new NoSuchElementException("The list is empty");
        int num = elementData[size - 1];
        this.remove(size - 1);
        return num;
    }

    public void removeFront(int n) {
        for (int i = 1; i <= n; i++)
            this.remove(0);
    }

    public void removeAll(int value) {
        while (indexOf(value) != -1)
            this.remove(this.indexOf(value));
    }

    public void printInversions() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (elementData[i] > elementData[j])
                    System.out.println("(" + elementData[i] + ", " + elementData[j] + ")");
            }
        }
    }

    public void mirror() {
        int n = size;
        for (int i = n - 1; i >= 0; i--)
            this.add(elementData[i]);
    }

    public void stutter() {
        for (int i = 0; i < size; i += 2)
            this.add(i + 1, elementData[i]);
    }

    public void stretch(int n) {
        for (int i = 0; i < size; i += n) {
            for (int j = 1; j < n; j++)
                this.add(i + 1, elementData[i]);
        }
    }

    public void doubleList() {
        int n = size;
        for (int i = 0; i < n; i--)
            this.add(elementData[i]);
    }

    public void compress() {
        for (int i = 1; i < size; i += 2)
            elementData[i] = elementData[i] + elementData[i - 1];

        if (size % 2 == 0) {
            for (int i = 0; i < size; i++)
                this.remove(i);
        }

        else if (size % 2 != 0) {
            for (int i = 0; i < size - 1; i++)
                this.remove(i);
        }
    }

    public void rotate() {
        this.add(elementData[0]);
        this.remove(0);
    }

    public void switchPairs() {
        int temp;
        for (int i = 1; i < size; i += 2) {
            temp = elementData[i];
            elementData[i] = elementData[i - 1];
            elementData[i - 1] = temp;
        }
    }
} // ArrayIntList
