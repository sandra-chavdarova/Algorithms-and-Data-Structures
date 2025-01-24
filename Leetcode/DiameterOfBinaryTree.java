//package Leetcode;
//
//// NE E RESENA
//
//public class DiameterOfBinaryTree {
//    public int left(TreeNode root) {
//        if (root == null)
//            return 0;
//        int counter = 0;
//        counter = 1 + diameterOfBinaryTree(root.left);
//        return counter - 1;
//    }
//
//    public int right(TreeNode root) {
//        if (root == null)
//            return 0;
//        int counter = 0;
//        counter = 1 + diameterOfBinaryTree(root.right);
//        return counter - 1;
//    }
//
//    public int diameterOfBinaryTree(TreeNode root) {
//        if (root == null)
//            return 0;
//        if (root.left == null && root.right == null)
//            return left(root) + right(root);
//    }
////        if (root.left == null)
////            return diameterOfBinaryTree(root.right);
////        if (root.right == null)
////            return diameterOfBinaryTree(root.left);
////        int left = diameterOfBinaryTree(root.left) + 1;
////        int right = diameterOfBinaryTree(root.right) + 1;
////        return Math.max(left - 1, right - 1);
////    }
//}
