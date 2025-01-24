/*
Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).

Link: https://leetcode.com/problems/find-largest-value-in-each-tree-row/description/?envType=problem-list-v2&envId=tree
*/

package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class FindLargestValueInEachTreeRow {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;
        largestValuesUtil(root, result, 0);
        return result;
    }

    public static void largestValuesUtil(TreeNode node, List<Integer> result, int level) {
        if (result.size() > level) {
            if (result.get(level) < node.val)
                result.set(level, node.val);
        } else {
            result.add(node.val);
        }
        if (node.left != null)
            largestValuesUtil(node.left, result, level + 1);
        if (node.right != null)
            largestValuesUtil(node.right, result, level + 1);
    }
}
