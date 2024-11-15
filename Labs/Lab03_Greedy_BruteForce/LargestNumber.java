/*
Дадени се N цифри. Да се напише алгоритам кој ќе го даде најголемиот можен број составен од тие цифри.
Влез: Првиот број од влезот е бројот на цифри N, а потоа во следниот ред се цифрите.
Излез: Најголемиот број кој може да се состави од тие цифри.

/

We are given N digits. Write an algorithm that composes the largest possible number from those digits.
Input: The first number in the input is the number of digits N, then in the next line are the given digits.
Output: The maximum possible number containing those digits.

Input 1:
7
9 7 3 7 9 3 1

Result 1:
9977331


Input 2:
6
0 0 0 0 0 0

Result 2:
000000
*/

package Labs.Lab03_Greedy_BruteForce;

import java.util.Scanner;

public class LargestNumber {
    void bubbleSort(int[] digits, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (digits[i] < digits[j]) {
                    int temp = digits[i];
                    digits[i] = digits[j];
                    digits[j] = temp;
                }
            }
        }
    }

//    int number(int[] digits, int n) {
//        bubbleSort(digits, n);
//        int result = 0;
//        for (int i = 0; i < n; i++) {
//            result = result * 10 + digits[i];
//        }
//        return result;
//    }

    public static void main(String[] args) {
        LargestNumber l = new LargestNumber();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] digits = new int[n];
        for (int i = 0; i < n; i++) {
            digits[i] = scanner.nextInt();
        }
        l.bubbleSort(digits, n);
        for (int i = 0; i < n; i++) {
            System.out.print(digits[i]);
        }
    }
}
