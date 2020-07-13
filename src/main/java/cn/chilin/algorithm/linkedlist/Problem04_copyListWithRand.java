package cn.chilin.algorithm.linkedlist;


import java.util.HashMap;
import java.util.Map;

/**
 * 复制含有随机指针的链表
 *
 */
public class Problem04_copyListWithRand {

    static class Node {

        int value;

        Node next;

        Node rand; // 随机指针

        public Node(int value) {
            this.value = value;
        }
    }

    // 思路1：使用哈希表
    static Node solution1(Node head){
        Map<Node, Node> nodeMap = new HashMap<>();
        Node curr = head;
        while (curr != null) {
            nodeMap.put(curr, new Node(curr.value));
            curr = curr.next;
        }
        for (Map.Entry<Node, Node> entry : nodeMap.entrySet()) {
            entry.getValue().rand = entry.getKey().rand;
            entry.getValue().next = entry.getKey().next;
        }
        return nodeMap.get(head);
    }

    // 思路2：直接将copy的节点接在当前节点后面，通过相互关系找到rand节点
    static Node solution2(Node head) {
        Node curr = head;
        Node next;
        while (curr != null) {
            // 1->2->3 变成 1->1'->2->2'->3->3'
            next = curr.next;
            curr.next = new Node(curr.value);
            curr.next.next = next;
            curr = next;
        }
        curr = head;
        Node copy;
        // 这里复制rand节点
        while (curr != null) {
           next = curr.next.next;
           copy = curr.next;
           // 注意这里如果 1r->3 那么 1'r->3'
           copy.rand = curr.rand != null ? curr.rand.next : null;
           curr = next;
        }
        // 先保存一下结果
        Node res = head.next;
        curr = head;
        // 拆分
        while (curr != null) {
            next = curr.next.next;
            copy = curr.next;
            curr.next = next;
            // 这里注意越界
            copy.next = next != null ? next.next : null;
            curr = next;
        }

        return res;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.next = node2;
        node1.rand = node3;
        node2.next = node3;
        node2.rand = node1;

//        Node copy = solution1(node1);
        Node copy = solution2(node1);
        int i = 1;
        while (copy != null) {
            System.out.print("Node" + i);
            System.out.println(" next: " + (copy.next == null ? "null" : copy.next.value) +
                               " rand: " + (copy.rand == null ? "null" : copy.rand.value));
            copy = copy.next;
            i++;
        }

    }


}
