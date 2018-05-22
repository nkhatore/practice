package chapter2;

import datastructures.LinkedList;
import datastructures.LinkedList.Node;
import java.util.HashSet;
/**
 * Created by nkhatore on 5/21/18.
 */
public class LinkedLists {

    /** O(n) using HashSet **/
    private static <E> void removeDups(LinkedList<E> l) {
        HashSet<E> h = new HashSet<>();
        Node<E> temp = l.sentinel.next;
        while (temp != null) {
            if (h.contains(temp.item)) {
                l.delete(temp);
            } else {
                h.add(temp.item);
            }
            temp = temp.next;
        }
    }

    /** O(n) not utilizing .size() **/
    private static <E> E kthToLast(LinkedList<E> l, int k) {
        Node<E> ptr = l.sentinel.next;
        Node<E> ptr2 = l.nodeIndex(k);
        while (ptr2.next != null) {
            ptr = ptr.next;
            ptr2 = ptr2.next;
        }
        return ptr.item;
    }

    /** O(1) just given access to a node **/
    private static <E> void deleteMiddleNode(Node<E> n) {
        n.item = n.next.item;
        n.next = n.next.next;
        n.next.prev = n;
    }

    /** O(n) **/
    private static void partition(LinkedList<Integer> l, int x) {
        Node<Integer> lessThan = l.sentinel;
        Node<Integer> temp = l.sentinel.next;
        while (temp != null) {
            Node<Integer> temp2 = temp;
            temp = temp.next;
            if (temp2.item < x) {
                if (temp2.prev != null) {
                    temp2.prev.next = temp2.next;
                }
                if (temp2.next != null) {
                    temp2.next.prev = temp2.prev;
                }
                temp2.next = lessThan.next;
                lessThan.next.prev = temp2;
                lessThan.next = temp2;
                temp2.prev = lessThan;
                lessThan = temp2;
            }
        }
    }

    /** O(m + n) **/
    private static LinkedList<Integer> sumLists(LinkedList<Integer> l1, LinkedList<Integer> l2) {
        String num1 = "";
        String num2 = "";
        Node<Integer> temp1 = l1.sentinel.next;
        Node<Integer> temp2 = l2.sentinel.next;
        while (temp1 != null) {
            num1 = temp1.item.toString() + num1;
            temp1 = temp1.next;
        }
        while (temp2 != null) {
            num2 = temp2.item.toString() + num2;
            temp2 = temp2.next;
        }
        int sum = Integer.parseInt(num1) + Integer.parseInt(num2);
        String sumString = String.valueOf(sum);
        LinkedList<Integer> sumList = new LinkedList<>();
        for (char c : sumString.toCharArray()) {
            sumList.addFirst(Character.getNumericValue(c));
        }
        return sumList;
    }

    /** O(n) with no access to .nodeIndex() **/
    private static <E> Boolean isPalindrome(LinkedList<E> l) {
        Node<E> left = l.sentinel.next;
        Node<E> right = left;
        int size = 1;
        while (right.next != null) {
            right = right.next;
            size++;
        }
        if (size % 2 == 1) {
            while (left != right) {
                if (!left.item.equals(right.item)) {
                    return false;
                }
                left = left.next;
                right = right.prev;
            }
        } else {
            while (true) {
                if (!left.item.equals(right.item)) {
                    return false;
                }
                left = left.next;
                if (left == right) {
                    break;
                }
                right = right.prev;
            }
        }
        return true;
    }

    /** O(m + n) using HashSet **/
    private static <E> Node<E> intersect(LinkedList<E> l1, LinkedList<E> l2) {
        Node<E> temp1 = l1.sentinel.next;
        Node<E> temp2 = l2.sentinel.next;
        HashSet<Node<E>> h = new HashSet<>();
        while (temp1 != null) {
            h.add(temp1);
            temp1 = temp1.next;
        }
        while (temp2 != null) {
            if (h.contains(temp2)) {
                return temp2;
            }
            temp2 = temp2.next;
        }
        return null;
    }

    /** O(n) time, O(n) space using HashSet **/
    private static <E> Node<E> loopDetection(LinkedList<E> l) {
        HashSet<Node<E>> h = new HashSet<>();
        Node<E> temp = l.sentinel.next;
        while (!h.contains(temp)) {
            h.add(temp);
            temp = temp.next;
        }
        return temp;
    }

    /** O(n) time, O(1) space using pointers **/
    private static <E> Node<E> loopDetectionPtrs(LinkedList<E> l) {
        Node<E> slow = l.sentinel.next;
        Node<E> fast = l.sentinel.next;
        while (true) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        slow = l.sentinel.next;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    public static void main(String[] args) {
        LinkedList<Integer> test = new LinkedList<>();
        for (int i = 1; i < 11; i++) {
            test.addLast(i);
            test.addLast(i);
        }
        test.print();
        removeDups(test);
        test.print();
        System.out.println(kthToLast(test, 7));
        System.out.println(kthToLast(test, 2));
        System.out.println(kthToLast(test, 0));
        deleteMiddleNode(test.nodeIndex(2));
        test.print();
        LinkedList<Integer> test2 = new LinkedList<>();
        for (int i = 1; i < 11; i++) {
            test2.addLast(11 - i);
        }
        partition(test2, 5);
        test2.print();
        LinkedList<Integer> l1 = new LinkedList<>();
        l1.addFirst(1);
        l1.addFirst(2);
        l1.addFirst(3);
        LinkedList<Integer> l2 = new LinkedList<>();
        l2.addFirst(4);
        l2.addFirst(5);
        l2.addFirst(6);
        LinkedList<Integer> sum = sumLists(l1, l2);
        sum.print();
        LinkedList<Integer> test3 = new LinkedList<>();
        for (int i = 1; i < 4; i++) {
            test3.addLast(i);
        }
        for (int i = 1; i < 4; i++) {
            test3.addLast(4 - i);
        }
        System.out.println(isPalindrome(test3));
        test3.deleteIndex(2);
        System.out.println(isPalindrome(test3));
        test3.deleteIndex(0);
        System.out.println(isPalindrome(test3));
        LinkedList<Integer> l3 = new LinkedList<>();
        l3.addLast(1);
        l3.addLast(2);
        l3.addLast(3);
        LinkedList<Integer> l4 = new LinkedList<>();
        l4.addLast(2);
        l4.sentinel.next.next = l3.nodeIndex(1);
        System.out.println(intersect(l3, l4).item);
        test.nodeIndex(8).next = test.nodeIndex(5);
        System.out.println(loopDetection(test).item);
        System.out.println(loopDetectionPtrs(test).item);
    }
}
