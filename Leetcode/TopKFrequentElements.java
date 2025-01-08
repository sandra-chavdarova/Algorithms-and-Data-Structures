/*
Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

Link: https://leetcode.com/problems/top-k-frequent-elements/description/?envType=problem-list-v2&envId=hash-table
*/

package Leetcode;

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }
}

class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        // Construct an empty Auditoriski.SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int size() {
        int listSize = 0;
        SLLNode<E> temp = first;
        while (temp != null) {
            listSize++;
            temp = temp.succ;
        }
        return listSize;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            SLLNode<E> temp = first;
            ret += temp.element;
            while (temp.succ != null) {
                temp = temp.succ;
                ret += " -> " + temp.element;
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, null);
        ins.succ = first;
        // Auditoriski.SLLNode<E> ins = new Auditoriski.SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }

    public void insertBefore(E o, SLLNode<E> before) {

        if (first != null) {
            SLLNode<E> temp = first;
            if (first == before) {
                this.insertFirst(o);
                return;
            }
            // ako first!=before
            while (temp.succ != before && temp.succ != null)
                temp = temp.succ;
            if (temp.succ == before) {
                temp.succ = new SLLNode<E>(o, before);
                ;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> temp = first;
            while (temp.succ != null)
                temp = temp.succ;
            temp.succ = new SLLNode<E>(o, null);
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> temp = first;
            first = first.succ;
            return temp.element;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> temp = first;
            if (first == node) {
                return this.deleteFirst();
            }
            while (temp.succ != node && temp.succ.succ != null)
                temp = temp.succ;
            if (temp.succ == node) {
                temp.succ = temp.succ.succ;
                return node.element;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }
        } else {
            System.out.println("Listata e prazna");
            return null;
        }

    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> temp = first;
            while (!temp.element.equals(o) && temp.succ != null)
                temp = temp.succ;
            if (temp.element.equals(o)) {
                return temp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return null;
    }

    public void merge(SLL<E> in) {
        if (first != null) {
            SLLNode<E> temp = first;
            while (temp.succ != null)
                temp = temp.succ;
            temp.succ = in.getFirst();
        } else {
            first = in.getFirst();
        }
    }

    public void mirror() {
        if (first != null) {
            // m = nextsucc, p = temp, q = next
            SLLNode<E> temp = first;
            SLLNode<E> newsucc = null;
            SLLNode<E> next;

            while (temp != null) {
                next = temp.succ;
                temp.succ = newsucc;
                newsucc = temp;
                temp = next;
            }
            first = newsucc;
        }
    }
}


class MapEntry<K extends Comparable<K>, E> {
    // Each MapEntry object is a pair consisting of a key (a Comparable object)
    // and a value (an arbitrary object).
    K key;
    E value;

    public MapEntry(K key, E val) {
        this.key = key;
        this.value = val;
    }

    public String toString() {
        return "<" + key + "," + value + ">";
    }
}

class CBHT<K extends Comparable<K>, E> {

    // An object of class CBHT is a closed-bucket hash table, containing
    // entries of class MapEntry.
    private SLLNode<MapEntry<K, E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        // Construct an empty CBHT with m buckets.
        buckets = (SLLNode<MapEntry<K, E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        // Translate key to an index of the array buckets.
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K, E>> search(K targetKey) {
        // Find which if any node of this CBHT contains an entry whose key is equal to targetKey.
        // Return a link to that node (or null if there is none).
        int b = hash(targetKey);
        SLLNode<MapEntry<K, E>> currNode = buckets[b];
        while (currNode != null) {
            MapEntry<K, E> currEntry = currNode.element;
            if (currEntry.key.equals(targetKey)) return currNode;
            else currNode = currNode.succ;
        }
        return null;
    }

    public void insert(K key, E val) {
        // Insert the entry <key, val> into this CBHT.
        // If entry with same key exists, overwrite it.
        MapEntry<K, E> newEntry = new MapEntry<>(key, val);
        int b = hash(key);
        SLLNode<MapEntry<K, E>> currNode = buckets[b];
        while (currNode != null) {
            MapEntry<K, E> currEntry = currNode.element;
            if (currEntry.key.equals(key)) {
                // Make newEntry replace the existing entry ...
                currNode.element = newEntry;
                return;
            } else currNode = currNode.succ;
        }
        // Insert newEntry at the front of the SLL in bucket b ...
        buckets[b] = new SLLNode<>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        // Delete the entry (if any) whose key is equal to key from this CBHT.
        int b = hash(key);
        SLLNode<MapEntry<K, E>> predNode = null, currNode = buckets[b];
        while (currNode != null) {
            MapEntry<K, E> currEntry = currNode.element;
            if (currEntry.key.equals(key)) {
                if (predNode == null) buckets[b] = currNode.succ;
                else predNode.succ = currNode.succ;
                return;
            } else {
                predNode = currNode;
                currNode = currNode.succ;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            SLLNode<MapEntry<K, E>> curr = buckets[i];
            while (curr != null) {
                temp += curr.element.toString() + " ";
                curr = curr.succ;
            }
            temp += "\n";
        }
        return temp;
    }
}

public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        CBHT<Integer, Integer> table = new CBHT<>(nums.length);
        int[] newNums = new int[nums.length];
        int p = 0;
        for (int i = 0; i < nums.length; i++) {
            if (table.search(nums[i]) == null) {
                table.insert(nums[i], 1);
                newNums[p] = nums[i];
                p++;
            } else {
                table.search(nums[i]).element.value++;
            }
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            int maxValue = -1, maxKey = -1;
            for (int j = 0; j < p; j++) {
                SLLNode<MapEntry<Integer, Integer>> node = table.search(newNums[j]);
                if (node != null && node.element.value >= maxValue) {
                    maxKey = node.element.key;
                    maxValue = node.element.value;
                }
            }
            result[i] = maxKey;
            table.delete(maxKey);
        }
        return result;
    }
}