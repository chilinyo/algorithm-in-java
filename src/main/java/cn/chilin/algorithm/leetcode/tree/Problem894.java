package cn.chilin.algorithm.leetcode.tree;

import cn.chilin.structure.TreeNode;
import cn.chilin.util.TreeTraversalUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 满二叉树是一类二叉树，其中每个结点恰好有 0 或 2 个子结点。
 *
 * 返回包含 N 个结点的所有可能满二叉树的列表。 答案的每个元素都是一个可能树的根结点。
 *
 * 答案中每个树的每个结点都必须有 node.val=0。
 *
 * 你可以按任何顺序返回树的最终列表。
 *
 *
 *
 * @author yangzhilin
 * @date 2020-12-09
 */
public class Problem894 {

    public static void main(String[] args) {
        List<TreeNode> resultList = solution(7);
        resultList.forEach(TreeTraversalUtil::show);
    }

    private static List<TreeNode> solution(int N) {
        List<TreeNode> res = new ArrayList<>();
        if (N % 2 == 0) return res;

        if (N == 1) {
            res.add(new TreeNode(0));
            return res;
        }

        N --;

        for (int i = 1; i < N; i += 2) {
            List<TreeNode> leftList = solution(i);
            List<TreeNode> rightList = solution(N - i);
            for (TreeNode leftRoot : leftList) {
                for (TreeNode rightRoot : rightList) {
                    TreeNode root = new TreeNode(0);
                    root.left = leftRoot;
                    root.right = rightRoot;
                    res.add(root);
                }
            }
        }
        return res;

    }



}
