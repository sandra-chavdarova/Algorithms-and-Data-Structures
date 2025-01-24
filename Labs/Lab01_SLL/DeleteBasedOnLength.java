/*
Дадена е еднострана поврзана листа чии што јазли содржат по еден String.
Дополнително, даден е и уште еден природен број L.
Од дадената листа потребно е да се избрише секој јазел којшто содржи String со должина помала од L.

Влез: Во првиот ред од влезот е даден бројот на елементи во листата - N,
па во следните N редови се дадени самите елементи (String-ови) од листата.
На крај, во последниот ред е даден бројот L.

Излез: На излез треба да се испечати листата пред и после промената.

/

You are given a single linked list with String nodes.
Additionally, you are given one more integer L.
You need to delete every node from the list that contains a String with less than L characters.

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
Cloud
*/

package Labs.Lab01_SLL;

import java.util.Scanner;

import DataStructures.SLLNode;
import DataStructures.SLL;

public class DeleteBasedOnLength {
    public static void deleteNodes(SLL<String> list, int L) {
        SLLNode<String> temp = list.getFirst();
        while (temp != null) {
            if (temp.element.length() < L) {
                list.delete(temp);
            }
            temp = temp.succ;
        }
    }

    public static void main(String[] args) {
        int n;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        SLL<String> list = new SLL<String>();
        for (int i = 0; i < n; i++) {
            list.insertLast(scanner.next());
        }
        int L = scanner.nextInt();
        System.out.println(list.toString());
        deleteNodes(list, L);
        System.out.println(list.toString());
    }
}
