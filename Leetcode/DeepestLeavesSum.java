/*
Given the root of a binary tree, return the sum of values of its deepest leaves.

Link: https://leetcode.com/problems/deepest-leaves-sum/description/?envType=problem-list-v2&envId=tree
*/

package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class DeepestLeavesSum {
    public int deepestLeavesSum(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        int depth = depth(root);
        getSum(root, list, depth, 0);
        int sum = 0;
        for (int i = 0; i < list.size(); i++)
            sum += list.get(i);
        return sum;
    }

    public static void getSum(TreeNode node, List<Integer> result, int level, int currentLevel) {
        if (node == null)
            return;
        if (currentLevel == level)
            result.add(node.val);
        getSum(node.left, result, level, currentLevel + 1);
        getSum(node.right, result, level, currentLevel + 1);
    }

    public static int depth(TreeNode node) {
        if (node == null)
            return 0;
        return Math.max(depth(node.left), depth(node.right)) + 1;
    }
}
