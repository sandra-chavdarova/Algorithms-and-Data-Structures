/*
Дадено ви е бинарно пребарувачко дрво со N цели броеви.
Напишете функција со која за бинарно пребарувачко дрво и избран елемент Т од дрвото:
- ќе најдете колку елементи во дрвото се помали од T.
Ќе треба да ја искористите таа функција Q пати при градењето на дрвото.
Влезот ќе содржи N+Q редови од видот
- insert value - Треба да ја вметнете вредноста value во дрвото.
- ask value - Треба да одговорите колку елементи во дрвото имаат вредност помала од value

/

You are given a binary search tree with N integers.
Write a function that, for a binary search tree and a selected element T from the tree will:
- you will find how many elements in the tree are smaller than T.
You will need to use that function Q times when building the tree.
The input will contain N+Q rows of the type
- insert value - You need to insert the value value into the tree.
- ask value- You need to answer how many elements in the tree have a value less than value

Input:
10 7
insert 6
insert 3
insert 7
ask 3
ask 6
insert 4
insert 1
insert 2
insert 5
insert 9
ask 3
ask 9
insert 8
insert 10
ask 8
ask 4
ask 5

Result:
0
1
2
7
7
3
4
*/

package Labs.Lab08_BinarySearchTrees;

import java.util.Scanner;

class BNode<E extends Comparable<E>> {

    public E info;
    public BNode<E> left;
    public BNode<E> right;

    public BNode(E info) {
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

//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// Comparable find( x )   --> Return item that matches x
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 *
 * @author Mark Allen Weiss
 */
class BinarySearchTree<E extends Comparable<E>> {

    /**
     * The tree root.
     */
    private BNode<E> root;

    /**
     * Construct the tree.
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     *
     * @param x the item to insert.
     */
    public void insert(E x) {
        root = insert(x, root);
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     *
     * @param x the item to remove.
     */
    public void remove(E x) {
        root = remove(x, root);
    }

    /**
     * Find the smallest item in the tree.
     *
     * @return smallest item or null if empty.
     */
    public E findMin() {
        return elementAt(findMin(root));
    }

    /**
     * Find the largest item in the tree.
     *
     * @return the largest item of null if empty.
     */
    public E findMax() {
        return elementAt(findMax(root));
    }

    /**
     * Find an item in the tree.
     *
     * @param x the item to search for.
     * @return the matching item or null if not found.
     */
    public BNode<E> find(E x) {
        return find(x, root);
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty() {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree() {
        if (isEmpty()) {
            System.out.println("Empty tree");
        } else {
            printTree(root);
        }
    }

    /**
     * Internal method to get element field.
     *
     * @param t the node.
     * @return the element field or null if t is null.
     */
    private E elementAt(BNode<E> t) {
        if (t == null)
            return null;
        return t.info;
    }

    /**
     * Internal method to insert into a subtree.
     *
     * @param x the item to insert.
     * @param t the node that roots the tree.
     * @return the new root.
     */
    private BNode<E> insert(E x, BNode<E> t) {
        if (t == null) {
            t = new BNode<E>(x, null, null);
        } else if (x.compareTo(t.info) < 0) {
            t.left = insert(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
            t.right = insert(x, t.right);
        } else ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     *
     * @param x the item to remove.
     * @param t the node that roots the tree.
     * @return the new root.
     */
    @SuppressWarnings({"raw", "unchecked"})
    private BNode<E> remove(Comparable x, BNode<E> t) {
        if (t == null)
            return t;   // Item not found; do nothing

        if (x.compareTo(t.info) < 0) {
            t.left = remove(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) { // Two children
            t.info = findMin(t.right).info;
            t.right = remove(t.info, t.right);
        } else {
            if (t.left != null)
                return t.left;
            else
                return t.right;
        }
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     *
     * @param t the node that roots the tree.
     * @return node containing the smallest item.
     */
    private BNode<E> findMin(BNode<E> t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        }
        return findMin(t.left);
    }

    /**
     * Internal method to find the largest item in a subtree.
     *
     * @param t the node that roots the tree.
     * @return node containing the largest item.
     */
    private BNode<E> findMax(BNode<E> t) {
        if (t == null) {
            return null;
        } else if (t.right == null) {
            return t;
        }
        return findMax(t.right);
    }

    /**
     * Internal method to find an item in a subtree.
     *
     * @param x is item to search for.
     * @param t the node that roots the tree.
     * @return node containing the matched item.
     */
    private BNode<E> find(E x, BNode<E> t) {
        if (t == null)
            return null;

        if (x.compareTo(t.info) < 0) {
            return find(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
            return find(x, t.right);
        } else {
            return t;    // Match
        }
    }

    /**
     * Internal method to print a subtree in sorted order.
     *
     * @param t the node that roots the tree.
     */
    private void printTree(BNode<E> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.info);
            printTree(t.right);
        }
    }

    public void printTreeWithIndent() {
        printTreeWithIndent(root, 0);
    }

    private void printTreeWithIndent(BNode<E> t, int indent) {
        if (t != null) {
            printTreeWithIndent(t.left, indent + 1);
            for (int i = 0; i < indent; i++) System.out.print("   ");
            System.out.println(t.info);
            printTreeWithIndent(t.right, indent + 1);
        }
    }

    public BNode<E> getRoot() {
        return root;
    }
}

public class CountSmallerElements {
    public static int count(BNode<Integer> node, int value) {
        if (node == null)
            return 0;
        if (node.info < value)
            return 1 + count(node.left, value) + count(node.right, value);
        return count(node.left, value);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int q = scanner.nextInt();
        scanner.nextLine();
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i = 0; i < n + q; i++) {
            String type = scanner.next();
            if (type.equals("insert")) {
                int child = scanner.nextInt();
                tree.insert(child);
            } else {
                int value = scanner.nextInt();
                System.out.println(count(tree.getRoot(), value));
            }
            scanner.nextLine();
        }
    }
}
