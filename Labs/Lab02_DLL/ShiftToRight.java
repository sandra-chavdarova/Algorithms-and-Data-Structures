/*
Дадена е двострано поврзана листа од цели броеви. Дополнително, даден е и уште еден природен број k.
Елементите во листата треба да се ротираат k пати на десно.

Влез: Во првиот ред од влезот е даден бројот на елементи во листата - N,
па во следните следниот ред самите елементи одделени со празно место.
На крај, во последниот ред даден е и природниот број k.

Излез: На излез треба да се испечати листата пред и после промената.
/

You are given a doubly-linked list of integers. Additionally, there is one more natural number k.
You need to rotate the elements of the list k times to the right.

Input: The first number in the input is the number of integers in the list - N,
then in the next line the elements are given, separated by spaces.
Then, in the last line, the natural number k is given.

Output: The list before and after the transformation

Input:
5
1 2 3 4 5
2

Result:
1<->2<->3<->4<->5
4<->5<->1<->2<->3
*/

package Labs.Lab02_DLL;

import java.util.Scanner;

import DataStructures.DLLNode;
import DataStructures.DLL;

public class ShiftToRight {
    public static void shift(DLL<Integer> list, int k) {
        for (int i = 0; i < k; i++) {
            list.insertFirst(list.getLast().element);
            list.deleteLast();
        }
    }

    public static void main(String[] args) {
        int n;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        DLL<Integer> list = new DLL<>();
        for (int i = 0; i < n; i++) {
            list.insertLast(scanner.nextInt());
        }
        int k = scanner.nextInt();
        System.out.println(list.toString());
        shift(list, k);
        System.out.println(list.toString());
    }
}