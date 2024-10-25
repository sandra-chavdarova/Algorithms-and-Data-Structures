package Exercises;

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