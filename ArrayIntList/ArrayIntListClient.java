package CreatingMyOwnArrayIntListProject.ArrayIntList;

public class ArrayIntListClient {
    public static void main(String[] args) {
        ArrayIntList list1 = new ArrayIntList();
        list1.add(11);
        list1.add(-7);
        list1.add(3);
        list1.add(42);
        list1.add(0);
        list1.add(14);
        ArrayIntList list2 = new ArrayIntList();
        list2.add(3);
        list2.add(42);
        list2.add(0);
        list1.add(3);
        System.out.println("Ex 2:");
        System.out.println(list1.indexOfSubList(list2));
        System.out.println("\n");

        ArrayIntList Ex4 = new ArrayIntList();
        Ex4.add(11);
        Ex4.add(-7);
        Ex4.add(3);
        Ex4.add(42);
        Ex4.add(0);
        Ex4.add(14);
        Ex4.add(56);
        System.out.println("Ex 4:");
        System.out.println(Ex4.toString());
        Ex4.reverse();
        System.out.println(Ex4.toString());
        System.out.println("\n");

        ArrayIntList Ex14 = new ArrayIntList();
        Ex14.add(4);
        Ex14.add(3);
        Ex14.add(2);
        Ex14.add(1);
        System.out.println("Ex 14:");
        System.out.println(Ex14.toString());
        Ex14.printInversions();
        System.out.println();
        System.out.println("\n");

        System.out.println("Ex 19:");
        System.out.println(Ex14.toString());
        Ex14.compress();
        System.out.println(Ex14.toString());
        System.out.println("\n");
    }
}
