package Exercises;

import java.util.Scanner;

class QuarterlySales {

    private int numberOfSales;
    private int[] income;
    private int quarterNumber;

    public QuarterlySales(int numberOfSales, int[] income, int quarterNumber) {
        this.numberOfSales = numberOfSales;
        this.income = income;
        this.quarterNumber = quarterNumber;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(getTotalIncome()).append("   ");
        return string.toString();
    }

    public int getTotalIncome() {
        int total = 0;
        for (int i = 0; i < numberOfSales; i++) {
            total += income[i];
        }
        return total;
    }

    public int[] getIncome() {
        return income;
    }

    public int getNumberOfSales() {
        return numberOfSales;
    }

    public int getQuarterNumber() {
        return quarterNumber;
    }
}

class SalesPerson {

    private String name;
    private QuarterlySales[] quarters;

    public SalesPerson(String name, QuarterlySales[] quarters) {
        this.name = name;
        this.quarters = quarters;
    }

    public QuarterlySales[] getQuarters() {
        return quarters;
    }

    public int getTotalIncome() {
        int total = 0;
        for (int i = 0; i < quarters.length; i++) {
            total += quarters[i].getTotalIncome();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(this.name).append("   ");
        int total = 0;
        for (int i = 0; i < quarters.length; i++) {
            string.append(quarters[i]);
            total += quarters[i].getTotalIncome();
        }
        string.append(total);
        return string.toString();
    }

    public String getName() {
        return name;
    }
}


public class Main {

    public static SalesPerson salesChampion(SalesPerson[] arr) {
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getTotalIncome() > arr[index].getTotalIncome()) {
                index = i;
            }
        }
        return arr[index];
    }

    public static int sumSales(SalesPerson person) {
        int sum = 0;
        QuarterlySales[] quarterlySales = person.getQuarters();
        for (int i = 0; i < quarterlySales.length; i++) {
            int sale = 0;
            for (int j = 0; j < quarterlySales[i].getNumberOfSales(); j++) {
                sale += quarterlySales[i].getIncome()[j];
            }
            sum += sale;
        }
        return sum;
    }

    public static void table(SalesPerson[] arr) {
        System.out.print("SP   1   2   3   4   Total\n");
        for (SalesPerson salesPerson : arr) {
            System.out.println(salesPerson);
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {

        int n;
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        input.nextLine();
        SalesPerson[] arr = new SalesPerson[n];
        for (int i = 0; i < n; i++) {
            String name = input.nextLine();
            QuarterlySales[] quarterlySales = new QuarterlySales[4];
            for (int j = 0; j < 4; j++) {
                int sales = input.nextInt();
                int[] income = new int[sales];
                for (int k = 0; k < sales; k++) {
                    income[k] = input.nextInt();
                }
                quarterlySales[j] = new QuarterlySales(sales, income, j);
            }
            input.nextLine();

            arr[i] = new SalesPerson(name, quarterlySales);
        }

        table(arr);
        System.out.println("SALES CHAMPION: " + salesChampion(arr).getName());

    }
}