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
package Labs.Lab07_Trees;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import DataStructures.Stack;
import DataStructures.BNode;
import DataStructures.BTree;

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