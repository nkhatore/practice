package datastructures;

/**
 * Created by nkhatore on 5/21/18.
 */
public class LinkedList<E> {

    public Node<E> sentinel;
    private int size;

    public static class Node<E> {

        public Node<E> prev;
        public Node<E> next;
        public E item;

        Node(E item) {
            this.prev = null;
            this.next = null;
            this.item = item;
        }
    }

    public LinkedList() {
        sentinel = new Node<>(null);
        size = 0;
    }

    public int size() {
        return this.size;
    }

    public void addFirst(E item) {
        Node<E> first = new Node<>(item);
        if (sentinel.next != null) {
            sentinel.next.prev = first;
            first.next = sentinel.next;
        }
        sentinel.next = first;
        this.size++;
    }

    public void addLast(E item) {
        Node<E> last = new Node<>(item);
        Node temp = sentinel;
        while (temp.next != null) {
            temp = temp.next;
        }
        last.prev = temp;
        temp.next = last;
        this.size++;
    }


    public E index(int index) {
        if (index < 0 || index >= this.size()) {
            return null;
        }
        Node<E> temp = sentinel.next;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.item;
    }

    public Node<E> nodeIndex(int index) {
        if (index < 0 || index >= this.size()) {
            return null;
        }
        Node<E> temp = sentinel.next;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    public void delete(Node<E> n) {
        if (n != null) {
            if (n.prev != null) {
                n.prev.next = n.next;
            }
            if (n.next != null) {
                n.next.prev = n.prev;
            }
            if (n.prev == null) {
                sentinel.next = n.next;
            }
            this.size--;
        }
    }

    public void deleteIndex(int index) {
        this.delete(this.nodeIndex(index));
    }

    public void print() {
        Node temp = sentinel.next;
        while (temp != null) {
            System.out.print(temp.item.toString());
            if (temp.next != null) {
                System.out.print(" -> ");
            }
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedList<Integer> test = new LinkedList<>();
        test.addFirst(5);
        for (int i = 1; i < 11; i++) {
            if (i < 5) {
                test.addFirst(5 - i);
            } else if (i > 5) {
                test.addLast(i);
            }
        }
        System.out.println(test.size());
        test.print();
        System.out.println(test.index(0));
        System.out.println(test.index(5));
        System.out.println(test.index(10));
        for (int i = 0; i < 5; i++) {
            test.deleteIndex(i);
        }
        test.print();
    }
}
