/*
Во следната задача треба да изградите бинарно дрво со N јазли,
за кое ќе треба да одговорите на Q прашања од видот „колку внатрешни јазли има во поддрвото на избран јазол“.

Секој јазол ќе има уникатно име. Името на коренот на дрвото секогаш ќе ви биде дадено прво.

Влезот ќе содржи N+Q редови од видот
root ime - Треба да го поставите коренот на дрвото да биде јазелот со име ime
add parent_name child_name - Треба да додадете дете јазел со име child_name на јазелот со име parent_name
ask node_name - Треба да го одговориме прашањето за поддрвото на јазелот со име node_name

/

In the following task, you need to build a binary tree with N nodes,
for which you will need to answer Q questions of the type "how many internal nodes are there in the subtree of a selected node".

Each node will have a unique name. The name of the root of the tree will always be given to you first.

The input will contain N+Q rows of the type
root name - You need to set the root of the tree to be the node named name
add parent_name child_name - You need to add a child node named child_name to the node named parent_name
ask node_name - We need to answer the question about the subtree of the node named node_name

Input:
11 9
root bravo
add bravo echo LEFT
add echo beard LEFT
ask beard
ask bravo
add bravo foxtrot RIGHT
add beard hotel LEFT
add beard india RIGHT
ask echo
add foxtrot golf LEFT
add golf juliet RIGHT
add india sierra RIGHT
ask foxtrot
ask bravo
ask beard
add echo mike RIGHT
add foxtrot tango RIGHT
ask echo
ask bravo
ask foxtrot

Result:
0
2
2
2
6
2
3
6
2
*/
package Labs.Lab07_Trees;

import java.util.NoSuchElementException;
import java.util.Scanner;

interface Stack<E> {
    // Elementi na stekot se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty();
    // Vrakja true ako i samo ako stekot e prazen.

    public E peek();
    // Go vrakja elementot na vrvot od stekot.

    // Metodi za transformacija:

    public void clear();
    // Go prazni stekot.

    public void push(E x);
    // Go dodava x na vrvot na stekot.

    public E pop();
    // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
}

class ArrayStack<E> implements Stack<E> {
    private E[] elems;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack(int maxDepth) {
        // Konstrukcija na nov, prazen stek.
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }

    public boolean isEmpty() {
        // Vrakja true ako i samo ako stekot e prazen.
        return (depth == 0);
    }

    public E peek() {
        // Go vrakja elementot na vrvot od stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth - 1];
    }

    public void clear() {
        // Go prazni stekot.
        for (int i = 0; i < depth; i++) elems[i] = null;
        depth = 0;
    }

    public void push(E x) {
        // Go dodava x na vrvot na stekot.
        elems[depth++] = x;
    }

    public E pop() {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}

class BNode<E> {
    public E info;
    public BNode<E> left;
    public BNode<E> right;

    static int LEFT = 1;
    static int RIGHT = 2;

    public BNode<E> parent;

    public BNode(E info, BNode<E> parent) {
        this.parent = parent;
        this.info = info;
        left = null;
        right = null;
    }

    public BNode(E info) {
        this.parent = null;
        this.info = info;
        left = null;
        right = null;
    }

    public BNode(E info, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }
}

class BTree<E> {
    public BNode<E> root;

    public BTree() {
        root = null;
    }

    public BTree(E info) {
        root = new BNode<E>(info);
    }

    public void makeRoot(E elem) {
        root = new BNode<E>(elem);
    }

    public BNode<E> addChild(BNode<E> node, int where, E elem) {

        BNode<E> tmp = new BNode<E>(elem, node);

        if (where == BNode.LEFT) {
            if (node.left != null)  // Left child already exists
                return null;
            node.left = tmp;
        } else {
            if (node.right != null) // Right child already exists
                return null;
            node.right = tmp;
        }

        return tmp;
    }

    public void inorder() {
        System.out.print("INORDER: ");
        inorderR(root);
        System.out.println();
    }

    public void inorderR(BNode<E> n) {
        if (n != null) {
            inorderR(n.left);
            System.out.print(n.info.toString() + " ");
            inorderR(n.right);
        }
    }

    public void preorder() {
        System.out.print("PREORDER: ");
        preorderR(root);
        System.out.println();
    }

    public void preorderR(BNode<E> n) {
        if (n != null) {
            System.out.print(n.info.toString() + " ");
            preorderR(n.left);
            preorderR(n.right);
        }
    }

    public void postorder() {
        System.out.print("POSTORDER: ");
        postorderR(root);
        System.out.println();
    }

    public void postorderR(BNode<E> n) {
        if (n != null) {
            postorderR(n.left);
            postorderR(n.right);
            System.out.print(n.info.toString() + " ");
        }
    }

    public void inorderNonRecursive() {
        ArrayStack<BNode<E>> s = new ArrayStack<BNode<E>>(100);
        BNode<E> p = root;
        System.out.print("INORDER (nonrecursive): ");

        while (true) {
            // going to the most-left element while all roots of the subtrees
            // are being added to a stack for later processing
            // pridvizuvanje do kraj vo leva nasoka pri sto site koreni
            // na potstebla se dodavaat vo magacin za podocnezna obrabotka
            while (p != null) {
                s.push(p);
                p = p.left;
            }

            // if stack is empty the subtree has been iterated
            // ako magacinot e prazen znaci deka stebloto e celosno izminato
            if (s.isEmpty())
                break;

            p = s.peek();
            // priting the node on top of the stack
            // pecatenje (obrabotka) na jazelot na vrvot od magacinot
            System.out.print(p.info.toString() + " ");
            // deleting the top node of the stack
            // brisenje na obraboteniot jazel od magacinot
            s.pop();
            // move on the right of the processed node and repeat the process
            // for the right subtree of the node
            // pridvizuvanje vo desno od obraboteniot jazel i povtoruvanje na
            // postapkata za desnoto potsteblo na jazelot
            p = p.right;

        }
        System.out.println();

    }

    int insideNodesR(BNode<E> node) {
        if (node == null)
            return 0;

        if ((node.left == null) && (node.right == null))
            return 0;

        return insideNodesR(node.left) + insideNodesR(node.right) + 1;
    }

    public int insideNodes() {
        return insideNodesR(root);
    }

    int leavesR(BNode<E> node) {
        if (node != null) {
            if ((node.left == null) && (node.right == null))
                return 1;
            else
                return (leavesR(node.left) + leavesR(node.right));
        } else {
            return 0;
        }
    }

    public int leaves() {
        return leavesR(root);
    }

    int depthR(BNode<E> node) {
        if (node == null)
            return 0;
        if ((node.left == null) && (node.right == null))
            return 0;
        return (1 + Math.max(depthR(node.left), depthR(node.right)));
    }

    public int depth() {
        return depthR(root);
    }

    void mirrorR(BNode<E> node) {
        BNode<E> tmp;

        if (node == null)
            return;

        // symmetrical mirror of left and right subtrees
        // simetricno preslikuvanje na levoto i desnoto potsteblo
        mirrorR(node.left);
        mirrorR(node.right);

        // swap left and right pointers on current node
        // smena na ulogite na pokazuvacite na momentalniot jazel
        tmp = node.left;
        node.left = node.right;
        node.right = tmp;

    }

    public void mirror() {
        mirrorR(root);
    }

    BNode<String> findNode(BNode<String> node, String value) {
        if (node == null)
            return null;
        if (node.info.equals(value))
            return node;
        BNode<String> left = findNode(node.left, value);
        if (left != null)
            return left;
        return findNode(node.right, value);
    }

    int countInnerLeaves(BNode<String> node) {
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return 0;
        return countInnerLeaves(node.left) + countInnerLeaves(node.right) + 1;
    }
}

public class InnerLeaves {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int q = scanner.nextInt();
        scanner.nextLine();

        BTree<String> tree = new BTree<String>();
        String type = scanner.next();
        String value = scanner.next();
        tree.makeRoot(value);
        for (int i = 1; i < n + q; i++) {
            type = scanner.next();
            if (type.equals("add")) {
                String parent = scanner.next();
                String child = scanner.next();
                String side = scanner.next();
                if (side.equals("LEFT")) {
                    tree.addChild(tree.findNode(tree.root, parent), BNode.LEFT, child);
                } else {
                    tree.addChild(tree.findNode(tree.root, parent), BNode.RIGHT, child);
                }
            } else if (type.equals("ask")) {
                String node = scanner.next();
                BNode<String> innerRoot = tree.findNode(tree.root, node);
                System.out.println(tree.countInnerLeaves(innerRoot));
            }
        }
    }
}