/*
Дадено ви е бинарно пребарувачко дрво со N цели броеви.
Напишете функција со која за бинарно пребарувачко дрво и избран елемент Т од дрвото:
- ќе најдете колку елементи во дрвото се поголеми од T
Ќе треба да ја искористите таа функција Q пати при градењето на дрвото.
Влезот ќе содржи N+Q редови од видот
- insert value - Треба да ја вметнете вредноста value во дрвото.
- ask value - Треба да одговорите колку елементи во дрвото имаат вредност поголема од value.

/

You are given a binary search tree with N integers.
Write a function that, for a binary search tree and a selected element T from the tree will:
- you will find how many elements in the tree are greater than T
You will need to use that function Q times when building the tree.
The input will contain N+Q rows of the type
- insert value - You need to insert the value value into the tree.
- ask value- You need to answer how many elements in the tree have a value greater than value

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
2
1
5
0
2
6
5
*/

package Labs.Lab08_BinarySearchTrees;

import java.util.Scanner;

import DataStructures.BNode;
import DataStructures.BinarySearchTree;

public class CountGreaterElements {
    public static int count(BNode<Integer> node, int value) {
        if (node == null)
            return 0;
        if (node.info > value)
            return 1 + count(node.left, value) + count(node.right, value);
        return count(node.right, value);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int q = scanner.nextInt();
        scanner.nextLine();
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i = 0; i < n + q; i++) {
            String type = scanner.next();
            int value = scanner.nextInt();
            scanner.nextLine();
            if (type.equals("insert")) {
                tree.insert(value);
            } else {
                System.out.println(count(tree.getRoot(), value));
            }
        }
    }
}

