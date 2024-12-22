/*
За дадена низа од случајни броеви кои се внесуваат од стандарден влез,
да се направи преместување на сите нули на почеток на низата.
На стандарден излез да се испечати трансформираната низа.

/

For a given array of random numbers given from standard input,
perform a shift of all zeros at the beginning of the sequence.
Print the transformed array to standard output.

Input:
12
1 9 8 4 0 0 2 7 0 6 0 9

Result:
Transformiranata niza e:
0 0 0 0 1 9 8 4 2 7 6 9
*/


package Exercises.FirstPartialExam;

import java.util.Scanner;

public class PushZero {
    static void pushZeroesToBeginning(int[] arr, int n) {
        int counter = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] != 0) {
                arr[counter] = arr[i];
                counter--;
            }
        }

        for (int i = 0; i < counter; i++) {
            arr[i] = 0;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }
        pushZeroesToBeginning(arr, n);
        System.out.println("Transformiranata niza e:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}