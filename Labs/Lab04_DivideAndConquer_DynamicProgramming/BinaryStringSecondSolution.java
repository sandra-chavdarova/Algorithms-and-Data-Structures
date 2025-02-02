/*
Даден е бинарен стринг S. Бинарен стринг е стринг којшто во себе соджи само ‘1’ и ‘0’.
Подстринг на бинарен стринг се нарекува позитивен ако бројот на '1' во подстрингот е строго поголем од бројот на '0'.
Да се испечати бројот на позитивни подстрингови за внесениот стринг.

Влез: Во првиот ред на влезот е должината на стрингот N. Во вториот ред е бинарниот стринг S
5
10011

Излез: На излез треба да се испечати бројот на позитивни подстрингови во S
6

Објаснување: За дадениот стринг 10011, позитивни подстрингови се:
1(позиција 0)
1(позиција 3)
1 (позиција 4)
11 (позиции 3 и 4)
011 (позиции 2 до 4)
10011 (целиот стринг)

/

You are given a binary string S. A binary string is a string that contains only '1's and '0's.
A substring of a binary string is called positive if the number of '1's in the substring is strictly greater
than the number of '0's. Print the number of positive substrings for the given string.

Input: The first line of the input contains the length of the string N. The second line contains the binary string S.
5
10011

Output: The output should print the number of positive substrings in S
6

Explanation: For the given string 10011, the positive substrings are:
1(position 0)
1(position 3)
1 (position 4)
11 (positions 3 и 4)
011 (positions from 2 to 4)
10011 (the entire string)
*/

package Labs.Lab04_DivideAndConquer_DynamicProgramming;

import java.util.Scanner;

public class BinaryStringSecondSolution {
    public static boolean count(String string, int start, int end) {
        int ones = 0, zeroes = 0;
        for (int i = start; i <= end; i++) {
            if (string.charAt(i) == '1') {
                ones++;
            } else
                zeroes++;
        }
        return ones > zeroes;
    }

    public static int positiveSubstrings(String string, int n) {
        int[] dp = new int[string.length()];
        for (int i = 0; i < n; i++) {
            if (string.charAt(i) == '1') {
                dp[i] = 1;
            } else
                dp[i] = 0;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (count(string, j, i)) {
                    dp[i]++;
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += dp[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String string = scanner.nextLine();
        System.out.println(positiveSubstrings(string, n));
    }
}
