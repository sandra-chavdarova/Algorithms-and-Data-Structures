/*
За дадена двојно поврзана листа од N цели броеви, треба да се најде бројот на елементи
такви што просекот на елементите од пред него во листата е поголем од просекот на елементи после него во листата.

Влез: Првиот број од влезот е бројот на елементи во листата N,
а потоа во следниот ред се дадени самите елементи одделени со празно место.

Излез: Бројот на елементи што го задоволуваат условот.

/

For a given doubly-linked list with N integers, you need to find the number of elements
such that the average of all elements before it is bigger than the average of all elements after it in the list.

Input: The first number in the input is the number of integers in the list N,
then in the next line the elements are given, separated by spaces.

Output: The number of elements that satisfy the condition.

Input:
5
1 2 3 4 5

Result:
0
*/

package Labs.Lab02_DLL;

import java.util.Scanner;

import DataStructures.DLLNode;
import DataStructures.DLL;

public class Average {
    public static int find(DLL<Integer> list) {
        int counter = 0;
        DLLNode<Integer> temp = list.getFirst();
        // don't check for the last element in the list,
        // because there are no elements after it
        while (temp.succ != null) {
            float averageStart = 0, averageEnd = 0;
            int start = 0, end = 0;
            DLLNode<Integer> tmp1 = temp.succ;
            DLLNode<Integer> tmp2 = temp.pred;
            // Right side
            while (tmp1 != null) {
                averageEnd += tmp1.element;
                end++;
                tmp1 = tmp1.succ;
            }
            // Left side
            while (tmp2 != null) {
                averageStart += tmp2.element;
                start++;
                tmp2 = tmp2.pred;
            }

            // check for dividing by 0
            if (start > 0) {
                averageStart /= (float) start;
            }
            if (end > 0) {
                averageEnd /= (float) end;
            }
            if (averageStart > averageEnd)
                counter++;

            temp = temp.succ;
        }

        return counter;
    }

    public static void main(String[] args) {
        int n;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        DLL<Integer> list = new DLL<Integer>();
        for (int i = 0; i < n; i++) {
            list.insertLast(scanner.nextInt());
        }

        int result = find(list);
        System.out.println(result);
    }
}
