/*
За дадена низа од броеви да се најде максималниот производ кој се формира со множење
на броевите од некоја растечка подниза на таа низа.

Влез: На влез во првиот ред е даден бројот на броеви во низата, а во вториот ред е дадена низата од броеви.

Излез: На излез треба да се испечати максималниот производ кој се бара во задачата.

Пример:
Влез:
6
3 100 4 5 150 6

Излез:
45000

Максималниот производ се формира од растечката подниза 3, 100, 150.
Да се забележи дека најдолгата растечка подниза е друга, 3, 4, 5, 6.

/

For a given array of integers, find the maximum product
formed by multiplication of the numbers of an increasing subarray of the given array.

Input: The first line of the input contains N, the number of elements of the array.
The second line of the input contains the array.

Output: The maximum product formed in the described way.

Example:
Input:
6
3 100 4 5 150 6

Output:
45000

The maximum product is formed from the increasing subarray 3, 100, 150.
Note that the longest increasing subarray is 3, 4, 5, 6.

Input:
6
3 100 4 5 150 6

Result:
45000
*/

package Labs.Lab04_DivideAndConquer_DynamicProgramming;

import java.util.Scanner;

public class MaxProduct {
    static int maxProduct(int[] arr) {
        int n = arr.length;
        int[] products = new int[n];
        for (int i = 0; i < n; i++) {
            products[i] = arr[i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && products[i] < products[j] * arr[i]) {
                    products[i] = products[j] * arr[i];
                }
            }
        }
        int result = products[0];
        for (int i = 1; i < n; i++) {
            if (products[i] > result)
                result = products[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        System.out.println(maxProduct(arr));
    }
}
