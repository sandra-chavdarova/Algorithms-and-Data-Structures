package Labs.Lab06_HashTables;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

class Person {
    String name;
    String surname;
    int budget;
    String IPaddress;
    String time;
    int hour;
    int minutes;
    String city;
    int price;
    String network;

    public Person(String name, String surname, int budget, String IPaddress, String time, int hour, int minutes, String city, int price, String network) {
        this.name = name;
        this.surname = surname;
        this.budget = budget;
        this.IPaddress = IPaddress;
        this.time = time;
        this.hour = hour;
        this.minutes = minutes;
        this.city = city;
        this.price = price;
        this.network = network;
    }
}

public class EarliestCustomerByIP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        CBHT<String, Person> table = new CBHT<>(n);
        CBHT<String, Integer> customers = new CBHT<>(n);
        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            String surname = scanner.next();
            int budget = scanner.nextInt();
            String IPaddress = scanner.next();
            String time = scanner.next();

            String[] split = time.split(":");
            int hour = Integer.parseInt(split[0]);
            int minutes = Integer.parseInt(split[1]);

            String city = scanner.next();
            int price = scanner.nextInt();
            scanner.nextLine();

            String[] networks = IPaddress.split("\\.");
            String network = String.join(".", networks[0], networks[1], networks[2]);

            Person person = new Person(name, surname, budget, IPaddress, time, hour, minutes, city, price, network);

            if (table.search(network) == null) {
                if (hour >= 12) {
                    table.insert(network, person);
                    customers.insert(network, 1);
                }
            } else {
                if (hour >= 12) {
                    customers.insert(network, customers.search(network).element.value + 1);
                    Person existing = table.search(network).element.value;
                    if (existing.hour > hour) {
                        table.insert(network, person);
                    } else if (existing.hour == hour && existing.minutes > minutes) {
                        table.insert(network, person);
                    }
                }
            }
        }

        int m = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < m; i++) {
            String name = scanner.next();
            String surname = scanner.next();
            int budget = scanner.nextInt();
            String IPaddress = scanner.next();
            String time = scanner.next();

            String[] split = time.split(":");
            int hour = Integer.parseInt(split[0]);
            int minutes = Integer.parseInt(split[1]);

            String city = scanner.next();
            int price = scanner.nextInt();
            scanner.nextLine();

            String[] networks = IPaddress.split("\\.");
            String network = String.join(".", networks[0], networks[1], networks[2]);

            Person person = new Person(name, surname, budget, IPaddress, time, hour, minutes, city, price, network);

            System.out.println("IP network: " + network + " has the following number of users:");
            System.out.println(customers.search(network).element.value);
            System.out.println("The user who logged on earliest after noon from that network is:");
            Person result = table.search(network).element.value;
            System.out.println(result.name + " " + result.surname + " with salary " + result.budget + " from address " + result.IPaddress + " who logged in at " + result.time);
            System.out.println();
        }
    }
}
