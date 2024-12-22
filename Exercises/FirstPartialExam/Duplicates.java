/*
Дадена е еднострана поврзана листа од броеви со дупликати.
За дадена вредност на info поле во листата, да се обезбеди бројот на повторувања на тој број да биде парен.
Доколку за дадената вредност на инфо полето, бројот на елементи со таа вредност е непарен,
треба да се додаде истиот елемент пред првото појавување на елементот со таа вредност  во листата.

Забелешка: 0 е парен број.

Влез:
Во првиот ред се внесуваат бројот на елементи во листата, па потоа самите елементи,
а на крај се внесува бројот (вредноста) кој ќе се осигураме дека се појавува парен број на пати.

Излез:
Листата со парен број на дупликати за дадената вредност.

/

A single linked list of duplicate numbers is given.
For a given value of an info field in the list, ensure that the number of repetitions (duplicates)
of that number is even. If for the given value of the info field, the number of elements with that value is odd,
an element with the same value should be added before the first appearance of the value.

Note: 0 is an even number.

Input: In the first line, the number of elements in the list are given, then the elements themselves,
and finally, the number (value) that will ensure that it appears an even number of times.

Output: The list with an even number of duplicates for a given value is printed on output.

Input:
11
1 2 5 7 9 12 2 4 1 2 8
2

Result:
1->2->2->5->7->9->12->2->4->1->2->8
*/

package Exercises.FirstPartialExam;

import java.util.Scanner;

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }
}

class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int size() {
        int listSize = 0;
        SLLNode<E> temp = first;
        while (temp != null) {
            listSize++;
            temp = temp.succ;
        }
        return listSize;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            SLLNode<E> temp = first;
            ret += temp.element;
            while (temp.succ != null) {
                temp = temp.succ;
                ret += "->" + temp.element;
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, null);
        ins.succ = first;
        // SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }

    public void insertBefore(E o, SLLNode<E> before) {
        if (first != null) {
            SLLNode<E> temp = first;
            if (first == before) {
                this.insertFirst(o);
                return;
            }
            // ako first!=before
            while (temp.succ != before && temp.succ != null)
                temp = temp.succ;
            if (temp.succ == before) {
                temp.succ = new SLLNode<E>(o, before);
                ;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> temp = first;
            while (temp.succ != null)
                temp = temp.succ;
            temp.succ = new SLLNode<E>(o, null);
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> temp = first;
            first = first.succ;
            return temp.element;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> temp = first;
            if (first == node) {
                return this.deleteFirst();
            }
            while (temp.succ != node && temp.succ.succ != null)
                temp = temp.succ;
            if (temp.succ == node) {
                temp.succ = temp.succ.succ;
                return node.element;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }
        } else {
            System.out.println("Listata e prazna");
            return null;
        }

    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> temp = first;
            while (!temp.element.equals(o) && temp.succ != null)
                temp = temp.succ;
            if (temp.element.equals(o)) {
                return temp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return null;
    }
}

public class Duplicates {
    public static void evenRepeats(SLL<Integer> list, int info) {
        int counter = 0;
        SLLNode<Integer> temp = list.getFirst();
        while (temp != null) {
            if (temp.element.equals(info)) {
                counter++;
            }
            temp = temp.succ;
        }

        if (counter % 2 == 1) {
            list.insertBefore(info, list.find(info));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        SLL<Integer> list = new SLL<Integer>();
        for (int i = 0; i < n; i++) {
            list.insertLast(scanner.nextInt());
        }
        int info = scanner.nextInt();
        evenRepeats(list, info);
        System.out.println(list.toString());
    }
}
