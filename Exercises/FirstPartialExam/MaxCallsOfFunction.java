/*
Да се најде функцијата која повикува најголем број функции од дадени повици на функции.
Повиците и враќањата од функции се дадени на влез.
На излез да се испечати името на функцијата од која се повикани најголем број други функции.
Се бројат само директни повици на функции. Рекурзивни повици нема да бидат излистани.
Повиците од нулто ниво се прават од main функцијата.
На влез прво е даден бројот на редови во влезот N,
а потоа се излистани повиците и враќањата од процедурите.
Пример влез:
12
Call a
Call b
Call c
Return
Call d
Return
Call e
Return
Return
Call f
Return
Return

Пример излез:
b 3

Објаснување:
Од функцијата b се повикани функциите c, d и е

--------------------------------------------

Find the function that makes the most calls to other functions.
The calls and returns of functions are given on input.
The output should contain the name of the function that made most of the calls,
as well as the number of calls made. Only direct function calls are counted.
Recursive calls will not be listed. Zero-level calls are made from the main function.
The input contains a number N, which is the number of next lines on the input.
In the next N lines, the calls and returns are listed.
Example input:
12
Call a
Call b
Call c
Return
Call d
Return
Call e
Return
Return
Call f
Return
Return

Example output:
b 3

Explanation:
The function b calls the functions c, d and е
*/

package Exercises.FirstPartialExam;

public class MaxCallsOfFunction {

}
