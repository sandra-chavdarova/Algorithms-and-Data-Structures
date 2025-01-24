/*
Потребно е да се направи проверка на текст даден на англиски,
дали е во ред напишан (односно дали правилно се напишани зборовите).
За таа цел прво се даваат речник на зборови (односно листа на зборови кои ги содржи англискиот јазик),
а потоа се дава текст. Како резултат треба да се испечатат сите зборови кои се направилно напишани или ги нема во речникот.

Влез: Прво се дава број N на поими кои ќе ги содржи речникот,
а во наредните N реда се дадени зборовите кои ги содржи англискиот јазик.
Потоа се дава еден текст, кој треба да се провери дали е правилно напишан.

Излез: Се печати листа на зборови кои се неправилно напишани (секој во посебен ред).
Доколку сите зборови се добро напишани се печати: Bravo.

Забелешка: Треба да се игнорираат интерпункциски знаци како точка (.), запирка (,), извичник (!) и прашалник (?).
Исто така да се внимава на голема и мала буква, односно иако зборовите во речникот се со мали букви,
во реченица може да појават со голема почетна буква и притоа се сметаат за точни.
Работете со хеш табела со отворени кофички. Сами треба да го одредите бројот на кофички и хеш функцијата.

/

You need to do a spell check of a text written in English, if it's correctly written.
For that, first you are given a dictionary of words
(i.e. a list of all the words that are used), and then you are given a text.
As a result you should print all the words that are spelled incorrectly or are not present in the dictionary.

Input: First you are given an integer N - number of terms in the dictionary,
and then in the next N rows you have the words themselves.
Then you are given a text, which should be spell-checked.

Output: You should print a list of words that are written incorrectly (each in a separate row).
If all words are written correctly you should print: Bravo.

Note: You should ignore punctual marks such as dot(.), comma(,), exclamation mark(!) and question mark(?).
You should also take in consideration uppercase / lowercase letters,
i.e. if the words in the dictionary are with only lowercase letters,
in a sentence they can appear with a capitalized first letter,
and that should be considered as correct. Work with an Open bucket hash table.
You should determine the number of buckets and the hash function yourselves.

Input:
4
where
is
my
cat
Where is my cat?


Result:
Bravo
*/

package Exercises.SecondPartialExam;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import DataStructures.MapEntry;
import DataStructures.OBHT;
import DataStructures.SLLNode;

class Zbor implements Comparable<Zbor> {
    String zbor;

    public Zbor(String zbor) {
        this.zbor = zbor;
    }

    @Override
    public boolean equals(Object obj) {
        Zbor pom = (Zbor) obj;
        return this.zbor.equals(pom.zbor);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(zbor);
    }

    @Override
    public String toString() {
        return zbor;
    }

    @Override
    public int compareTo(Zbor arg0) {
        return zbor.compareTo(arg0.zbor);
    }
}

public class SpellCheck {
    static String[] checkSentence(String[] sentence, OBHT<Zbor, String> table) {
        String[] list = new String[sentence.length];
        int j = 0;
        for (int i = 0; i < sentence.length; i++) {
            String word = sentence[i].replaceAll("[.,!?]", "").toLowerCase();
//            System.out.println(word);
            if (word.isEmpty())
                return new String[0];
            if (table.search(new Zbor(word.toLowerCase())) == -1) {
                list[j] = word;
                j++;
            }
        }
        if (j != 0)
            return list;
        return new String[0];
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        OBHT<Zbor, String> tabela = new OBHT<Zbor, String>(n + 1);
        for (int i = 0; i < n; i++) {
            String word = scanner.nextLine();
            tabela.insert(new Zbor(word.toLowerCase()), word.toLowerCase());
        }
        String[] sentence = scanner.nextLine().split(" ");
        String[] result = checkSentence(sentence, tabela);
        if (result.length == 0) {
            System.out.println("Bravo");
        } else {
            for (int i = 0; i < result.length; i++)
                if (result[i] != null) {
                    System.out.println(result[i]);
                }
        }
//        System.out.println(tabela);
    }
}
