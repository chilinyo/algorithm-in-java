package cn.chilin.algorithm.linkedlist;

/**
 * 跟问题2类似，但要求分区之后要保持链表原本的顺序
 * 比如 9->0->4->5->1, pivot=3
 * 那么 0->1->9->4->5
 *
 */
public class Problem03_ListPartition2 {

    static class Node{

        int value;

        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    static Node solution(Node head, int pivot) {
        Node sHead = null; // 小的链表的头
        Node sTail = null; // 小的链表的尾
        Node eHead = null; // 相等的链表的头
        Node eTail = null; // 相等的链表的尾
        Node bHead = null; // 大的链表的头
        Node bTail = null; // 大的链表的尾
        Node next;  // 拆开原链表时保存下一个节点
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < pivot) {
                if (sHead == null) {
                    sHead = head;
                    sTail = head;
                } else {
                    sTail.next = head;
                    sTail = sTail.next;
                }
            } else if (head.value == pivot) {
                if (eHead == null) {
                    eHead = head;
                    eTail = head;
                } else {
                    eTail.next = head;
                    eTail = eTail.next;
                }
            } else {
                if (bHead == null) {
                    bHead = head;
                    bTail = head;
                } else {
                    bTail.next = head;
                    bTail = bTail.next;
                }
            }
            head = next;
        }
        // 将三个链表连起来
        if (sTail != null) {
            sTail.next = eHead;
            // 这里加个判断方便下面连接第三个链表
            eTail = eTail == null ? sTail : eTail;
        }
        if (eTail != null) {
            eTail.next = bHead;
        }
        return sHead != null ? sHead : eHead != null ? eHead : bHead;
    }

    public static void main(String[] args) {
        Node node1 = new Node(9);
        Node node2 = new Node(0);
        Node node3 = new Node(4);
        Node node4 = new Node(5);
        Node node5 = new Node(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        Node curr = solution(node1, 3);
        while (curr != null) {
            System.out.println(curr.value);
            curr = curr.next;
        }
    }
}
