/*
Во следната задача треба да изградите неподредено (општо) дрво со N јазли,
за кое ќе треба да одговорите на Q прашања од видот „колку лисја има поддрвото на избран јазол“.

Секој јазол ќе има уникатен индекс од 1 до N. Коренот на дрвото секогаш ќе има индекс 1.
Сите деца ќе имаат индекси поголеми од индексите на родителите.

Влезот ќе содржи N+Q редови од видот
root 1 - Треба да го поставите коренот на дрвото да биде јазелот со индекс 1
add parent_index child_index - Треба да додадете дете јазел со индекс child_index на јазелот со индекс parent_index
ask node_index - Треба да одговорите колку листови има во поддрвото на јазелот со индекс node_index

/

In the following task you need to build an unordered (general) tree with N nodes,
for which you will need to answer Q questions of the type "how many leaves does the subtree of a selected node have".

Each node will have a unique index from 1 to N. The root of the tree will always have an index 1.
All children will have indices greater than the parent indices.

The input will contain N+Q rows of the type
root 1 - You need to set the root of the tree to be the node with index 1
add parent_index child_index - You need to add a child node with index child_index to the node with index parent_index
ask node_index - You need to answer how many leaves are in the subtree of the node with index node_index

Input:
11 5
root 1
add 1 2
add 2 3
ask 1
add 1 4
add 2 5
add 3 6
ask 2
add 3 7
ask 1
add 1 8
add 4 9
add 2 10
add 4 11
ask 2
ask 1

Result:
1
2
4
4
7
*/

package Labs.Lab7_Trees;

import java.util.*;

interface Tree<E> {
    // //////////Accessors ////////////

    public Tree.Node<E> root();

    public Tree.Node<E> parent(Tree.Node<E> node);

    public int childCount(Tree.Node<E> node);

    // //////////Transformers ////////////
    public void makeRoot(E elem);

    public Tree.Node<E> addChild(Tree.Node<E> node, E elem);

    public void remove(Tree.Node<E> node);

    // //////////Iterator ////////////
    public Iterator<E> children(Tree.Node<E> node);

    // //////Inner interface for tree nodes ////////
    public interface Node<E> {

        public E getElement();

        public void setElement(E elem);

    }
}


class SLLTree<E> implements Tree<E> {

    // SLLNode is the implementation of the Node interface
    class SLLNode<P> implements Node<P> {

        // Holds the links to the needed nodes
        SLLNode<P> parent, sibling, firstChild;

        // Hold the data
        P element;

        public SLLNode(P o) {
            element = o;
            parent = sibling = firstChild = null;
        }

        public P getElement() {
            return element;
        }

        public void setElement(P o) {
            element = o;
        }

    }

    protected SLLNode<E> root;

    public SLLTree() {
        root = null;
    }

    public Node<E> root() {
        return root;
    }

    public Tree.Node<E> parent(Tree.Node<E> node) {
        return ((SLLNode<E>) node).parent;
    }

    public int childCount(Tree.Node<E> node) {
        SLLNode<E> tmp = ((SLLNode<E>) node).firstChild;
        int num = 0;
        while (tmp != null) {
            tmp = tmp.sibling;
            num++;
        }
        return num;
    }

    public void makeRoot(E elem) {
        root = new SLLNode<E>(elem);
    }

    public Node<E> addChild(Node<E> node, E elem) {
        SLLNode<E> tmp = new SLLNode<E>(elem);
        SLLNode<E> curr = (SLLNode<E>) node;
        tmp.sibling = curr.firstChild;
        curr.firstChild = tmp;
        tmp.parent = curr;
        return tmp;
    }

    public void remove(Tree.Node<E> node) {
        SLLNode<E> curr = (SLLNode<E>) node;
        if (curr.parent != null) {
            if (curr.parent.firstChild == curr) {
                // The node is the first child of its parent
                // Reconnect the parent to the next sibling
                curr.parent.firstChild = curr.sibling;
            } else {
                // The node is not the first child of its parent
                // Start from the first and search the node in the sibling list
                // and remove it
                SLLNode<E> tmp = curr.parent.firstChild;
                while (tmp.sibling != curr) {
                    tmp = tmp.sibling;
                }
                tmp.sibling = curr.sibling;
            }
        } else {
            root = null;
        }
    }

    class SLLTreeIterator<T> implements Iterator<T> {

        SLLNode<T> start, current;

        public SLLTreeIterator(SLLNode<T> node) {
            start = node;
            current = node;
        }

        public boolean hasNext() {
            return (current != null);
        }

        public T next() throws NoSuchElementException {
            if (current != null) {
                SLLNode<T> tmp = current;
                current = current.sibling;
                return tmp.getElement();
            } else {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            if (current != null) {
                current = current.sibling;
            }
        }
    }

    public Iterator<E> children(Tree.Node<E> node) {
        return new SLLTreeIterator<E>(((SLLNode<E>) node).firstChild);
    }

    void printTreeRecursive(Node<E> node, int level) {
        if (node == null)
            return;
        int i;
        SLLNode<E> tmp;

        for (i = 0; i < level; i++)
            System.out.print("  ");
        System.out.println(node.getElement().toString());
        tmp = ((SLLNode<E>) node).firstChild;
        while (tmp != null) {
            printTreeRecursive(tmp, level + 1);
            tmp = tmp.sibling;
        }
    }

    public void printTree() {
        printTreeRecursive(root, 0);
    }

    public int countMaxChildren() {
        return countMaxChildrenRecursive(root);
    }

    int countMaxChildrenRecursive(SLLNode<E> node) {
        int t = childCount(node);
        SLLNode<E> tmp = node.firstChild;
        while (tmp != null) {
            t = Math.max(t, countMaxChildrenRecursive(tmp));
            tmp = tmp.sibling;
        }
        return t;
    }

    public int countLeaves(Tree.Node<E> node) {
        return countLeavesRecursive((SLLNode<E>) node);
    }

    int countLeavesRecursive(SLLNode<E> node) {
        if (node == null) {
            return 0;
        }

        if (childCount(node) == 0) {
            return 1;
        }

        int leafCount = 0;
        SLLNode<E> child = node.firstChild;

        while (child != null) {
            leafCount += countLeavesRecursive(child);
            child = child.sibling;
        }
        return leafCount;
    }
}

public class NumberOfLeaves {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int q = scanner.nextInt();
        scanner.nextLine();

        SLLTree<Integer> tree = new SLLTree<Integer>();
        Map<Integer, Tree.Node<Integer>> nodes = new HashMap<Integer, Tree.Node<Integer>>();

        String action = scanner.next();
        int i = scanner.nextInt();
        tree.makeRoot(i);
        Tree.Node<Integer> nodeRoot = tree.root;
        nodes.put(i, nodeRoot);

        for (i = 2; i <= n + q; i++) {
            scanner.nextLine();
            action = scanner.next();
            if (action.equals("add")) {
                int parent = scanner.nextInt();
                int child = scanner.nextInt();
                Tree.Node<Integer> nodeParent = nodes.get(parent);
                Tree.Node<Integer> nodeChild = tree.addChild(nodeParent, child);
                nodes.put(child, nodeChild);

            } else if (action.equals("ask")) {
                int parent = scanner.nextInt();
                Tree.Node<Integer> node = nodes.get(parent);
                System.out.println(tree.countLeaves(node));
            }
        }
    }
}
