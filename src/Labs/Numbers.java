package Labs;

import java.util.Scanner;

public class Numbers {
    static int Array(int[] a, int n) {
        float average = 0;
        for (int i = 0; i < n; i++) {
            average += a[i];
        }
        average /= n;
        for (int i = 0; i < n; i++) {
            if (a[i] < average) {
                for (int j = i + 1; j < n; j++) {
                    a[j - 1] = a[j];
                }
                n--;
                i--;
            }
        }
        return n;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = input.nextInt();
        }
        System.out.print("{");
        for (int i = 0; i < n - 1; i++) {
            System.out.print(a[i] + ",");
        }
        System.out.println(a[n - 1] + "}");
        n = Array(a, n);
        System.out.print("{");
        for (int i = 0; i < n - 1; i++) {
            System.out.print(a[i] + ",");
        }
        System.out.println(a[n - 1] + "}");
    }
}




/*
import java.util.Scanner;

class Array {
    private int size;
    private int[] a;

    public Array(int size, int[] a) {
        this.size = size;
        this.a = a;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int delete() {
        float average = 0;
        for (int i = 0; i < size; i++) {
            average += a[i];
        }
        average /= size;
        for (int i = 0; i < size; i++) {
            if (a[i] < average) {
                for (int j = i + 1; j < size; j++) {
                    a[j - 1] = a[j];
                }
                setSize(size - 1);
                i--;
            }
        }
        return size;
    }
}

class Labs.Numbers {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = input.nextInt();
        }
        System.out.print("{");
        for (int i = 0; i < n - 1; i++) {
            System.out.print(a[i] + ",");
        }
        System.out.println(a[n - 1] + "}");
        Array array = new Array(n, a);
        n = array.delete();
        System.out.print("{");
        for (int i = 0; i < n - 1; i++) {
            System.out.print(a[i] + ",");
        }
        System.out.println(a[n - 1] + "}");
    }
}
*/