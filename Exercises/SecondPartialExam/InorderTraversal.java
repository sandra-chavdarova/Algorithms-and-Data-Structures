/*
Напиши inorder изминување за дадено бинарно пребарувачко дрво.

Input:
10
8
2
11
6
0
9
19
3
14
16

Result:
0
2
3
6
8
9
11
14
16
19
*/

package Exercises.SecondPartialExam;

import java.util.Scanner;

import DataStructures.BNode;
import DataStructures.BinarySearchTree;

public class InorderTraversal {
    static void inorderTraversal(BNode<Integer> node) {
        if (node != null) {
            inorderTraversal(node.left);
            System.out.println(node.info);
            inorderTraversal(node.right);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            tree.insert(scanner.nextInt());
        }
        inorderTraversal(tree.getRoot());
    }
}
