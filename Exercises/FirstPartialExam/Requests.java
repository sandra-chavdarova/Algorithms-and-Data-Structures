/*
Наредени луѓе со број на барања, им се извршува едно барање и се враќаат назад во редот.
Печати редослед на завршување на луѓето.

Input:
5
Nenad 3
Slave 1
Martin 2
Ana 1
Igor 2

Result:
Slave
Ana
Martin
Igor
Nenad
*/

package Exercises.FirstPartialExam;

import java.util.*;

import DataStructures.LinkedQueue;

public class Requests {
    static class Person {
        String name;
        int requests;

        public Person(String name, int requests) {
            this.name = name;
            this.requests = requests;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        LinkedQueue<Person> people = new LinkedQueue<>();
        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            int requests = scanner.nextInt();
            Person person = new Person(name, requests);
            people.enqueue(person);
            scanner.nextLine();
        }
        while (!people.isEmpty()) {
            Person person = people.dequeue();
            if (person.requests == 1) {
                System.out.println(person.name);
            } else {
                person.requests--;
                people.enqueue(person);
            }
        }
    }
}
