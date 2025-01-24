/*
Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

Link: https://leetcode.com/problems/top-k-frequent-elements/description/?envType=problem-list-v2&envId=hash-table
*/

package Leetcode;

import DataStructures.MapEntry;
import DataStructures.CBHT;
import DataStructures.SLLNode;

public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        CBHT<Integer, Integer> table = new CBHT<>(nums.length);
        int[] newNums = new int[nums.length];
        int p = 0;
        for (int i = 0; i < nums.length; i++) {
            if (table.search(nums[i]) == null) {
                table.insert(nums[i], 1);
                newNums[p] = nums[i];
                p++;
            } else {
                table.search(nums[i]).element.value++;
            }
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            int maxValue = -1, maxKey = -1;
            for (int j = 0; j < p; j++) {
                SLLNode<MapEntry<Integer, Integer>> node = table.search(newNums[j]);
                if (node != null && node.element.value >= maxValue) {
                    maxKey = node.element.key;
                    maxValue = node.element.value;
                }
            }
            result[i] = maxKey;
            table.delete(maxKey);
        }
        return result;
    }
}