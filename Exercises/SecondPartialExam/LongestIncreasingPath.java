/*
За дадено бинарно дрво, пронајдете ја должината на најдолгата патека која се состои од последователни јазли (родител-дете, односно патека движечки се од горе надолу во хиерархијата) со вредности во последователен растечки редослед (разликата меѓу даден претходник и следбеник во патеката е 1).
         10
        /   \
      11     9
     / \    / \
   13  12  13  8

Максимална должина на патека со јазли во последователен растечки редослед е 3 (за јазлите: 10, 11, 12).
Појаснување: Патеката 10, 11, 13 не се зема во предвид бидејќи се гледа разликата меѓу 13 и 11, која треба да изнесува 1 за да се смета за валидна патека.
         5
       /   \
      8     11
     / \    / \
    9  10  6  15

*/

package Exercises.SecondPartialExam;

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


    static BNode<Integer> findNode(BNode<Integer> node, Integer value) {
        if (node == null) {
            return null;
        }
        if (node.info == value) {
            return node;
        }
        BNode<Integer> left = findNode(node.left, value);
        if (left != null) {
            return left;
        }
        return findNode(node.right, value);
    }
}

public class LongestIncreasingPath {
    public static int maxDistance(BNode<Integer> node) {
        return 1 + maxDistanceUtil(node);
    }

    public static int maxDistanceUtil(BNode<Integer> node) {
        if (node == null) {
            return 0;
        }
        if ((node.left != null && node.info + 1 == node.left.info) || (node.right != null && node.info + 1 == node.right.info)) {
            return 1 + Math.max(maxDistanceUtil(node.right), maxDistanceUtil(node.left));
        }
        return Math.max(maxDistanceUtil(node.right), maxDistanceUtil(node.left));
    }

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        BTree<Integer> tree = new BTree<>();
//        tree.makeRoot(scanner.nextInt());
//        for (int i = 0; i < n; i++) {
//            Integer parent = scanner.nextInt();
//            Integer child = scanner.nextInt();
//            String side = scanner.next();
//            if (side.equals("LEFT")) {
//                tree.addChild(BTree.findNode(tree.root, parent), BNode.LEFT, child);
//            } else {
//                tree.addChild(BTree.findNode(tree.root, parent), BNode.RIGHT, child);
//            }
//            scanner.nextLine();
//        }
        BNode<Integer> root = new BNode<>(5);
        root.left = new BNode<>(8);
        root.right = new BNode<>(11);
        root.left.left = new BNode<>(9);
        root.left.left.left = new BNode<>(6);
        root.right.right = new BNode<>(10);
        root.right.right.left = new BNode<>(15);
        System.out.println(maxDistance(root));
    }
}
