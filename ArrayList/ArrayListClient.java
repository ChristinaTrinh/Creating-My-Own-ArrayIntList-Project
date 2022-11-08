public class ArrayListClient {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(list);
        ArrayList sub = list.subList(1, 4);
        System.out.println(sub);
        sub.set(0, 7);
        System.out.println(sub);
        System.out.println(list);
    }
}