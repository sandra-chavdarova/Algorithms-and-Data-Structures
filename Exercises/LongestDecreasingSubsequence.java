/*
Најди ја должината на најдолгата опаѓачка секвенца во една низа.
Броевите во секвенцата не мора да се наоѓаат на последователни индекси во низата.

/

Find the length of longest decreasing sequence in an array.
The numbers in the sequence don't need to be on consecutive indices in the array.

Input:
6
0 -10 -20 -30 -50 0

Result:
5
*/

package Exercises;

import java.util.Scanner;

public class LongestDecreasingSubsequence {
    private static int longest(int[] a) {
        int n = a.length;
        int max = 1;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (a[j] > a[i] && result[j] + 1 > result[i]) {
                    result[i] = result[j] + 1;
                    max=Math.max(max, result[i]);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = stdin.nextInt();
        }
        System.out.println(longest(a));
    }
}
