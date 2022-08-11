package cn.chilin.algorithm.leetcode.tree;

import cn.chilin.structure.TreeNode;
import cn.chilin.util.TreeTraversalUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * @author yangzhilin
 * @date 2022/8/11
 *
 * 二叉树的序列化与反序列化
 *
 */
public class TreeCodec {

    public static void main(String[] args) {

        String tree = "1,2,#,4,#,#,3,#,#";
        TreeNode root = deserialize(tree);
        TreeTraversalUtil.show(root);

        String serialize = serialize(root);
        System.out.println(serialize);


    }

    final static String NULL = "#";
    final static String SEP = ",";

    public static String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return  sb.toString();
    }

    // 用前序遍历
    public static void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL).append(SEP);
            return;
        }
        sb.append(root.val).append(SEP);
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    public static TreeNode deserialize(String data) {
        String[] split = data.split(SEP);
        LinkedList<String> list = new LinkedList<>();
        for (String s : split) {
            list.addLast(s);
        }
        return deserialize(list);
    }
    public static TreeNode deserialize(LinkedList<String> list) {
        if (list.isEmpty()) return null;
        // 前序的第一个节点一定是根节点
        String node = list.removeFirst();
        if (node.equals(NULL)) return null;
        TreeNode root = new TreeNode(Integer.parseInt(node));
        root.left = deserialize(list);
        root.right = deserialize(list);
        return root;
    }

}
