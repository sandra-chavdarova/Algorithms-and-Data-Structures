/*
Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.

Link: https://leetcode.com/problems/validate-binary-search-tree/description/?envType=problem-list-v2&envId=tree&difficulty=MEDIUM
*/

package Leetcode;

import java.util.ArrayList;

public class ValidateBinarySearchTree {
    public ArrayList<Integer> inorder(TreeNode root, ArrayList<Integer> result) {
        if (root != null) {
            inorder(root.left, result);
            result.add(root.val);
            inorder(root.right, result);
        }
        return result;
    }

    public boolean isValidBST(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        result = inorder(root, result);
        for (int i = 0; i < result.size() - 1; i++) {
            if (result.get(i) >= result.get(i + 1))
                return false;
        }
        return true;
    }
}
