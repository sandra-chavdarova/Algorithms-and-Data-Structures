/*
За дадена низа од N цели броеви, да се избришат елементите со вредност помала од просекот на сите елементи во низата.
На пример за низата 1, 2, 3, 4, 5 просекот е (1 + 2 + 3 + 4 + 5) / 5 = 15 / 5 = 3,
што значи треба да се избришат елементите 1 и 2, што значи низата после оваа трансформација ќе биде 3, 4, 5.

Влез: Првиот број од влезот е бројот на елементи во низата N,
а потоа во следниот ред се дадени самите елементи одделени со празно место.

Излез: Низата пред и после направената трансформација.

/

For a given array with N integers, all the elements that are lower than the average of the whole array need to be deleted.
For example for the array 1, 2, 3, 4, 5 the average is (1 + 2 + 3 + 4 + 5) / 5 = 15 / 5 = 3
which means that the elements 1 and 2 should be deleted, so the array after the transformation will be 3, 4, 5.

Input: The first number in the input is the number of integers in the array N,
then in the next line the elements are given, separated by spaces.

Output: The array before and after the transformation.

Input:
5
1 2 3 4 5

Result:
{1,2,3,4,5}
{3,4,5}
*/

package Labs.Lab01;

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
Second solution

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

// add public before class Numbers

class Labs.Lab01.Numbers {
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