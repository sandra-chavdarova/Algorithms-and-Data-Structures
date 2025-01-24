/*
Given the root of a complete binary tree, return the number of the nodes in the tree.

According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Design an algorithm that runs in less than O(n) time complexity.

Link: https://leetcode.com/problems/count-complete-tree-nodes/description/?envType=problem-list-v2&envId=tree&difficulty=EASY
*/

package Leetcode;

public class CountCompleteTreeNodes {
    public int countNodes(TreeNode root) {
        if (root == null)
            return 0;
        if (root.right == null && root.left == null)
            return 1;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
