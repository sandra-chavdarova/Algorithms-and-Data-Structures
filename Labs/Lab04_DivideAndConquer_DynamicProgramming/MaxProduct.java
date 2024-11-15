/*
За дадена низа од броеви да се најде максималниот производ кој се формира
со множење на броевите од некоја растечка подниза на таа низа.

Влез: На влез во првиот ред е даден бројот на броеви во низата, а во вториот ред е дадена низата од броеви.

Излез: На излез треба да се испечати максималниот производ кој се бара во задачата.

Input:
6
3 100 4 5 150 6

Result:
45000


Input:
8
10 22 33 21 50 41 60 80

Result:
1742400000
*/

package Labs.Lab04_DivideAndConquer_DynamicProgramming;

public class MaxProduct {
    static int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    static int maxProduct(int[] arr) {
        int n = arr.length;
        int max = arr[0];
        int min = arr[0];
        int result = arr[0];
        for (int i = 1; i < n; i++) {
            int temp = max(arr[i], arr[i] * max, arr[i] * min);
            min = min(arr[i], arr[i] * max, arr[i] * min);
            max = temp;
            result = Math.max(result, max);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {-2, 6, -3, -10, 0, 2};
        System.out.println(maxProduct(arr));
    }

}
