package cn.chilin.algorithm.linkedlist;


import java.util.Stack;

/**
 *
 * 判断一个链表是否是回文：
 * 1->2->3->2->1  true
 * 1->1->2->3     false
 * 123->1->123    true
 */
public class Problem01_IsPalindrome {


    static class Node{

        int value;

        Node next;

        public Node(int value) {
            this.value = value;
        }
    }


    /**
     * 方法一：将整个链表放进一个栈里面，再pop出来逐个比较
     *
     * @param head
     * @return
     */
    public static boolean solution1(Node head) {
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (head != null) {
            if (stack.pop().value != head.value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }


    /**
     * 方法二：先用快慢指针找到中点，将链表后半部分放入栈，弹出来跟前半部分比较
     *
     * @param head
     * @return
     */
    public static boolean solution2(Node head) {
        if (head == null || head.next == null) return true;
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast =fast.next.next;
        }
        // 指向右半区第一个 Node
        slow = slow.next;
        Stack<Node> stack = new Stack<>();
        while(slow != null) {
            stack.push(slow);
            slow = slow.next;
        }

        while (!stack.isEmpty()) {
            if (stack.pop().value != head.value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 不使用栈，找到链表的中点，然后反转右半区的链表，遍历比较左右半区，完了把链表恢复
     *
     * @param head
     * @return
     */
    public static boolean solution3(Node head) {
        if (head == null || head.next == null) return true;
        Node n1 = head;
        Node n2 = head;
        while (n2.next != null && n2.next.next !=null) { // 此时 n2 是快指针，n1 是慢指针，最后 n1->mid
            n1 = n1.next;
            n2 = n2.next.next;
        }
        n2 = n1.next;   // n2指向右半区  1->2->3->2->1
        n1.next = null; // mid -> null
        Node n3;
        // 反转右半区
        while (n2 != null) {
            n3 = n2.next; // 因为要反转，所以 n3 先保存 n2 的下一个节点
            n2.next = n1; // n2 作为 n1.next，将它的 next 指向 n1 实现反转
            n1 = n2;      // n1 向前移动
            n2 = n3;      // n2 向前移动
        }
        // 最后 n2->null, n3->null, n1->最后一个Node
        // 保存最后一个节点，用来还原链表
        n3 = n1;
        n2 = head;
        boolean result = true;
        // 遍历比较 n2->左半链表, n1->右边链表
        while (n1 != null && n2 != null) {
            if (n1.value != n2.value) {
                result = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }

        n1 = n3.next;
        n3.next = null;
        while (n1 != null) {
            n2 = n1.next;  // 保存下一个节点
            n1.next = n3;  // 反转指向
            n3 = n1;       // 移动
            n1 = n2;       // 移动
        }

        return result;
    }


    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(2);
        Node node5 = new Node(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        System.out.println(solution1(node1));
        System.out.println(solution2(node1));
        System.out.println(solution3(node1));
    }


}
