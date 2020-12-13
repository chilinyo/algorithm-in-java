package cn.chilin.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * N 叉数前序/后序遍历
 *
 * @author yangzhilin
 * @date 2020-12-13
 */
public class Problem589 {

    static class Node {

        public int val;

        public List<Node> children = new ArrayList<>();

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", children=" + children +
                    '}';
        }
    }

    /**
     * 前序遍历（迭代）
     *
     * @param root
     * @return
     */
    public static List<Integer> preOrder(Node root) {
        LinkedList<Node> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }
        // addFirst
        stack.push(root);
        while (!stack.isEmpty()) {
            // removeFirst
            Node node = stack.pop();
            output.add(node.val);
            // 这里 reverse 会改变树原本的结构
            Collections.reverse(node.children);
            for (Node item : node.children) {
                stack.push(item);
            }
        }
        return output;
    }

    /**
     * 后续遍历（迭代）
     *
     * @param root
     * @return
     */
    public static List<Integer> postOrder(Node root) {
        LinkedList<Node> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            output.addFirst(node.val);

            for (Node item : node.children) {
                stack.push(item);
            }
        }
        return output;
    }


    public static void main(String[] args) {
        List<Node> nodeList1 = new ArrayList<>();
        List<Node> nodeList2 = new ArrayList<>();
        Node node1 = new Node(1, nodeList1);
        Node node2 = new Node(3, nodeList2);
        Node node3 = new Node(2);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        nodeList1.add(node2);
        nodeList1.add(node3);
        nodeList1.add(node4);
        nodeList2.add(node5);
        nodeList2.add(node6);

        List<Integer> preOrder = preOrder(node1);
        System.out.println(preOrder);

        List<Integer> postOrder = postOrder(node1);
        System.out.println(postOrder);


    }



}
