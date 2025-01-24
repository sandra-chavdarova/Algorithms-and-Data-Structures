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

package Labs.Lab07_Trees;

import java.util.*;

import DataStructures.SLLNode;
import DataStructures.SLLTree;
import DataStructures.Tree;

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
