/*
Дадена е еднострана поврзана листа чии што јазли содржат по еден String.
Дополнително, даден е и уште еден природен број L.
Од дадената листа потребно е да се најде последниот јазел
којшто содржи String со должина L и да се премести на почеток на листата.

Влез: Во првиот ред од влезот е даден бројот на елементи во листата - N,
па во следните N редови се дадени самите елементи (String-ови) од листата.
На крај, во последниот ред е даден бројот L.

Излез: На излез треба да се испечати листата пред и после промената.

/

You are given a single linked list with String nodes.
Additionally, you are given one more integer L.
You need to find the last node that contains a String with exactly L characters,
and move it to the front of the list.

Input: In the first line from the input the number of elements in the list is given - N,
and then in the next N lines the elements themselves. In the last line, the integer L is given.

Output: The list before and after the transformation

Input:
3
Sky
Blue
Cloud
5

Result:
Sky->Blue->Cloud
Cloud->Sky->Blue
*/

package Labs.Lab01_SLL;

import java.util.Scanner;

import DataStructures.SLLNode;
import DataStructures.SLL;

public class ShiftToFirst {
    public static void shift(SLL<String> list, int L) {
        SLLNode<String> temp = list.getFirst();
        SLLNode<String> match = null;
        while (temp != null) {
            if (temp.element.length() == L) {
                match = temp;
            }
            temp = temp.succ;
        }
        if (match != null) {
            list.insertFirst(match.element);
            list.delete(match);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        SLL<String> list = new SLL<String>();
        for (int i = 0; i < n; i++) {
            list.insertLast(scanner.next());
        }
        int L = scanner.nextInt();
        System.out.println(list.toString());
        shift(list, L);
        System.out.println(list.toString());
    }
}
