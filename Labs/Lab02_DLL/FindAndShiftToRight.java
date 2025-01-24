/*
Дадена е двострано поврзана листа од цели броеви.
Дополнително, дадени се и уште еден цел број M и еден природен број k.
Треба да се најде првото појавување на M во листата и тој број да се помести k места на десно.

Влез: Во првиот ред од влезот е даден бројот на елементи во листата - N,
па во следните следниот ред самите елементи одделени со празно место.
На крај, во последните два реда дадени се целиот број M и природниот број k.

Излез: На излез треба да се испечати листата пред и после промената.

/

You are given a doubly-linked list of integers.
Additionally, there is one more integer M and a natural number k.
You need to find the first occurence of M in the list and move that node k times to the right.

Input: The first number in the input is the number of integers in the list - N,
then in the next line the elements are given, separated by spaces.
Then, in the last two lines, the integer M and the natural number k are given.

Output: The list before and after the transformation

Input:
5
1 2 3 4 5
3
2

Result:
1<->2<->3<->4<->5
1<->2<->4<->5<->3
*/

package Labs.Lab02_DLL;

import java.util.Scanner;
import DataStructures.DLLNode;
import DataStructures.DLL;

public class FindAndShiftToRight {
    public static void shift(DLL<Integer> list, int M, int k) {
        DLLNode<Integer> match = list.find(M);
        DLLNode<Integer> temp = list.getFirst();
        boolean flag = false;
        while (temp != null) {
            if (temp == match) {
                flag = true;
                for (int i = 0; i < k; i++) {
                    if (temp.succ == null) {
                        list.deleteLast();
                        list.insertFirst(match.element);
                        temp = list.getFirst();
                    } else {
                        list.delete(temp);
                        list.insertAfter(match.element, temp.succ);
                        temp = temp.succ.succ;
                    }
                }
            }
            if (flag) {
                return;
            }
            temp = temp.succ;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        DLL<Integer> list = new DLL<Integer>();
        for (int i = 0; i < n; i++) {
            list.insertLast(scanner.nextInt());
        }
        int M = scanner.nextInt();
        int k = scanner.nextInt();
        System.out.println(list.toString());
        shift(list, M, k);
        System.out.println(list.toString());
    }
}
