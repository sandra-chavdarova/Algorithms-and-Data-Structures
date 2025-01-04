/*
Во следната задача треба да изградите бинарно дрво со N јазли,
за кое ќе треба да одговорите на Q прашања од видот „која е максималната длабочина во поддрвото на избран јазол“.

Секој јазол ќе има уникатно име. Името на коренот на дрвото секогаш ќе ви биде дадено прво.

Влезот ќе содржи N+Q редови од видот
root ime - Треба да го поставите коренот на дрвото да биде јазелот со име ime
add parent_name child_name - Треба да додадете дете јазел со име child_name на јазелот со име parent_name
ask node_name - Треба да го одговориме прашањето за поддрвото на јазелот со име node_name

/

In the following task, you need to build a binary tree with N nodes,
for which you will need to answer Q questions of the type "what is the maximum depth in the subtree of a selected node"..

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
1
3
3
3
5
3
4
5
3
*/

package Labs.Lab07_Trees;

import java.util.Scanner;

public class MaxDepth {
    public static BNode<String> findNode(BNode<String> node, String value) {
        if (node == null)
            return null;
        if (node.info.equals(value))
            return node;
        BNode<String> left = findNode(node.left, value);
        if (left != null)
            return left;
        return findNode(node.right, value);
    }

    public static int maximumDepth(BNode<String> node) {
        if (node == null)
            return 0;
        return Math.max(1 + maximumDepth(node.left), 1 + maximumDepth(node.right));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int q = scanner.nextInt();
        scanner.nextLine();
        BTree<String> tree = new BTree<>();
        scanner.next();
        String root = scanner.next();
        tree.makeRoot(root);
        for (int i = 1; i < n + q; i++) {
            String type = scanner.next();
            String parent = scanner.next();
            if (type.equals("add")) {
                String child = scanner.next();
                String side = scanner.next();
                BNode<String> parentNode = findNode(tree.root, parent);
                if (side.equals("LEFT"))
                    tree.addChild(parentNode, BNode.LEFT, child);
                else
                    tree.addChild(parentNode, BNode.RIGHT, child);
            } else {
                System.out.println(maximumDepth(findNode(tree.root, parent)));
            }
        }
    }
}
