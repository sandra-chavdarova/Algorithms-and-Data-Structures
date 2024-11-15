/*
Дадена е сортирана низа од N броеви и број М кој го бараме во таа низа.
Со помош на методата „раздели па владеј“ да се имплементира бинарно пребарување,
да се најде бараниот број во таа низа и да се испечати неговата позиција.
Доколку бројот не е во низата да е испечати "Ne postoi".

Влез: Првиот број од влезот е големината на низата N и бараниот број М,
а потоа во следниот ред се елементите на низата.

Излез: Позицијата на која се наоѓа бараниот број, или "Ne postoi" доколку бројот не се наоѓа во низата.

/

We are given an array of N numbers. Using the method "divide and conquer"
write a binary search algorithm that searches for a given number M.
If the number is found, print the position of the number in the array.
If the number is not in the array, print "Ne postoi".

Input: The first number in the input is the size of the array N, and the number we are searching M.
Then in the next line are the array elements.

Output: The position of the number we are searching, or "Ne postoi" if no such number exists in the array.
*/

package Labs.Lab04_DivideAndConquer_DynamicProgramming;

import java.util.Scanner;

public class BinarySearch {
    public static int binarySearch(int[] array, int start, int end, int key) {
        int result = -1;
        if (start > end)
            return result;
        int mid = (start + end) / 2;
        if (array[mid] == key) {
            return mid;
        } else if (array[mid] < key) {
            return binarySearch(array, mid + 1, end, key);
        } else {
            return binarySearch(array, start, mid - 1, key);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        int result = binarySearch(array, 0, n - 1, m);
        if (result == -1)
            System.out.println("Ne postoi");
        else
            System.out.println(result);
    }
}
