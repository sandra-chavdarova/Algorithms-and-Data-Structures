/*
Да се напише алгоритам кој ќе пресметува (евалуира) математички израз составен
од броеви и операциите за собирање (+) и множење (*).

Забелешка: Операцијата множење има предност пред операцијата собирање.

/

Write an algorithm that will calculate (evaluate) a mathematical expression
hat consists of numbers and operations for adding (*) and multiplying (*).

Note: The operation of multiplying has precedence before the operation of adding.

Input:
2+2*2*2*2*2*2+2*2


Result:
70
*/

package Exercises.FirstPartialExam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

interface Stack<E> {
    // Elementi na stekot se objekti od proizvolen tip.
    // Metodi za pristap:

    public boolean isEmpty();
    // Vrakja true ako i samo ako stekot e prazen.

    public E peek();
    // Go vrakja elementot na vrvot od stekot.

    // Metodi za transformacija:
    public void clear();
    // Go prazni stekot.

    public void push(E x);
    // Go dodava x na vrvot na stekot.

    public E pop();
    // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
}

class ArrayStack<E> implements Stack<E> {
    private E[] elems; //elems[0...depth-1] se negovite elementi.
    private int depth; //depth e dlabochinata na stekot

    @SuppressWarnings("unchecked")
    public ArrayStack(int maxDepth) {
        // Konstrukcija na nov, prazen stek.
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }

    public boolean isEmpty() {
        // Vrakja true ako i samo ako stekot e prazen.
        return (depth == 0);
    }

    public E peek() {
        // Go vrakja elementot na vrvot od stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth - 1];
    }

    public void clear() {
        // Go prazni stekot.
        for (int i = 0; i < depth; i++) elems[i] = null;
        depth = 0;
    }

    public void push(E x) {
        // Go dodava x na vrvot na stekot.
        elems[depth++] = x;
    }

    public int size() {
        // Ja vrakja dolzinata na stack-ot.
        return depth;
    }

    public E pop() {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}

public class ExpressionEvaluator {
    public static int evaluateExpression(String expression) {
        String[] numbers = expression.split("[+*]");
        Stack<Integer> additions = new ArrayStack<>(expression.length());
        additions.push(Integer.parseInt(numbers[0]));
        int result = 0, counter = 1;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '+') {
                additions.push(Integer.parseInt(numbers[counter++]));
            }
            if (expression.charAt(i) == '*') {
                additions.push(Integer.parseInt(numbers[counter++]));
                int operand1 = additions.pop();
                int operand2 = additions.pop();
                additions.push(operand1 * operand2);
            }
        }
        while (!additions.isEmpty()) {
            result += additions.pop();
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(evaluateExpression(input.readLine()));
    }
}
