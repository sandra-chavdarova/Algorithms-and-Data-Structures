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

class DLLNode<E> {
    protected E element;
    protected DLLNode<E> pred, succ;

    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class DLL<E> {
    private DLLNode<E> first, last;

    public DLL() {
        // Construct an empty DLL
        this.first = null;
        this.last = null;
    }

    public void insertFirst(E o) {
        DLLNode<E> ins = new DLLNode<E>(o, null, first);
        if (first == null)
            last = ins;
        else
            first.pred = ins;
        first = ins;
    }

    public void insertLast(E o) {
        if (first == null)
            insertFirst(o);
        else {
            DLLNode<E> ins = new DLLNode<E>(o, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    public void insertAfter(E o, DLLNode<E> after) {
        if (after == last) {
            insertLast(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
        after.succ.pred = ins;
        after.succ = ins;
    }

    public void insertBefore(E o, DLLNode<E> before) {
        if (before == first) {
            insertFirst(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
        before.pred.succ = ins;
        before.pred = ins;
    }

    public E deleteFirst() {
        if (first != null) {
            DLLNode<E> tmp = first;
            first = first.succ;
            if (first != null) first.pred = null;
            if (first == null)
                last = null;
            return tmp.element;
        } else
            return null;
    }

    public E deleteLast() {
        if (first != null) {
            if (first.succ == null)
                return deleteFirst();
            else {
                DLLNode<E> tmp = last;
                last = last.pred;
                last.succ = null;
                return tmp.element;
            }
        } else
            return null;
    }

    public E delete(DLLNode<E> node) {
        if (node == first) {
            return deleteFirst();
        }
        if (node == last) {
            return deleteLast();
        }
        node.pred.succ = node.succ;
        node.succ.pred = node.pred;
        return node.element;

    }

    public DLLNode<E> find(E o) {
        if (first != null) {
            DLLNode<E> tmp = first;
            while (!tmp.element.equals(o) && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element.equals(o)) {
                return tmp;
            } else {
//                System.out.println("The element does not exist in the list");
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("The list is empty");
//            System.out.println("Listata e prazna");
        }
        return null;
    }

    public void deleteList() {
        first = null;
        last = null;
    }

    public int getSize() {
        int listSize = 0;
        DLLNode<E> tmp = first;
        while (tmp != null) {
            listSize++;
            tmp = tmp.succ;
        }
        return listSize;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp.toString();
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += "<->" + tmp.toString();
            }
        } else {
            ret = "Empty list!!!";
//            ret = "Prazna lista!!!";
        }
        return ret;
    }

    public String toStringR() {
        String ret = new String();
        if (last != null) {
            DLLNode<E> tmp = last;
            ret += tmp.toString();
            while (tmp.pred != null) {
                tmp = tmp.pred;
                ret += "<->" + tmp.toString();
            }
        } else {
            ret = "Empty list!!!";
//            ret = "Prazna lista!!!";
        }
        return ret;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {
        return last;
    }

    public void mirror() {
        DLLNode<E> tmp = null;
        DLLNode<E> current = first;
        last = first;
        while (current != null) {
            tmp = current.pred;
            current.pred = current.succ;
            current.succ = tmp;
            current = current.pred;
        }

        if (tmp != null && tmp.pred != null) {
            first = tmp.pred;
        }
    }
}


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
