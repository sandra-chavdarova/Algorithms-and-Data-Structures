/*
Дадена е улица на која има поставено места за улични светилки. Улицата е со должина M и има N такви места
на кои можат да се постават улични светилки и нивната позиција е дадена во една низа.
Ако знаеме дека една светилка освен својата позиција осветлува и уште две позиции во лево и две во десно,
да се најде најмалиот број на светилки со кои може да се осветли улицата.
Не мора да поставуваме светилка на секоја можна позиција.

Влез: Првиот број од влезот е бројот на позиции за светилки N и должината на улицата M,
а потоа во следниот ред се дадени можните позиции за поставување на светилки.

Излез: Минимален број на светилки за да се осветли улицата.

/

We are given a street with N possible positions on which we can put a light.
A single light will illuminate its own position and 2 more positions to the left and 2 more to the right of it.
Our task is to illuminate the street with the minimum possible lights. Not all possible positions must contain light.

Input: The first number in the input is the number of possible positions to put a light bulb N
and the length of the street M, then in the next line are the possible positions on which we can put a light.

Output: The minimum lights we need to illuminate the street.

Input 1:
5 5
1 2 3 4 5

Result 1:
1

Input 2:
3 10
3 8 9

Result 2:
2
*/
package Labs.Lab03_Greedy_BruteForce;

import java.util.Scanner;

public class LampPosts {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] positions = new int[N];
        for (int i = 0; i < N; i++) {
            positions[i] = sc.nextInt();
        }

        int lamps = 0;
        int i = 0;
        while (i < N) {
            int lampPosition = positions[i];
            while (i < N && positions[i] <= lampPosition + 2) {
                i++;
            }
            lamps++;

            int coveredPosition = positions[i - 1] + 2;
            while (i < N && positions[i] <= coveredPosition) {
                i++;
            }
        }
        System.out.println(lamps);
    }
}


