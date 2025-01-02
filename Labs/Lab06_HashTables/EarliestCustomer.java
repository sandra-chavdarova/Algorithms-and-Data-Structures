/*
На влез во оваа задача ќе ви бидат дадени редови како следниот формат:
Ime Prezime budzhet ip_adresa vreme grad cena
Пример
Jovan Todorov 1000 10.73.112.200 15:30 Bitola 760
Кое што значи дека лицето со Име и Презиме Jovan Todorov,
има буџет од 1000 денари, има IP адреса со мрежа 10.73.112 и домаќин (host number) 200,
и се приклучил во 15:30 часот за да купи билет кон Bitola кој што чини 760 денари.

Ќе ви бидат дадени N такви редови, по што ќе следи празен ред па уште M редови од истиот формат, кои ќе ги користиме за тестирање.

Од редот за тестирање треба да го извадите градот и потоа да го одговорите следното прашање со овој град:

Од сите N лица на влез, кои купуваат билет за до истиот град,
колку од нив се вклучиле подоцна од 11:59;
и од овие, кој од нив се вклучил најрано?

(погледнете го тест примерот!)
(доколку има повеќе со исто најмало време тогаш кој е првиот таков во влезот?) (секогаш ќе постои барем еден таков)

Ова ќе треба да го направите за сите M редови за тестирање!
Препорака, користете OBHT и/или CBHT.
-----------------------------------------------------------------------------------------------
At the input of this task you will be given rows with the following format:
First_name Last_name budget ip_address time city price
Example
Jovan Todorov 1000 10.73.112.200 15:30 Bitola 760
Which means that the person with First name and Last name Jovan Todorov,
has a budget of 1000 denars, has an IP address with network 10.73.112 and host number 200,
and joined at 15:30 to buy a ticked to Bitola which costs 760 denars.

You will be given N such rows, followed by an empty row and M more rows of the same format, which we will use for testing.

From the test row you need to extract the city and then answer the following question with this city:

Of the N people at the input, from the ones who buy a ticket to the same city,
how many of them joined later than 11:59;
and of these, which one joined the earliest?

(if there are more with the same minimum time then which one is the first one in the entry?) (there will always be at least one)

You will need to do this for all M rows for testing!
Recommendation, use OBHT and/or CBHT.

Input:
5
Jovan Todorov    1000    10.73.112.200     16:30   Bitola     760
Mitko Janev      4350    132.28.112.200    12:15   Krusevo    4000
Sara Dobreva     2700    10.73.60.29       14:35   Bitola     2500
Mence Trajanova  4000    10.73.112.112     11:25   Bitola     4200
Viktor Jovev     2200    10.73.112.79      15:15   Strumica   1800

1
Jovan Todorov    1000    10.73.112.200     16:30   Bitola     760

Result:
City: Bitola has the following number of customers:
2
The user who logged on earliest after noon from that city is:
Sara Dobreva with salary 2700 from address 10.73.60.29 who logged in at 14:35
*/

package Labs.Lab06_HashTables;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

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

class Person {
    String name;
    String surname;
    Integer budget;
    String ip_address;
    String time;
    Integer hour;
    Integer minutes;
    String city;
    Integer price;

    public Person(String name, String surname, Integer budget, String ip_address, String time, Integer hour, Integer minutes, String city, Integer price) {
        this.name = name;
        this.surname = surname;
        this.budget = budget;
        this.ip_address = ip_address;
        this.time = time;
        this.hour = hour;
        this.minutes = minutes;
        this.city = city;
        this.price = price;
    }
}

public class EarliestCustomer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        CBHT<String, Person> tableCities = new CBHT<>(n);
        CBHT<String, Integer> earliest = new CBHT<>(n);
        for (int i = 0; i < n; i++) {

            String name = scanner.next();
            String surname = scanner.next();
            Integer budget = scanner.nextInt();
            String ip_address = scanner.next();
            String time = scanner.next();

            String[] split = time.split(":");

            Integer hour = parseInt(split[0]);
            Integer minutes = parseInt(split[1]);
            String city = scanner.next();
            Integer price = scanner.nextInt();

            scanner.nextLine();

            Person person = new Person(name, surname, budget, ip_address, time, hour, minutes, city, price);
            if (tableCities.search(person.city) == null) {
                if (person.hour >= 12) {
                    tableCities.insert(person.city, person);
                    earliest.insert(person.city, 1);
                }
            } else {
                if (person.hour >= 12) {
                    Person existingPerson = tableCities.search(person.city).element.value;
                    earliest.insert(person.city, earliest.search(person.city).element.value + 1);
                    if (existingPerson.hour > person.hour) {
                        tableCities.insert(person.city, person);

                    } else if (existingPerson.hour.equals(person.hour) && existingPerson.minutes > person.minutes) {
                        tableCities.insert(person.city, person);
                    }
                }
            }
        }

        int m = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            scanner.nextLine();
            String name = scanner.next();
            String surname = scanner.next();
            Integer budget = scanner.nextInt();
            String ip_address = scanner.next();
            String time = scanner.next();

            String[] split = time.split(":");

            Integer hour = parseInt(split[0]);
            Integer minutes = parseInt(split[1]);
            String city = scanner.next();
            Integer price = scanner.nextInt();

            System.out.println("City: " + city + " has the following number of customers:");
            Integer total = earliest.search(city).element.value;
            System.out.println(total);
            System.out.println("The user who logged on earliest after noon from that city is:");
            Person result = tableCities.search(city).element.value;

            System.out.println(result.name + " " + result.surname + " with salary " + result.budget + " from address " + result.ip_address + " who logged in at " + result.time);
            System.out.println();
        }
    }
}