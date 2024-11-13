/*
Колку најмалку пати треба да одземеме квадрат на цел број за некој број Х да стане 0?

Влез: Цел број Х од 1 до 10^5.

Излез: Бараниот резултат.

Примери:

Влез	    Излез	                            Објаснување
13            2             13 - 2*2 = 9 ;   9 - 3*3  = 0.   (одземавме 2 пати)
21            3             21 - 2*2 = 17;  17 - 4*4 = 1;  1 - 1*1 = 0. (одземавме 3 пати)
25            1             25 - 5*5 = 0 . (одземавме 1 пат)
32            2             32 - 4*4 = 16;  16 - 4*4 = 0. (одземавме 2 пати)
------------------------------------------------------------------------------------------------
What is the minimum number of times we need to subtract the square of an integer for a number X to become 0?

Input: Integer X from 1 to 10^5.

Output: The desired result.

Examples:

Input	    Output	                            Explanation
13            2             13 - 2*2 = 9 ;   9 - 3*3  = 0. (we subtracted 2 times)
21            3             21 - 2*2 = 17;  17 - 4*4 = 1;  1 - 1*1 = 0. (we subtracted 3 times)
25            1             25 - 5*5 = 0 . (we subtracted 1 time)
32            2             32 - 4*4 = 16;  16 - 4*4 = 0. (we subtracted 2 times)
*/

package Labs.Lab04;

import java.util.Scanner;
import java.util.Arrays;

public class SquareOfInteger {
    public static int countingSquares(int x) {
        int[] dp = new int[x + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i < x + 1; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[x];
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int X = input.nextInt();
        int result;
        result = countingSquares(X);
        System.out.println(result);
    }
}

/*
6 = 2^2 + 1^2 + 1^2

1 -> d[1] = 1
2 -> d[2] = 2
3 -> d[3] = 3
4 -> d[4] = d[3] + 1 = 4
     d[4] = min(4, 1) = 1
*/


