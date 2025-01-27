/*
Библиотека е посетена од студентите со цел да изнајмат еден или повеќе типови на книги.
Дадена книга може да припаѓа на една од трите категории: Наука, Научна фантастика или Историја.
Кога библиотеката ќе започне со работа се услужуваат студенти од сите три типа паралелно,
но исто така сите три шалтера не одат со иста брзина па услужувањето е со следниот редослед
(два студента што ги бараат книга од тип Наука, па еден студент што бара книга од тип Научна фантастика,
па два студенат што бараат книга од тип Историја).

Доколку студент чека ред за книги од различен тип, тој чека ред првин во редицата за книга од тип Наука,
потоа во редицата за книга од тип Научна фантастика и на крај во редицата за книга од тип Историја
(во зависност ако ги бара овие книги за позајмување).

Влез: Во првата линија е даден број на студенти кои имаат дојдено во библиотеката да позајмат книга.
Потоа 4 редици се внесуваат за секој студент, каде првата линија е име на студент,
а во останатите 3 редици се внесува дали студентот ќе позајми книга од даден тип (Наука, Научна фантастика и Историја соодветно),
каде 1 значи дека има за цел да позајми книга од тој тип, 0 значи дека нема да позајми книга од тој тип.

Пример:
Иван Ивановски
1
1
0
значи дека студентот Иван Ивановски има за цел да позајми книга од тип Наука и тип Научна фантастика,
но не има за цел да позајми книга од тип Историја.

Излез: Испечати го редоследот на студентите по редослед како завршуваат со позајмување на сите книги.

/

A library is visited by students in order to borrow one or more types of books.
A given book can belong to one of three categories: Science, Science Fiction, or History.
When the library opens, students of all three types are served in parallel,
but it is important to mention that all 3 counters go with different speeds,
so the serving of students is in this order (two students that borrow a book of type Science,
then one student who wants to borrow a book of type Science Fiction,
then two students who want to borrow a book of type History).

If a student is waiting in line for books of different types,
he waits in line first for a Science book, then for a Science Fiction book,
and finally for a History book (depending on whether he is requesting these books for borrowing).

Input: The first line contains the number of students who have come to the library to borrow a book.
Then 4 lines are entered for each student, where the first line is the student's name,
and the remaining 3 lines indicate whether the student will borrow a book of a given type
(Science, Science Fiction, and History, respectively),
where 1 means that he intends to borrow a book of that type,
0 means that he will not borrow a book of that type.

Example:
Ivan Ivanovski
1
1
0

means that student Ivan Ivanovski aims to borrow a book of type Science and type Science Fiction,
but does not aim to borrow a book of type History.

Output: Print the order of students in the order they finish borrowing all the books.

Input:
3
Иван Ивановски
1
0
1
Марија Маркова
0
1
1
Петар Петров
1
1
0

Result:
Иван Ивановски
Марија Маркова
Петар Петров
*/

package Labs.Lab05_StackAndQueue;

import java.util.ArrayList;
import java.util.Scanner;

import DataStructures.Queue;
import DataStructures.ArrayQueue;

public class LibraryParallel {
    static class Person {
        String name;
        int science, SciFi, history;

        public Person(String name, int science, int scifi, int history) {
            this.name = name;
            this.science = science;
            this.SciFi = scifi;
            this.history = history;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        Queue<Person> science = new ArrayQueue<>(n);
        Queue<Person> SciFi = new ArrayQueue<>(n);
        Queue<Person> history = new ArrayQueue<>(n);
        ArrayList<String> done = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String name = scanner.nextLine();
            int book1 = scanner.nextInt();
            int book2 = scanner.nextInt();
            int book3 = scanner.nextInt();
            scanner.nextLine();
            Person person = new Person(name, book1, book2, book3);
            if (book1 == 1) {
                science.enqueue(person);
            } else if (book2 == 1) {
                SciFi.enqueue(person);
            } else {
                history.enqueue(person);
            }
        }

        while (!science.isEmpty() || !SciFi.isEmpty() || !history.isEmpty()) {
            int counter1 = 2, counter2 = 1, counter3 = 2;
            if (!science.isEmpty()) {
                for (int i = 0; i < counter1; i++) {
                    Person person = science.dequeue();
                    person.science--;
                    if (person.science == 0 && person.SciFi == 1) {
                        SciFi.enqueue(person);
                    } else if (person.history == 1) {
                        history.enqueue(person);
                    } else {
                        done.add(person.name);
                    }
                    if (science.isEmpty())
                        break;
                }
            }

            if (!SciFi.isEmpty()) {
                for (int i = 0; i < counter2; i++) {
                    Person person = SciFi.dequeue();
                    person.SciFi--;
                    if (person.SciFi == 0 && person.history == 1) {
                        history.enqueue(person);
                    } else {
                        done.add(person.name);
                    }
                    if (SciFi.isEmpty())
                        break;
                }
            }

            if (!history.isEmpty()) {
                for (int i = 0; i < counter3; i++) {
                    Person person = history.dequeue();
                    person.history--;
                    done.add(person.name);
                    if (history.isEmpty())
                        break;
                }
            }
        }

        for (int i = 0; i < done.size(); i++) {
            System.out.println(done.get(i));
        }
    }
}