/*
A binary tree is named Even-Odd if it meets the following conditions:

The root of the binary tree is at level index 0, its children are at level index 1, their children are at level index 2, etc.
For every even-indexed level, all nodes at the level have odd integer values in strictly increasing order (from left to right).
For every odd-indexed level, all nodes at the level have even integer values in strictly decreasing order (from left to right).
Given the root of a binary tree, return true if the binary tree is Even-Odd, otherwise return false.

Link: https://leetcode.com/problems/even-odd-tree/submissions/1510466355/?envType=problem-list-v2&envId=tree
*/

package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class EvenOddTree {
    public static boolean isEvenOddTree(TreeNode root) {
        int n = depth(root);
        for (int i = 0; i < n; i++) {
            List<Integer> result = new ArrayList<>();
            getRow(root, i, 0, result);
            System.out.println(result.toString());
            for (int j = 0; j < result.size(); j++) {
                if (i % 2 == 0 && result.get(j) % 2 == 0)
                    return false;
                if (i % 2 == 1 && result.get(j) % 2 == 1)
                    return false;
            }
            if (i % 2 == 0 && !isIncreasing(result) && result.size() != 1)
                return false;
            else if (i % 2 == 1 && isIncreasing(result) && result.size() != 1)
                return false;
        }
        return true;
    }

    public static boolean isIncreasing(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) >= list.get(i + 1))
                return false;
        }
        return true;
    }

    public static void getRow(TreeNode node, int level, int currentLevel, List<Integer> result) {
        if (node == null)
            return;
        if (level == currentLevel)
            result.add(node.val);
        getRow(node.left, level, currentLevel + 1, result);
        getRow(node.right, level, currentLevel + 1, result);
    }

    public static int depth(TreeNode node) {
        if (node == null)
            return 0;
        return 1 + Math.max(depth(node.left), depth(node.right));
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(11);
//        root.left = new TreeNode(18);
//        root.right = new TreeNode(14);
//        root.left.left = new TreeNode(3);
//        root.left.right = new TreeNode(7);
//        root.left.right.left = new TreeNode(18);
//        root.left.right.left.left = new TreeNode(6);
        System.out.println(isEvenOddTree(root));
    }
}
