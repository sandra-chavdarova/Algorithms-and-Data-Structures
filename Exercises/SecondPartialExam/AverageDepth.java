/*
Во следната задача треба да изградите бинарно дрво со N јазли,
за кое ќе треба да одговорите на Q прашања од видот
„колку e просечната длабочина на јазлите во поддрвото на избран јазол“.
Корените на поддрвото ќе ги сметаме на длабочина 1.
(Помош: просек се пресметува како сумата на длабочините на јазлите во поддрвото поделена со бројот на јазли во поддрвото.
Можете да користите две функции.)

Секој јазол ќе има уникатен индекс од 1 до N. Коренот на дрвото секогаш ќе има индекс 1.
Сите деца ќе имаат индекси поголеми од индексите на родителите.

Влезот ќе содржи N+Q редови од видот
- root 1 - Треба да го поставите коренот на дрвото да биде јазелот со индекс 1
- add parent_index child_index LEFT/RIGHT - Треба да додадете дете јазел со индекс child_index
  на јазелот со индекс parent_index на левата/десната страна.
- ask node_index - Треба да одговорите која е просечната длабочина на јазлите во поддрвото на јазелот со индекс node_index

Печатете ги одговорите како float.

/

In the following task you need to build a binary tree with N nodes,
for which you will need to answer Q questions of the type
"what is the average depth of the nodes in the subtree of a selected node".
We will consider the depth of the roots of the subtrees to be 1.
(Hint: the average is calculated as the sum divided by the number of members in the sum. You can use two functions.)

Each node will have a unique index from 1 to N. The root of the tree will always have an index 1.
All children will have indices greater than the parent indices.

The input will contain N+Q rows of the type
- root 1 - You need to set the root of the tree to be the node with index 1
- add parent_index child_index LEFT/RIGHT - You need to add a child node with index child_index
  to the node with index parent_index to its left/right side
- ask node_index - You need to answer what is the average depth of the nodes in the subtree of the node with index node_index

Print the answers as float.

Input:
11 5
root 1
add 1 2 LEFT
add 2 3 RIGHT
ask 1
add 1 4 RIGHT
add 2 5 LEFT
add 3 6 LEFT
ask 2
add 3 7 RIGHT
ask 1
add 4 8 LEFT
add 8 9 RIGHT
add 5 10 RIGHT
add 4 11 RIGHT
ask 2
ask 1

Result:
2.0
2.0
2.7142856
2.3333333
3.0
*/

package Exercises.SecondPartialExam;

import java.util.*;

import DataStructures.BNode;
import DataStructures.BTree;

public class AverageDepth {
    static BNode<Integer> findNode(BNode<Integer> node, int value) {
        if (node == null)
            return null;
        if (node.info == value)
            return node;
        BNode<Integer> left = findNode(node.left, value);
        if (left != null)
            return findNode(node.left, value);
        return findNode(node.right, value);
    }

    static float count(BNode<Integer> node) {
        if (node == null)
            return 0;
        return 1 + count(node.left) + count(node.right);
    }

    static float totalDepth(BNode<Integer> node, int depth) {
        if (node == null) {
            return 0;
        }
        float counter = depth;
        counter += totalDepth(node.left, depth + 1);
        counter += totalDepth(node.right, depth + 1);
        return counter;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int q = scanner.nextInt();
        scanner.nextLine();
        scanner.next();
        BTree<Integer> tree = new BTree<>();
        tree.makeRoot(scanner.nextInt());
        scanner.nextLine();
        for (int i = 1; i < n + q; i++) {
            String type = scanner.next();
            if (type.equals("add")) {
                int parent = scanner.nextInt();
                int child = scanner.nextInt();
                String side = scanner.next();
                if (side.equals("LEFT"))
                    tree.addChild(findNode(tree.root, parent), BNode.LEFT, child);
                else
                    tree.addChild(findNode(tree.root, parent), BNode.RIGHT, child);
            } else {
                int value = scanner.nextInt();
                BNode<Integer> root = findNode(tree.root, value);
                System.out.println(totalDepth(root, 1) / count(root));
            }
        }
    }
}