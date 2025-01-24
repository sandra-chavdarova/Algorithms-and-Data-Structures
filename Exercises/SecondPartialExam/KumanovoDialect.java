/*
Даден ви е речник на зборови на кумановски дијалект и како тие се пишуваат на македонски јазик.
Потоа даден ви е текст којшто е напишан на кумановски дијалект.
Потребно е да ги замените сите појавувања на зборовите на кумановскиот дијалект
кои се дадени во речникот со соодветни зборови на македонски јазик.

Забелешка: Треба да се игнорираат интерпункциските знаци точка (.), запирка (,), извичник(!) и прашалник (?).
Исто така зборовите во текстот можат да се појават и со прва голема буква
и во тој случај неговиот синоним на македонски јазик исто така треба да се отпечати со прва голема буква.

/

You are given a dictionary of words in Kumanovo's dialect, and how they are written in formal macedonian language.
Then, you are given a text that is written in the dialect.
You need to replace all occurrences of the words in the dialect that are given in the dictionary,
with the corresponding words in the formal language.

Note: You need to ignore punctuation marks dot(.), comma(,), exclamation mark(!) and question mark(?).
Also, the words might appear with a capital first letter in the text
and in that case the synonym in the formal macedonian language should also be printed capitalized.

Input:
20
nego otkolku
pesmu pesna
bija bil
u vo
s’s so
zhenu zhena
ubavu ubava
sakaja sakal
ednu edna
poznatu poznata
pesmu pesna
umreja umrel
sliku slika
zelje zelbi
rakiju rakija
ede jade
skup skap
chasku chaska
povishke povekje
narodnu narodna
Batko Gjorgjija e tipichna figura i karakter od Kumanovo, koj bija golem majtapdzija i boem i koj povishke sakaja kjef da tera nego da raboti. U ednu poznatu narodnu pesmu vika se deka umreja s’s tri zelje za ubavu zhenu, za chasku rakiju i za skup pajton.

Result:
Batko Gjorgjija e tipichna figura i karakter od Kumanovo, koj bil golem majtapdzija i boem i koj povekje sakal kjef da tera otkolku da raboti. Vo edna poznata narodna pesna vika se deka umrel so tri zelbi za ubava zhena, za chaska rakija i za skap pajton.
*/

package Exercises.SecondPartialExam;

import java.util.Scanner;

import DataStructures.MapEntry;
import DataStructures.CBHT;
import DataStructures.SLLNode;

public class KumanovoDialect {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        CBHT<String, String> dictionary = new CBHT<>(n + 1);
        for (int i = 0; i < n; i++) {
            dictionary.insert(scanner.next(), scanner.next());
            if (i == n - 1) // for the last test case...
                scanner.nextLine();
        }

        String text = scanner.nextLine();
        String[] words = text.split(" ");
        for (String word : words) {
            char lastCharacter = word.charAt(word.length() - 1);
            char firstCharacter = word.charAt(0);
            if (!Character.isLetter(lastCharacter)) {
                word = word.substring(0, word.length() - 1);
            }
            SLLNode<MapEntry<String, String>> mapEntry = dictionary.search(word.toLowerCase());
            if (mapEntry != null) {
                String newWord = mapEntry.element.value;
                if (Character.isUpperCase(firstCharacter))
                    newWord = newWord.substring(0, 1).toUpperCase() + newWord.substring(1);
                if (!Character.isLetter(lastCharacter)) {
                    newWord += lastCharacter;
                }
                System.out.print(newWord + " ");
            } else {
                if (!Character.isLetter(lastCharacter))
                    word += lastCharacter;
                System.out.print(word + " ");
            }
        }
    }
}
