/*
Дадени се N задачи кои треба да се изработат. За секоја од задачите го знаеме времето за изработка (во часови)
и заработката која ја носи. Да се најде максималната заработка во рок од една 40 часовна работна недела.
Напомена дека и делумно сработени задачи носат делумна заработка, во зависност од процентот на завршеност.

Влез: Првиот број од влезот е бројот на задачи N,
а потоа во следниот ред времетраењето на задачата во часови и заработката која ја носи.

Излез: Максимална заработка која можеме да ја направиме за 40 часа.

/

We are given N tasks with estimated completion time and the amount we can earn from that task.
Write an algorithm that finds the maximum earnings we can have for 40 hours.
Note that we can have a partial earning from a partially completed task.

Input: The first number in the input is the number of tasks N,
then in the next N lines are the time needed for the task and the amount of money we can earn from it.

Output: The maximum amount we can earn in 40 hours.

Input:
3
10 60
20 100
30 120

Result:
200
*/
package Labs.Lab03;

import java.util.Scanner;

public class FractionalKnapsack {
    public static void sort(int[] p, int[] w, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((float) p[i] / w[i] < (float) p[j] / w[j]) {
                    int temp = p[i];
                    p[i] = p[j];
                    p[j] = temp;
                    temp = w[i];
                    w[i] = w[j];
                    w[j] = temp;
                }
            }
        }
    }

    public static float knapsack(int[] p, int[] w, int n, float[] x) {
        int capacity = 40;
        sort(p, w, n);
        float profit = 0;
        for (int i = 0; i < n; i++) {
            x[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            if (capacity > w[i]) {
                profit += p[i];
                capacity -= w[i];
            } else {
                profit += (float) capacity / (float) w[i] * (float) p[i];
                x[i] = (float) capacity / (float) w[i];
                capacity = 0;
                break;
            }
        }
        return profit;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] p = new int[n];
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
            p[i] = scanner.nextInt();
        }
        float[] x = new float[n];
        float profit = knapsack(p, w, n, x);
        System.out.println((int) profit);
    }
}
