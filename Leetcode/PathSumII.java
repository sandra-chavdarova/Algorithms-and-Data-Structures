/*
Given the root of a binary tree and an integer targetSum,
return all root-to-leaf paths where the sum of the node values in the path equals targetSum.
Each path should be returned as a list of the node values, not node references.

A root-to-leaf path is a path starting from the root and ending at any leaf node.
A leaf is a node with no children.

Link: https://leetcode.com/problems/path-sum-ii/description/?envType=problem-list-v2&envId=tree
*/

package Leetcode;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentList = new ArrayList<>();
        list(root, targetSum, result, currentList, 0);
        return result;
    }

    public static void list(TreeNode node, int targetSum, List<List<Integer>> result, List<Integer> currentList, int sum) {
        if (node == null)
            return;
        // path can't end
        if ((sum + node.val != targetSum) || ((sum + node.val == targetSum) && (node.left != null || node.right != null))) {
            currentList.add(node.val);
            sum += node.val;
        } else if (sum + node.val == targetSum && node.left == null && node.right == null) {
            currentList.add(node.val);
            sum += node.val;
            List<Integer> newList = new ArrayList<>();
            for (int i = 0; i < currentList.size(); i++) {
                newList.add(i, currentList.get(i));
            }
            result.add(newList);
        }
        if (node.left != null) {
            list(node.left, targetSum, result, currentList, sum);
        }
        if (node.right != null) {
            list(node.right, targetSum, result, currentList, sum);
        }
        if (!currentList.isEmpty()) {
            sum = sum - node.val;
            currentList.removeLast();
        } else {
            return;
        }
    }
}
