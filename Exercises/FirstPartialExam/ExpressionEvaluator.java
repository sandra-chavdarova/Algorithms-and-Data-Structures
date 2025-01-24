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

import DataStructures.Stack;
import DataStructures.ArrayStack;

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
