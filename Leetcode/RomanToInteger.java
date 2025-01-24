/*
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer.

Link: https://leetcode.com/problems/roman-to-integer/description/?envType=problem-list-v2&envId=hash-table
*/

package Leetcode;

import DataStructures.MapEntry;
import DataStructures.CBHT;
import DataStructures.SLLNode;

public class RomanToInteger {
    public int romanToInt(String s) {
        CBHT<Character, Integer> map = new CBHT<>(7);
        map.insert('I', 1);
        map.insert('V', 5);
        map.insert('X', 10);
        map.insert('L', 50);
        map.insert('C', 100);
        map.insert('D', 500);
        map.insert('M', 1000);
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            int first = map.search(s.charAt(i)).element.value;
            if ((i < (s.length() - 1)) && (first < map.search(s.charAt(i + 1)).element.value)) {
                result -= first;
            } else {
                result += first;
            }
        }
        return result;
    }
}
