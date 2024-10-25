package Exercises;

import java.util.Scanner;

public class ReverseWord {

    public static void printReversed(String word) {
        int n = word.length();
        for (int i = n - 1; i >= 0; i--) {
            System.out.print(word.charAt(i));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String word = scanner.nextLine();
            printReversed(word);
        }
    }
}
