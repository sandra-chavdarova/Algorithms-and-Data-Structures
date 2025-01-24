///*
//Given the root of a Binary Search Tree (BST), return the minimum absolute difference
//between the values of any two different nodes in the tree.
//
//Link: https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/?envType=problem-list-v2&envId=tree
//*/
//
//package Leetcode;
//
//public class MinimumAbsoluteDifference {
//    public int minLeft(TreeNode node, int value) {
//        if (node == null)
//            return 0;
//        if (node.left == null)
//            return 0;
//        if (node.right == null)
//            return 0;
//        int left = node.val - node.left.val;
//        int right = node.val - node.right.val;
//        int result = Math.min(Math.min(left, right), value);
//        return minLeft(node.left, result);
//    }
//
//    public int minRight(TreeNode node, int value) {
//        if (node == null)
//            return 0;
//        if (node.left == null)
//            return 0;
//        if (node.right == null)
//            return 0;
//        int left = node.val - node.left.val;
//        int right = node.val - node.right.val;
//        int result = Math.min(Math.min(left, right), value);
//        return minLeft(node.right, result);
//    }
//
//    public int getMinimumDifference(TreeNode root) {
//        return Math.min(minLeft(root, Integer.MAX_VALUE), minRight(root, Integer.MAX_VALUE));
//    }
//}
