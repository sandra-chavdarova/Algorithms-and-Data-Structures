/*
Во оваа задача ќе работите со два објекти „Вработени“ и „Проекти“.
Еден вработен е дефиниран со 2 вредности: Име (String), Возраст (цел број).
Еден проект е дефиниран со 2 вредности: Работно Време (цел број), Плата по час (цел број).
За даден проект вкупната плата се пресметува како производ на работното време и платата по час.
Вработените ќе добиваат понудени проекти, и треба да го изберат проектот од кој ќе добијат
најголема плата (доколку имаат избор од повеќе проекти со иста најголема плата, тогаш првиот таков).

Ваша задача ќе биде да им помогнете на вработените со тоа што ќе искористите
CBHT со 10 кофички каде што за секој вработен ќе ја чувате неговата најдобра понуда.
За хеш функција користете производ од возраста на вработениот и ASCII вредноста
на првата буква од името на вработениот.

Влез:
Во првиот ред е даден еден цел број N кој го означува бројот на понуди.
Потоа во следните N редови се дадени по 4 вредности, информациите за понудите,
името и возраста на вработениот и работното време и платата од час на проектот соодветно:

N
name_1 age_1 time_1 rate_1
name_2 age_2 time_2 rate_2
...
name_N age_N time_N rate_N

Излез:
Отпечатете ја целата табела (со готовиот toString метод).
Секој вработен да се печати во формат "<name, age>" каде на местото на name и age
треба да стои името и возраста на вработениот соодветно (имплементирајте го ова во toString метод).
Секој проект да се печати во формат "<time, rate>" каде на местото на time и rate
треба да стои работното време и платата по час на проектот соодветно (имплементирајте го ова во toString метод).

-----------------------------------

In this task, you will work with two objects "Employees" and "Projects".
An employee is defined with 2 values: Name (String), Age (integer).
A project is defined with 2 values: Working Time (integer), Hourly Salary (integer).
For a given project, the total salary is calculated as the product of the working time and the hourly salary.
Employees will be offered projects, and they should choose the project
from which they will receive the highest salary (if they have a choice of multiple projects
with the same highest salary, then the first one).

Your task will be to help employees by using CBHT with 10 buckets where
for each employee you will store their best offer.
For the hash function, use the product of the employee's age
and the ASCII value of the first letter of the employee's name.

Input:
The first line contains an integer N that indicates the number of offers.
Then, in the next N rows, 4 values ​​are given, the information about the offers,
the name and age of the employee and the working hours and hourly wage of the project respectively:

N
name_1 age_1 time_1 rate_1
name_2 age_2 time_2 rate_2
...
name_N age_N time_N rate_N

Output:
Print the entire table (with the ready-made toString method).
Each employee should be printed in the format "<name, age>" where in place of name and age should be
the name and age of the employee respectively (implement this in the toString method).
Each project should be printed in the format "<time, rate>" where in place of time and rate should be
the working hours and hourly wage of the project respectively (implement this in the toString method).

Input:
5
Martin 25 3 100
Jana 26 4 90
Martin 25 5 120
Jana 26 2 95
Nenad 20 6 80

Result:
0:<<Nenad, 20>,<6, 80>>
1:
2:
3:
4:<<Jana, 26>,<4, 90>>
5:<<Martin, 25>,<5, 120>>
6:
7:
8:
9:
*/

package Labs.Lab6_HashTables;

import java.util.Objects;
import java.util.Scanner;

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
        // Construct an empty SLL
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
                ret += "->" + temp.element;
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, null);
        ins.succ = first;
        // SLLNode<E> ins = new SLLNode<E>(o, first);
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

class Person implements Comparable<Person> {
    String name;
    Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "<" + name + ", " + age + ">";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(age, person.age);
    }

    @Override
    public int hashCode() {
        return age * (name.charAt(0));
    }

    @Override
    public int compareTo(Person o) {
        return 0;
    }
}

class Project {
    Integer time;
    Integer fee;

    public Project(Integer time, Integer fee) {
        this.time = time;
        this.fee = fee;
    }

    public int totalFee() {
        return time * fee;
    }

    @Override
    public String toString() {
        return "<" + time + ", " + fee + ">";
    }
}

public class BestProjectOffer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        CBHT<Person, Project> table = new CBHT<Person, Project>(10);
        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            Integer age = scanner.nextInt();
            Person person = new Person(name, age);
            Integer time = scanner.nextInt();
            Integer fee = scanner.nextInt();
            Project project = new Project(time, fee);
            if (table.search(person) == null) {
                table.insert(person, project);
            } else {
                SLLNode<MapEntry<Person, Project>> newOffer = table.search(person);
                Project best = newOffer.element.value;
                if (project.totalFee() > best.totalFee()) {
                    table.insert(person, project);
                }
            }
        }
        System.out.println(table);
    }
}
