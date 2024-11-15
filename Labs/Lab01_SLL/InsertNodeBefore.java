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