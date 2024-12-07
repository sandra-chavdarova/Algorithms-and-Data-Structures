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
