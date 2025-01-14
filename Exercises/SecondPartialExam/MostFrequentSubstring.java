package Exercises.SecondPartialExam;

import java.io.IOException;
import java.util.Scanner;

class MapEntry<K extends Comparable<K>, E> implements Comparable<K> {

    K key;
    E value;

    public MapEntry(K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo(K that) {
        @SuppressWarnings("unchecked")
        MapEntry<K, E> other = (MapEntry<K, E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString() {
        return "(" + key + "," + value + ")";
    }
}

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class CBHT<K extends Comparable<K>, E> {

    private SLLNode<MapEntry<K, E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        buckets = (SLLNode<MapEntry<K, E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K, E>> search(K targetKey) {
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K, E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) {        // Insert the entry <key, val> into this CBHT.
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K, E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                curr.element = newEntry;
                return;
            }
        }
        buckets[b] = new SLLNode<MapEntry<K, E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        int b = hash(key);
        for (SLLNode<MapEntry<K, E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                if (pred == null)
                    buckets[b] = curr.succ;
                else
                    pred.succ = curr.succ;
                return;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            for (SLLNode<MapEntry<K, E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                temp += curr.element.toString() + " ";
            }
            temp += "\n";
        }
        return temp;
    }

    public static String mostFrequentSubstring(CBHT<String, Integer> table) {
        String result = "";
        int max = 0;
        for (int i = 0; i < table.buckets.length; i++) {
            SLLNode<MapEntry<String, Integer>> entry = table.buckets[i];
            while (entry != null) {
                int occurence = entry.element.value;
                int length = entry.element.key.length();
                if (occurence > max) {
                    max = occurence;
                    result = entry.element.key;
                } else if (occurence == max && length > result.length()) {
                    result = entry.element.key;
                } else if (occurence == max && length == result.length() && result.compareTo(entry.element.key) > 0) {
                    result = entry.element.key;
                }
                entry = entry.succ;
            }
        }
        return result;
    }
}

public class MostFrequentSubstring {
    public static void main(String[] args) throws IOException {
        CBHT<String, Integer> table = new CBHT<String, Integer>(300);
        Scanner scanner = new Scanner(System.in);

        String word = scanner.nextLine();

        for (int i = 0; i < word.length(); i++) {
            for (int j = i + 1; j <= word.length(); j++) {
                String substring = word.substring(i, j);
                if (table.search(substring) == null) {
                    table.insert(substring, 1);
                } else {
                    table.search(substring).element.value++;
                }
            }
        }
        System.out.println(table.mostFrequentSubstring(table));
    }
}
