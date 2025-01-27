/*
Испитна сесија
Додека Стефан ги подготвува испитите за полагање во јунска сесија, тој има навика да ги чува сите книги на еден куп, една врз друга.
При пребарување на дадена книга која му е потребна, тој секогаш ги трга прво најгорните,
една по една, се додека не ја земе книгата која му треба.
Штом ќе ја извади таа книга, останатите кои биле над неа ги враќа во истиот редослед назад.
Откако ќе го научи дадениот предмет, ја враќа книгата одозгора врз сите други.
Дадена е иницијалната поставеност на книгите на купот на Стефан (во редослед одоздола нагоре).
Дадени се и испитите кои треба да се полагаат по распоред за јунска сесија.
Ваша задача е да одредите колку пати секоја од книгите ќе биде извадена и ставена назад на купот.

Влез:
Во првата линија од влезот се дадени два броја: M, број на книги и N, број на испити.
Во втората линија од влезот се дадени имињата на книгите, подредени одоздола нагоре.
Во третата линија од влезот се дадени испитите кои се полагаат по редослед.

Излез
За секоја книга да се испечати колку пати ќе биде земена и вратена назад на купот
(еден „настан“ на земање-враќање на книгата се брои еднаш, не два пати).
Имињата на книгите се печатат во истиот редослед во кој биле дадени на влезот.

/

Exam Session
While studying for the exams in the June session, Stefan keeps his books ordered on a shelf, one on top of another.
When an exam is coming, he searches for a book, always starting from the top until he finds the book he searched.
After finishing studying for that exam, he puts that book back on top of the shelf.
Given the initial order of the books on the shelf and the exams in the order they are taken,
your task is to count the number of times every book will be removed and put back on the shelf.

Input:
The first line of the input contains two numbers: M - the number of books, and N - the number of exams.
The second line contains the names of the books on the shelf, from bottom to top.
The third line contains the exams in the order they are taken.

Output:
For each book, we need to print the number of times it will be taken/put back on the shelf
(a single take-return event is counted once, not twice).
The names of the books are printed in the same order they appeared in the input.

Explanation:
To get the APS book, we need to remove all the other books first.
Then we put them back to the shelf, and at the end, we put the APS book on top.
Next, we need to take the Objektno book, and to get it, we need to remove the APS, Calculus, and Strukturno books.
We put them back, with the Objektno book on top.
At the end, to get the Mrezhi book, we need to remove all the other books except for OS.
We put them back, with the Mrezhi on top at the end.

Note: Duplicate book titles will not be present.
One exam may appear multiple times. The book names must be printed in the same order they appeared in input.

Input:
7 3
APS OS Mrezhi AOK Objektno Strukturno Kalkulus
APS Objektno Mrezhi

Result:
APS 3
OS 1
Mrezhi 2
AOK 2
Objektno 3
Strukturno 3
Kalkulus 3
*/

package Exercises.FirstPartialExam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DataStructures.LinkedStack;

public class ExamSession {
    static class Book {
        String name;
        int count;

        public Book(String name, int count) {
            this.name = name;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();
        LinkedStack<Book> books = new LinkedStack<>();
        List<String> list = new ArrayList<>();
        List<String> inOrder = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            Book book = new Book(name, 0);
            books.push(book);
            inOrder.add(name);
        }
        for (int i = 0; i < m; i++) {
            list.add(scanner.next());
        }
        for (int i = 0; i < m; i++) {
            String name = list.get(i);
            LinkedStack<Book> takenOut = new LinkedStack<>();
            while (!books.peek().name.equals(name)) {
                takenOut.push(books.pop());
                takenOut.peek().count++;
            }
            Book chosen = books.pop();
            chosen.count++;
            while (!takenOut.isEmpty()) {
                books.push(takenOut.pop());
            }
            books.push(chosen);
        }

        List<Book> counted = new ArrayList<>();
        while (!books.isEmpty()) {
            counted.add(books.pop());
        }
        for (String name : inOrder) {
            for (Book book : counted) {
                if (name.equals(book.name)) {
                    System.out.println(book.name + " " + book.count);
                    break;
                }
            }
        }
    }
}
