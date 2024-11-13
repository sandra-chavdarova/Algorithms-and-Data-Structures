package Labs.Lab04;

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
