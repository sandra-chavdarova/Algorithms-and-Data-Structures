/*
Дадено  е бинарно дрво. Потоа дадена е вредноста на некој јазол во дрвото.
Испечатете го збирот на елементите во неговото лево поддрво кои се помали од него
и збирот на елементите во неговото десно поддрво кои се поголеми од него.
--------------------------------------------------------------------------------------------------------
You are given a binary tree and a node value in the tree.
Print the sum of the elements of the node's left subtree that are lower than the given node value
and the sum of the elements of the node's right subtree that are greater than the given node value.

For example:
Input:
10
10 root // index 0
19 left 0  // index 1
8 left 1 // index 2
7 left 2 // index 3
60 right 2 // index 4
5 right 1 // index 5
4 right 0 // index 6
13 right 6 // index 7
2 left 7 // index 8
1 right 7 // index 9
10

Result:
Left: 20
Right: 13

инпутот за првите три линии значи:
на влезот направи root со вредност 10
на node од влезот со индекс 0 стави node лево со вредност 19
на node од влезот со индекс 1 стави node лево со вредност 8

*во оригиналната задача изгледа се внесува и индексот влезот, но може да се реши и без тоа*
*/
package Labs.Lab7_Trees;

import java.util.HashMap;
import java.util.Map;
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
}


public class BinaryTreeSum {
    static int calculateSum(BNode<Integer> node, int parentValue, boolean isLeft) {
        if (node == null)
            return 0;
        int sum = 0;
        if (isLeft && node.info < parentValue) {
            sum += node.info;
        } else if (!isLeft && node.info > parentValue) {
            sum += node.info;
        }
        sum += calculateSum(node.left, parentValue, isLeft);
        sum += calculateSum(node.right, parentValue, isLeft);
        return sum;
    }

    static BNode<Integer> findNode(BNode<Integer> node, int value) {
        if (node == null)
            return null;
        if (value == node.info)
            return node;
        BNode<Integer> left = findNode(node.left, value);
        if (left != null)
            return left;
        return findNode(node.right, value);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        BTree<Integer> tree = new BTree<Integer>();
        int value = scanner.nextInt();
        String type = scanner.next();
        tree.makeRoot(value);
        Map<Integer, BNode<Integer>> levelNode = new HashMap<Integer, BNode<Integer>>();
        levelNode.put(0, tree.root);
        for (int i = 1; i < n; i++) {
            value = scanner.nextInt();
            type = scanner.next();
            int parent = scanner.nextInt();
            BNode<Integer> node = new BNode<Integer>(value);
            levelNode.put(i, node);
            BNode<Integer> parentNode = levelNode.get(parent);
            if (type.equals("left")) {
                parentNode.left = node;
            } else if (type.equals("right")) {
                parentNode.right = node;
            }
        }
        int root = scanner.nextInt();
        BNode<Integer> rootNode = findNode(tree.root, root);
        System.out.println(rootNode.info);
        int left = calculateSum(rootNode.left, rootNode.info, true);
        int right = calculateSum(rootNode.right, rootNode.info, false);

        System.out.println("Left: " + left);
        System.out.println("Right: " + right);
    }
}