/*
Given the root of a binary tree,
return the level order traversal of its nodes' values. (i.e., from left to right, level by level).

Link: https://leetcode.com/problems/binary-tree-level-order-traversal/description/?envType=problem-list-v2&envId=tree
*/

package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        int depth = depth(root) + 1;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < depth; i++) {
            List<Integer> list = new ArrayList<>();
            levelValues(root, list, i, 0);
            if (!list.isEmpty())
                result.add(list);
        }
        return result;
    }

    public static List<Integer> levelValues(TreeNode node, List<Integer> result, int level, int currentLevel) {
        if (node == null) {
            return result;
        }
        if (level == currentLevel) {
            result.add(node.val);
        }
        levelValues(node.left, result, level, currentLevel + 1);
        levelValues(node.right, result, level, currentLevel + 1);
        return result;
    }

    public static int depth(TreeNode node) {
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return 0;
        return Math.max(depth(node.left), depth(node.right)) + 1;
    }
}
