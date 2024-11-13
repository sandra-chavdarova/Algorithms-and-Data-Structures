package Labs.Lab04;

import java.util.Scanner;

public class BinaryString {
    public static int positiveSubstrings(String binary, int n) {
        int counter = 0;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            if (binary.charAt(i - 1) == '1') {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = dp[i - 1] - 1;
            }
        }
        for (int i = 0; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (dp[j] > dp[i])
                    counter++;
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String binary = scanner.nextLine();
        int result = positiveSubstrings(binary, n);
        System.out.println(result);
    }
}
