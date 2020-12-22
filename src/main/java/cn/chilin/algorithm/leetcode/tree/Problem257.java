package cn.chilin.algorithm.leetcode.tree;

import cn.chilin.structure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yangzhilin
 * @date 12/22/20
 */
public class Problem257 {

    static List<String> res = new ArrayList<>();

    public static List<String> binaryTreePaths(TreeNode root) {

        LinkedList<String> trace = new LinkedList<>();
        traceBack(root, trace);
        return res;

    }

    static void traceBack(TreeNode root, LinkedList<String> trace) {
        // 终止条件
        if (root == null) return;

        trace.add("" + root.val);
        // 叶子节点才把路径加到结果集
        if (root.left == null && root.right == null) {
            res.add(String.join("->", trace));
        }
        // 选择
        traceBack(root.left, trace);
        traceBack(root.right, trace);
        // 回溯
        trace.removeLast();

    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.right = node4;
        System.out.println(binaryTreePaths(node1));

    }

}
