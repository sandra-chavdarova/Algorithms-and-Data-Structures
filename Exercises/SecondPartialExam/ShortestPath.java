//package Exercises.SecondPartialExam;
//
//import java.util.*;
//
//interface Stack<E> {
//    // Elementi na stekot se objekti od proizvolen tip.
//
//    // Metodi za pristap:
//
//    public boolean isEmpty();
//    // Vrakja true ako i samo ako stekot e prazen.
//
//    public E peek();
//    // Go vrakja elementot na vrvot od stekot.
//
//    // Metodi za transformacija:
//
//    public void clear();
//    // Go prazni stekot.
//
//    public void push(E x);
//    // Go dodava x na vrvot na stekot.
//
//    public E pop();
//    // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
//}
//
//class ArrayStack<E> implements Stack<E> {
//    private E[] elems;
//    private int depth;
//
//    @SuppressWarnings("unchecked")
//    public ArrayStack(int maxDepth) {
//        // Konstrukcija na nov, prazen stek.
//        elems = (E[]) new Object[maxDepth];
//        depth = 0;
//    }
//
//    public boolean isEmpty() {
//        // Vrakja true ako i samo ako stekot e prazen.
//        return (depth == 0);
//    }
//
//    public E peek() {
//        // Go vrakja elementot na vrvot od stekot.
//        if (depth == 0)
//            throw new NoSuchElementException();
//        return elems[depth - 1];
//    }
//
//    public void clear() {
//        // Go prazni stekot.
//        for (int i = 0; i < depth; i++) elems[i] = null;
//        depth = 0;
//    }
//
//    public void push(E x) {
//        // Go dodava x na vrvot na stekot.
//        elems[depth++] = x;
//    }
//
//    public E pop() {
//        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
//        if (depth == 0)
//            throw new NoSuchElementException();
//        E topmost = elems[--depth];
//        elems[depth] = null;
//        return topmost;
//    }
//}
//
//class BNode<E> {
//    public E info;
//    public BNode<E> left;
//    public BNode<E> right;
//
//    static int LEFT = 1;
//    static int RIGHT = 2;
//
//    public BNode<E> parent;
//
//    public BNode(E info, BNode<E> parent) {
//        this.parent = parent;
//        this.info = info;
//        left = null;
//        right = null;
//    }
//
//    public BNode(E info) {
//        this.parent = null;
//        this.info = info;
//        left = null;
//        right = null;
//    }
//
//    public BNode(E info, BNode<E> left, BNode<E> right) {
//        this.info = info;
//        this.left = left;
//        this.right = right;
//    }
//}
//
//class BTree<E> {
//    public BNode<E> root;
//
//    public BTree() {
//        root = null;
//    }
//
//    public BTree(E info) {
//        root = new BNode<E>(info);
//    }
//
//    public void makeRoot(E elem) {
//        root = new BNode<E>(elem);
//    }
//
//    public BNode<E> addChild(BNode<E> node, int where, E elem) {
//
//        BNode<E> tmp = new BNode<E>(elem, node);
//
//        if (where == BNode.LEFT) {
//            if (node.left != null)  // Left child already exists
//                return null;
//            node.left = tmp;
//        } else {
//            if (node.right != null) // Right child already exists
//                return null;
//            node.right = tmp;
//        }
//
//        return tmp;
//    }
//
//    public void inorder() {
//        System.out.print("INORDER: ");
//        inorderR(root);
//        System.out.println();
//    }
//
//    public void inorderR(BNode<E> n) {
//        if (n != null) {
//            inorderR(n.left);
//            System.out.print(n.info.toString() + " ");
//            inorderR(n.right);
//        }
//    }
//
//    public void preorder() {
//        System.out.print("PREORDER: ");
//        preorderR(root);
//        System.out.println();
//    }
//
//    public void preorderR(BNode<E> n) {
//        if (n != null) {
//            System.out.print(n.info.toString() + " ");
//            preorderR(n.left);
//            preorderR(n.right);
//        }
//    }
//
//    public void postorder() {
//        System.out.print("POSTORDER: ");
//        postorderR(root);
//        System.out.println();
//    }
//
//    public void postorderR(BNode<E> n) {
//        if (n != null) {
//            postorderR(n.left);
//            postorderR(n.right);
//            System.out.print(n.info.toString() + " ");
//        }
//    }
//
//    public void inorderNonRecursive() {
//        ArrayStack<BNode<E>> s = new ArrayStack<BNode<E>>(100);
//        BNode<E> p = root;
//        System.out.print("INORDER (nonrecursive): ");
//
//        while (true) {
//            // going to the most-left element while all roots of the subtrees
//            // are being added to a stack for later processing
//            // pridvizuvanje do kraj vo leva nasoka pri sto site koreni
//            // na potstebla se dodavaat vo magacin za podocnezna obrabotka
//            while (p != null) {
//                s.push(p);
//                p = p.left;
//            }
//
//            // if stack is empty the subtree has been iterated
//            // ako magacinot e prazen znaci deka stebloto e celosno izminato
//            if (s.isEmpty())
//                break;
//
//            p = s.peek();
//            // priting the node on top of the stack
//            // pecatenje (obrabotka) na jazelot na vrvot od magacinot
//            System.out.print(p.info.toString() + " ");
//            // deleting the top node of the stack
//            // brisenje na obraboteniot jazel od magacinot
//            s.pop();
//            // move on the right of the processed node and repeat the process
//            // for the right subtree of the node
//            // pridvizuvanje vo desno od obraboteniot jazel i povtoruvanje na
//            // postapkata za desnoto potsteblo na jazelot
//            p = p.right;
//
//        }
//        System.out.println();
//
//    }
//
//    int insideNodesR(BNode<E> node) {
//        if (node == null)
//            return 0;
//
//        if ((node.left == null) && (node.right == null))
//            return 0;
//
//        return insideNodesR(node.left) + insideNodesR(node.right) + 1;
//    }
//
//    public int insideNodes() {
//        return insideNodesR(root);
//    }
//
//    int leavesR(BNode<E> node) {
//        if (node != null) {
//            if ((node.left == null) && (node.right == null))
//                return 1;
//            else
//                return (leavesR(node.left) + leavesR(node.right));
//        } else {
//            return 0;
//        }
//    }
//
//    public int leaves() {
//        return leavesR(root);
//    }
//
//    int depthR(BNode<E> node) {
//        if (node == null)
//            return 0;
//        if ((node.left == null) && (node.right == null))
//            return 0;
//        return (1 + Math.max(depthR(node.left), depthR(node.right)));
//    }
//
//    public int depth() {
//        return depthR(root);
//    }
//
//    void mirrorR(BNode<E> node) {
//        BNode<E> tmp;
//
//        if (node == null)
//            return;
//
//        // symmetrical mirror of left and right subtrees
//        // simetricno preslikuvanje na levoto i desnoto potsteblo
//        mirrorR(node.left);
//        mirrorR(node.right);
//
//        // swap left and right pointers on current node
//        // smena na ulogite na pokazuvacite na momentalniot jazel
//        tmp = node.left;
//        node.left = node.right;
//        node.right = tmp;
//
//    }
//
//    public void mirror() {
//        mirrorR(root);
//    }
//
//    public BNode<String> findNode(BNode<String> node, String value) {
//        if (node == null)
//            return null;
//        if (node.info.equals(value)) {
//            return node;
//        }
//        BNode<String> left = findNode(node.left, value);
//        if (left != null)
//            return left;
//        return findNode(node.right, value);
//    }
//
//    public int shortestPathWeight(BNode<String> start, BNode<String> end) {
//        if (start == null) {
//            return -1;
//        }
//        Queue<BNode<String>> queue = new LinkedList<>();
//        Map<BNode<String>, Integer> distance = new HashMap<>();
//        queue.add(start);
//        distance.put(start, 0);
//        while (!queue.isEmpty()) {
//            BNode<String> current = queue.poll();
//            if (current == end)
//                return distance.get(current);
//            if (current.left != null && !distance.containsKey(current.left)) {
//                queue.add(current.left);
//                distance.put(current.left, distance.get(current) + 1);
//            }
//            if (current.right != null && !distance.containsKey(current.right)) {
//                queue.add(current.right);
//                distance.put(current.right, distance.get(current) + 1);
//            }
//            if (current.parent != null && !distance.containsKey(current.parent)) {
//                queue.add(current.parent);
//                distance.put(current.parent, distance.get(current) + 1);
//            }
//        }
//        return -1;
//    }
//}
//
//public class ShortestPath {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        BTree<String> tree = new BTree<>();
//        Map<Integer, BNode<String>> map = new HashMap<>();
//        int position = scanner.nextInt();
//        String value = scanner.next();
//        String type = scanner.next();
//        scanner.nextLine();
//        tree.makeRoot(value);
//        BNode<String> node = tree.findNode(tree.root, value);
//        map.put(position, node);
//        for (int i = 1; i < n; i++) {
//            int index = scanner.nextInt();
//            value = scanner.next();
//            type = scanner.next();
//            position = scanner.nextInt();
//            if (type.equals("LEFT")) {
//                tree.addChild(map.get(position), BNode.LEFT, value);
//            } else {
//                tree.addChild(map.get(position), BNode.RIGHT, value);
//            }
//            map.put(index, tree.findNode(tree.root, value));
//        }
//        int m = scanner.nextInt();
//        for (int i = 0; i < m; i++) {
//            String start = scanner.next();
//            String end = scanner.next();
//            int result = tree.shortestPathWeight(tree.findNode(tree.root, start), tree.findNode(tree.root, end));
//            if (result == -1)
//                System.out.println("ERROR");
//            else {
//                System.out.println(result * 2);
//            }
//        }
//    }
//}
