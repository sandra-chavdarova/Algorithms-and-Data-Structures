/*
Дадена е двојно поврзана листа од двојно поврзани листи.
Да се најде сума на секоја од подлистите, а потоа производ на овие суми.

Влез:
Број N кој кажува колку листи има
Број М кој кажува колку елементи има во секоја листа
Во следните М линии се податоците 1<=A<=1000 за секоја од листите

Излез:
Еден број што е производот на сумите од листите.

Пример:
Влез:
3
4
1 2 3 4
2 3 4 5
6 7 8 9

Излез:
4200

/

A double linked list of double linked lists is given.
Find the sum of each sub-list, and then the product of all those sums.

Input:
An integer N that tells us how many lists there are.
An integer M that tells us how many elements there are in each list.

Output:
The product of the sums of the sub-lists.

Example:
Input:
3
4
1 2 3 4
2 3 4 5
6 7 8 9

Output:
4200
*/

package Exercises.FirstPartialExam;

import java.util.Scanner;

import DataStructures.DLLNode;
import DataStructures.DLL;

public class DLLofDLLs {
    public static long product(DLL<DLL<Integer>> list) {
        DLLNode<DLL<Integer>> sublist = list.getFirst();
        long product = 1;
        while (sublist != null) {
            DLLNode<Integer> temp = sublist.element.getFirst();
            int sum = 0;
            while (temp != null) {
                sum += temp.element;
                temp = temp.succ;
            }
            product *= sum;
            sublist = sublist.succ;
        }
        return product;
    }

    public static void main(String[] args) {
        DLL<DLL<Integer>> list = new DLL<DLL<Integer>>();
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        for (int i = 0; i < N; i++) {
            DLL<Integer> temp = new DLL<Integer>();
            for (int j = 0; j < M; j++) {
                temp.insertLast(scanner.nextInt());
            }
            list.insertLast(temp);
        }

        long result = product(list);
        System.out.println(result);
    }
}
