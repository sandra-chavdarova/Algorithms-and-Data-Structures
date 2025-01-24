/*
Дадена е еднострана поврзана листа чии што јазли содржат по еден String.
Дополнително, даден е и уште еден природен број L.
Во дадената листа потребно е сите јазли коишто содржат String со должина L да се поместат на крај на листата,
задржувајќи го и оригиналниот распоред на елементите.

Влез: Во првиот ред од влезот е даден бројот на елементи во листата - N,
па во следните N редови се дадени самите елементи (String-ови) од листата.
На крај, во последниот ред е даден бројот L.

Излез: На излез треба да се испечати листата пред и после промената.

/

You are given a single linked list with String nodes.
Additionally, you are given one more integer L.
You need to move all nodes from the list that contain a String with exactly L characters to the end of the list,
keeping the original order of elements as well.

Input: In the first line from the input the number of elements in the list is given - N,
and then in the next N lines the elements themselves.
In the last line, the integer L is given.

Output: The list before and after the transformation

Input:
4
Apple
Banana
Pear
Watermelon
6

Result:
Apple->Banana->Pear->Watermelon
Apple->Pear->Watermelon->Banana
*/

package Labs.Lab01_SLL;

import java.util.Scanner;

import DataStructures.SLLNode;
import DataStructures.SLL;

public class ShiftNodes {
    public static void shift(SLL<String> list, int L) {
        SLLNode<String> temp = list.getFirst();
        int n = list.size();
        for (int i = 0; i < n && temp != null; i++) {
            if (temp.element.length() == L) {
                list.insertLast(temp.element);
                list.delete(temp);
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
        shift(list, L);
        System.out.println(list.toString());
    }
}
