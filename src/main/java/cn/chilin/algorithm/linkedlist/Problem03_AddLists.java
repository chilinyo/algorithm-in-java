package cn.chilin.algorithm.linkedlist;

import java.util.Stack;

/**
 *
 * 两个链表相加 1->9->7 + 6->6->6 = 8->6->3
 */
public class Problem03_AddLists {

    static class Node {

        int value;

        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    static Node stackSolution(Node head1, Node head2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (head1 != null) {
            stack1.push(head1.value);
            head1 = head1.next;
        }
        while (head2 != null) {
            stack2.push(head2.value);
            head2 = head2.next;
        }
        int n1;
        int n2;
        int sum;
        int ca = 0;
        Node node = null;
        Node pre;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            n1 = stack1.isEmpty() ? 0 : stack1.pop();
            n2 = stack2.isEmpty() ? 0 : stack2.pop();
            sum = n1 + n2 + ca;
            pre = node;
            node = new Node(sum % 10);
            node.next = pre;
            ca = sum / 10;
        }
        // 最后检查是否有进1位
        if (ca == 1) {
            pre = node;
            node = new Node(1);
            node.next = pre;
        }
        return node;
    }

    // 使用反转链表
    static Node reverseSolution(Node head1, Node head2) {
        head1 = reverseList(head1);
        head2 = reverseList(head2);
        int n1;
        int n2;
        int sum;
        int ca = 0;
        Node c1 = head1;
        Node c2 = head2;
        Node pre;
        Node node = null;
        while (c1 != null || c2 != null) {
            n1 = c1 != null ? c1.value : 0;
            n2 = c2 != null ? c2.value : 0;
            sum = n1 + n2 + ca;
            // 像方法一一样反着接
            pre = node;
            node = new Node(sum % 10);
            node.next = pre;
            ca = sum / 10;
            c1 = c1 != null ? c1.next : null;
            c2 = c2 != null ? c2.next : null;
        }
        if (ca == 1) {
            pre = node;
            node = new Node(1);
            node.next = pre;
        }
        reverseList(head1);
        reverseList(head2);
        return node;
    }

    static Node reverseList(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        // head 最后是 null
        return pre;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        node1.next = new Node(9);
        node1.next.next = new Node(7);
        Node node2 = new Node(6);
        node2.next = new Node(6);
        node2.next.next = new Node(6);
//        Node res = stackSolution(node1, node2);
        Node res = reverseSolution(node1, node2);
        while (res != null) {
            System.out.println(res.value);
            res = res.next;
        }

    }

}
