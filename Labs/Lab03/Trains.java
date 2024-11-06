/*
We are given N trains. Write an algorithm that finds the minimum number of platforms needed to schedule those trains.

Input: the first number in the input is the number of trains N,
then in the next N lines are the arrival and departures minutes for each of the trains.

Output: the minimum number of needed platforms to accommodate the trains
*/

package Labs.Lab03;

import java.util.Scanner;

public class Trains {
    public static void sort(int[] arrivals, int[] departures, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arrivals[i] > arrivals[j]) {
                    int temp = arrivals[i];
                    arrivals[i] = arrivals[j];
                    arrivals[j] = temp;
                }
                if (departures[i] > departures[j]) {
                    int temp = departures[i];
                    departures[i] = departures[j];
                    departures[j] = temp;
                }
            }
        }
    }

    public static int minPlatforms(int[] arrivals, int[] departures, int n) {
        sort(arrivals, departures, n);
        int maxPlatforms = 0, platforms = 0;
        int a = 0, d = 0;
        while (a < n && d < n) {
            if (arrivals[a] < departures[d]) {
                platforms++;
                maxPlatforms = Math.max(maxPlatforms, platforms);
                a++;
            }
            if (arrivals[a] > departures[d]) {
                platforms--;
                maxPlatforms = Math.max(maxPlatforms, platforms);
                d++;
            }
        }
        return maxPlatforms;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] arrivals = new int[n];
        int[] departures = new int[n];

        for (int i = 0; i < n; i++) {
            arrivals[i] = scanner.nextInt();
            departures[i] = scanner.nextInt();
        }

        int minPlatforms = minPlatforms(arrivals, departures, n);
        System.out.println(minPlatforms);
    }
}
