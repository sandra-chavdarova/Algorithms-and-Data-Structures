/*
Дадена е еднострана поврзана листа чии што јазли содржат по еден String.
Дополнително, даден е и уште еден природен број L.
Во дадената листа пред секој јазол којшто содржи String со должина поголема од L да се вметне нов јазол со вредност "Outlier".

Влез: Во првиот ред од влезот е даден бројот на елементи во листата - N,
па во следните N редови се дадени самите елементи (String-ови) од листата.
На крај, во последниот ред е даден бројот L.

Излез: На излез треба да се испечати листата пред и после промената.

/

You are given a single linked list with String nodes.
Additionally, you are given one more integer L. You need to insert a new node with value "Outlier"
before each node that contains a String with more than L characters in the list.

Input: In the first line from the input the number of elements in the list is given - N,
and then in the next N lines the elements themselves. In the last line, the integer L is given.

Output: The list before and after the transformation

Input:
3
Big
Elephant
Small
6

Result:
Big->Elephant->Small
Big->Outlier->Elephant->Small
*/

package Labs.Lab01_SLL;

import java.util.Scanner;

import DataStructures.SLLNode;
import DataStructures.SLL;

public class InsertNodeBefore {
    public static void insert(SLL<String> list, int L) {
        SLLNode<String> temp = list.getFirst();
        while (temp != null) {
            if (temp.element.length() > L) {
                list.insertBefore("Outlier", temp);
            }
            temp = temp.succ;
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
        insert(list, L);
        System.out.println(list.toString());
    }
}