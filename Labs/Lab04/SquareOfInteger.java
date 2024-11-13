/*
What is the minimum number of times we need to subtract the square of an integer for a number X to become 0?

Input: Integer X from 1 to 10^5

Output: the desired result.

Input:
13
21
25
32

Result
2
3
1
2
*/

package Labs.Lab04;

import java.util.Arrays;
import java.util.Scanner;

public class SquareOfInteger {
    public static int countSquares(int x) {
        int[] counter = new int[x + 1];
        Arrays.fill(counter, Integer.MAX_VALUE);
        counter[0] = 0;
        for (int i = 1; i <= x; i++) {
            for (int j = 1; j * j <= i; j++) {
                counter[i] = Math.min(counter[i], counter[i - j * j] + 1);
            }
        }
        return counter[x];
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int X = input.nextInt();
        int result = countSquares(X);
        System.out.println(result);
    }
}

