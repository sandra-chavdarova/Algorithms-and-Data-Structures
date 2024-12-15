/*
Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.

A leaf is a node with no children.

Link: https://leetcode.com/problems/path-sum/description/?envType=problem-list-v2&envId=tree&difficulty=EASY
*/

package Leetcode;

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

public class PathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null)
            return targetSum - root.val == 0;
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }
}
