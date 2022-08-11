package cn.chilin.algorithm.linkedlist;

import cn.chilin.structure.ListNode;

import java.util.PriorityQueue;

/**
 * @author yangzhilin
 * @date 2022/3/10
 */
public class Problem04_MergeKLists {

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);
        for (ListNode listNode : lists) {
            if (listNode != null) {
                queue.add(listNode);
            }
        }
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            if (node.next != null) {
                queue.add(node.next);
            }
            p.next = node;
            p = p.next;
        }

        return dummy.next;

    }

}
