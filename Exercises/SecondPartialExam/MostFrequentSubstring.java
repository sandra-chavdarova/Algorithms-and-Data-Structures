package Exercises.SecondPartialExam;

import java.io.IOException;
import java.util.Scanner;

import DataStructures.MapEntry;
import DataStructures.CBHT;
import DataStructures.SLLNode;

public class MostFrequentSubstring {
    static String mostFrequentSubstring(CBHT<String, Integer> table) {
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
        System.out.println(mostFrequentSubstring(table));
    }
}
