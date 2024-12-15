/*
Given a binary tree, determine if it is height-balanced.

A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.

Link: https://leetcode.com/problems/balanced-binary-tree/description/?envType=problem-list-v2&envId=tree&difficulty=EASY
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

public class BalancedBinaryTree {
    public int height(TreeNode node) {
        if (node == null)
            return 0;
        return Math.max(height(node.right) + 1, height(node.left) + 1);
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        if (Math.abs(height(root.left) - height(root.right)) <= 1)
            return isBalanced(root.left) && isBalanced(root.right);
        return false;
    }
}
