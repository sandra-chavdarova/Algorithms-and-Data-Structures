/*
Given the root of a binary tree, return the number of nodes
where the value of the node is equal to the average of the values in its subtree.

Note:
The average of n elements is the sum of the n elements divided by n and rounded down to the nearest integer.
A subtree of root is a tree consisting of root and all of its descendants.

Link: https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree/description/?envType=problem-list-v2&envId=tree
*/

package Leetcode;

public class CountNodesEqualToAverageOfSubtree {
    public int averageOfSubtree(TreeNode root) {
        if (root == null)
            return 0;
        if (Math.floor(sumNodes(root) / count(root)) == root.val)
            return 1 + averageOfSubtree(root.left) + averageOfSubtree(root.right);
        return averageOfSubtree(root.left) + averageOfSubtree(root.right);
    }

    public static double sumNodes(TreeNode node) {
        if (node == null)
            return 0;
        return node.val + sumNodes(node.left) + sumNodes(node.right);
    }

    public static double count(TreeNode node) {
        if (node == null)
            return 0;
        return 1 + count(node.left) + count(node.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(sumNodes(root));
    }
}
