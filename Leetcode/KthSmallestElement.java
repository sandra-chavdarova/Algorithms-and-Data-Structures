/*
Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.

Link: https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/
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

public class KthSmallestElement {
    public List<Integer> inorderR(TreeNode node, List<Integer> ordered) {
        if (node != null) {
            inorderR(node.left, ordered);
            ordered.add(node.val);
            inorderR(node.right, ordered);
        }
        return ordered;
    }

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        list = inorderR(root, list);
        return list.get(k - 1);
    }
}
