/*
За да се изведе едно предвидување со тарот карти, гатачката користи еден шпил карти,
од кој зема точно 12 карти и ги дели на две половини, кои се чуваат во две еднострано поврзани листи.
Така, во првата листа се чуваат податоците за картите од првиот дел,
а додека пак во втората се чуваат податоците за картите во вториот дел.
За секоја карта се важни податоците за: id на картата (int id) и ранг на картата (int rank).
Пред да започне со предвидувањето со картите, гатачката прави мешање на картите кое се изведува во 3 чекори,
секогаш во овој редослед:
1. Од првиот дел ја зема првата карта и ја става како последна карта во вториот дел.
2. Потоа, од вториот дел ја зема првата карта и ја става како последна карта во првиот дел.
3. За крај, ја зема претпоследната карта од првиот дел и ја става на средина на вториот дел.

Ваша задача е да симулирате вакво мешање на тарот картите, во точно кажаниот редослед.

Влез:
Во секој ред се дадени податоци за една тарот карта, одделени со празно место, во формат id rank.
Притоа, прво се дадени картите од првиот дел, по што следуваат податоците за картите од вториот дел.

Излез:
Во првиот ред id на сите карти од првиот дел, а во вториот ред id на сите карти од вториот дел.

Внимавајте:
1. Секое едно земање на карта од еден и префрлање на истата во друг дел,
значи бришење на картата од едната листа и вметнување во другата листа, на одреденото место.
2. Даден е целосниот код на структурата којашто треба да се користи.
Дадена е и тест класата Tarot.java, со целосно имплементиран input и output.
Потребно е да се менува само во рамки на void tarotCards(SLL<Card> firstPart, SLL<Card> secondPart) функцијата.
3. Притоа, бришењето треба да биде имплементирано како бришење на цел јазол, а додавањето како додавање на цел јазол.
Промените (бришење/додавање елемент) не треба да се однесуваат на информациите во самите јазли,
туку во промени на врските меѓу јазлите.
4. Не смее да менувате во main функцијата!

Input:
33 51
18 52
40 50
6 24
4 18
88 13
45 34
98 3
87 16
32 19
28 22
82 5

Result:
18->40->6->4->45
98->87->32->88->28->82->33

*Output-от оригинално треба да е без стрелки.*
*Овде не е користена посебна класа за решението, но логиката е таа.*
*/

package Exercises.FirstPartialExam;

import java.util.*;

import DataStructures.SLLNode;
import DataStructures.SLL;

public class TarotShuffling {
    static class Card {
        int id;
        int rank;

        public Card(int id, int rank) {
            this.id = id;
            this.rank = rank;
        }

        @Override
        public String toString() {
            return id + "";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SLL<Card> firstDeck = new SLL<>();
        SLL<Card> secondDeck = new SLL<>();
        for (int i = 0; i < 6; i++) {
            int id = scanner.nextInt();
            int rank = scanner.nextInt();
            Card card = new Card(id, rank);
            firstDeck.insertLast(card);
        }
        for (int i = 0; i < 6; i++) {
            int id = scanner.nextInt();
            int rank = scanner.nextInt();
            Card card = new Card(id, rank);
            secondDeck.insertLast(card);
        }
        SLLNode<Card> middle = firstDeck.getFirst();
        while (middle != null) {
            if (middle.succ == null)
                break;
            middle = middle.succ;
        }
        firstDeck.delete(middle);
        Card middleCard = middle.element;
        Card c = firstDeck.deleteFirst();
        secondDeck.insertLast(c);
        c = secondDeck.deleteFirst();
        firstDeck.insertLast(c);
        SLLNode<Card> temp = secondDeck.getFirst();
        for (int i = 0; i < secondDeck.size() / 2; i++) {
            if (i == secondDeck.size() / 2 - 1) {
                secondDeck.insertAfter(middleCard, temp);
            }
            temp = temp.succ;
        }
        System.out.println(firstDeck.toString());
        System.out.println(secondDeck.toString());
    }
}
