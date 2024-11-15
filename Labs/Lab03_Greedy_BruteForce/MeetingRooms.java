/*
Дадени се N состаноци со време на почеток и крај.
Да се најде минималниот број на сали за состаноци потребен за да се одржат сите состаноци.

Влез: Првиот број од влезот е бројот на состаноци N,
а потоа во следниот ред минутата на почнување и минутата на завршување на состанокот.

Излез: Најмалиот потребен број на соби за состаноци за да можат да се одржат сите состаноци

/

We are given N scheduled meetings.
Write an algorithm that finds the minimum number of meeting rooms needed to schedule those meetings.

Input: The first number in the input is the number of meetings N,
then in the next N lines are the start and end time for each of the meetings.

Output: The minimum number of needed meeting rooms to schedule the meetings.

Input:
5
1 2
1 2
5 10
11 14
15 20

Result:
2
*/

package Labs.Lab03_Greedy_BruteForce;

import java.util.Scanner;

public class MeetingRooms {
    public static void sort(int[] start, int[] end, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (start[i] > start[j]) {
                    int temp = start[i];
                    start[i] = start[j];
                    start[j] = temp;
                }
                if (end[i] > end[j]) {
                    int temp = end[i];
                    end[i] = end[j];
                    end[j] = temp;
                }
            }
        }
    }

    public static int meetings(int[] start, int[] end, int n) {
        sort(start, end, n);
        int rooms = 0, maxRooms = 0;
        int s = 0, e = 0;
        while (s < n && e < n) {
            if (start[s] <= end[e]) {
                rooms++;
                maxRooms = Math.max(maxRooms, rooms);
                s++;
            } else {
                rooms--;
                maxRooms = Math.max(maxRooms, rooms);
                e++;
            }
        }
        return maxRooms;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = scanner.nextInt();
            end[i] = scanner.nextInt();
        }
        int maxRooms = meetings(start, end, n);
        System.out.println(maxRooms);
    }
}