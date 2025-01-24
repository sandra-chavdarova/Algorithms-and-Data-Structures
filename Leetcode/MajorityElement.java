/*
Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times.
You may assume that the majority element always exists in the array.

Example 1:
Input: nums = [3,2,3]
Output: 3

Example 2:
Input: nums = [2,2,1,1,1,2,2]
Output: 2

Link: https://leetcode.com/problems/majority-element/description/?envType=problem-list-v2&envId=hash-table
*/

package Leetcode;

import DataStructures.MapEntry;
import DataStructures.CBHT;
import DataStructures.SLLNode;

public class MajorityElement {
    public int majorityElement(int[] nums) {
        CBHT<Integer, Integer> map = new CBHT<>(10);
        int result = 1;
        for (int i = 0; i < nums.length; i++) {
            if (map.search(nums[i]) == null) {
                map.insert(nums[i], 1);
            } else {
                map.search(nums[i]).element.value++;
                result = Math.max(result, map.search(nums[i]).element.value);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            int value = map.search(nums[i]).element.value;
            if (value == result)
                return map.search(nums[i]).element.key;
        }
        return -1;
    }
}
