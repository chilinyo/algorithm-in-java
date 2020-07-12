package cn.chilin.algorithm.linkedlist;

/**
 *
 * 链表按照某个值，分成左边小、中间相等、右边大的形式
 * 比如 9->0->4->5->1, pivot=3
 * 那么 0->1->9->5->4 或者 1->0->5->4->9 等等，总之满足左边比3小，中间等于3（这里没有中间部分），右边大于3
 *
 */
public class Problem02_ListPartition {

    static class Node {

        int value;

        Node next;

        Node(int data) {
            value = data;
        }
    }

    static Node solution(Node head, int pivot) {
        if (head == null) return head;
        // 1. 遍历出链表的长度
        int length = 0;
        Node cur = head;
        while (cur != null) {
            length ++;
            cur = cur.next;
        }
        // 2. 建立数组
        Node[] arr = new Node[length];
        cur = head;
        for (int i = 0; i < length; i ++) {
            arr[i] = cur;
            cur = cur.next;
        }
        // 3. 将数组分区
        arrPartition(arr, pivot);
        // 4. 重新连起来
        for (int i = 1; i < arr.length; i ++) {
            arr[i - 1].next = arr[i];
        }
        arr[length - 1].next = null;
        return arr[0];

    }

    static void arrPartition(Node[] arr, int pivot) {
        int small = -1;
        int index = 0;
        int big = arr.length;
        while (big != index) {
            if (arr[index].value < pivot) {
                // 左边部分
                swap(arr, ++small, index++);
            } else if (arr[index].value == pivot) {
                // 中间部分
                index++;
            } else {
                // 右边部分
                swap(arr, index, --big);
            }
        }
    }

    static void swap (Node[] arr, int a, int b) {
        Node tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
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
