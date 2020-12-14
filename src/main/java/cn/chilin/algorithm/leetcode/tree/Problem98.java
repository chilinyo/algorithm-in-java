package cn.chilin.algorithm.leetcode.tree;

import cn.chilin.structure.TreeNode;
import cn.chilin.util.TreeTraversalUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
 *
 * 例如: n = 3
 * 可能组合:
 *       1
 *         \
 *           2
 *            \
 *             3
 *
 *       1
 *         \
 *           3
 *          /
 *         2
 *
 *    2
 *   / \
 *  1   3
 *
 *       3
 *     /
 *   1
 *    \
 *     2
 *
 *       3
 *     /
 *   2
 *  /
 * 1
 *
 * @author yangzhilin
 * @date 2020-12-14
 */
public class Problem98 {

    public static List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<>();
        }
        return generateTrees(1, n);
    }

    // 递归
    public static List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }

        for (int i = start; i <= end; i++) {
            // [1 ~ i-1] 节点组合
            List<TreeNode> leftTrees = generateTrees(start, i - 1);
            // [i+1 ~ n] 节点组合
            List<TreeNode> rightTrees = generateTrees(i + 1, end);

            // 遍历组合
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode currTree = new TreeNode(i);
                    currTree.left = left;
                    currTree.right = right;
                    allTrees.add(currTree);
                }
            }
        }
        return allTrees;
    }



    public static void main(String[] args) {
        List<TreeNode> treeNodes = generateTrees(3);
        treeNodes.forEach(TreeTraversalUtil::show);
    }


}
