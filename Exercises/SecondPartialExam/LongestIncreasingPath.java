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

import DataStructures.BNode;
import DataStructures.BTree;
import DataStructures.Stack;

public class LongestIncreasingPath {
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
//                tree.addChild(findNode(tree.root, parent), BNode.LEFT, child);
//            } else {
//                tree.addChild(findNode(tree.root, parent), BNode.RIGHT, child);
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
