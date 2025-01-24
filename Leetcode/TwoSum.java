/*
Given an array of integers nums and an integer target,
return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution,
and you may not use the same element twice.

You can return the answer in any order.

Link: https://leetcode.com/problems/two-sum/description/?envType=problem-list-v2&envId=hash-table
*/

package Leetcode;

import DataStructures.MapEntry;
import DataStructures.CBHT;
import DataStructures.SLLNode;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        int k = 0;
        CBHT<Integer, Integer> indices = new CBHT<>(nums.length + 1);
        for (int i = 0; i < nums.length; i++) {
            indices.insert(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int first = nums[i];
            SLLNode<MapEntry<Integer, Integer>> node = indices.search(target - first);
            if (node != null && node.element.value != i) {
                int second = indices.search(target - first).element.value;
                result[k++] = i;
                result[k] = second;
                return result;
            }
        }
        return null;
    }
}
