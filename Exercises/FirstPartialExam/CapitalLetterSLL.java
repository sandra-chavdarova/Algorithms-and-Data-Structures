/*
Дадена е еднострано поврзана листа од зборови (стрингови).
Да се трансформира листата така што зборовите коишто започнуваат со мала буква се преместуваат
на крајот на листата, задржувајќи го редоследот.
Влез: Во првиот ред од влезот е даден цел број N, кој го претставува бројот на зборови во листата,
а потоа во следните N реда даден е секој од зборовите.
Излез: На излез треба да се испечати листата пред и после трансформацијата.

Пример:
Влез:
4
ova
E
Eden
primer

Излез:
ova -> E -> Eden -> primer
E -> Eden -> ova -> primer

/

A singly linked list of words (Strings) is given.
You need to transform the list so that the words starting with a lowercase letter
will be placed at the end of the list, keeping the original general order.
Input: In the first line of the input, an integer N is given,
representing the number of words in the list, and then in the next N lines each of the words is given.
Output: The list before and after the transformation

Example:
Input:
4
ova
E
Eden
primer

Output:
ova -> E -> Eden -> primer
E -> Eden -> ova -> primer
*/

package Exercises.FirstPartialExam;

import java.util.Scanner;

import DataStructures.SLL;
import DataStructures.SLLNode;

public class CapitalLetterSLL {
    public static void shift(SLL<String> list, int n) {
        SLLNode<String> temp = list.getFirst();
        while (temp != null && n > 0) {
            if (Character.isLowerCase(temp.element.charAt(0))) {
                list.insertLast(temp.element);
                list.delete(temp);
            }
            temp = temp.succ;
            n--;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        SLL<String> list = new SLL<String>();
        for (int i = 0; i < n; i++) {
            list.insertLast(scanner.next());
        }
        System.out.println(list.toString());
        shift(list, n);
        System.out.println(list.toString());
    }
}