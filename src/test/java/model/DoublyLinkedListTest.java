package model;

import model.DoublyLinkedList;
import model.ListException;
import org.junit.jupiter.api.Test;

import java.util.Random;

class DoublyLinkedListTest {

    @Test
    void doublyLinkedListTest() {
        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>();
        Random random = new Random();

        while (doublyLinkedList.size() < 50) {
            int value = random.nextInt(100) + 1;

            if (!doublyLinkedList.contains(value)) {
                doublyLinkedList.add(value);
            }
        }

        System.out.println("Lista con 50 valores numéricos no repetidos");
        System.out.println(doublyLinkedList);
        System.out.println("_".repeat(80));

        try {
            System.out.println("getFirst(): " + doublyLinkedList.getFirst());
            System.out.println("getLast(): " + doublyLinkedList.getLast());
            System.out.println("_".repeat(80));

            System.out.println("Búsqueda de 20 valores aleatorios");

            for (int i = 1; i <= 20; i++) {
                int value = random.nextInt(100) + 1;

                if (doublyLinkedList.contains(value)) {
                    System.out.println("Valor encontrado: " + value);
                    System.out.println("Posición: " + doublyLinkedList.indexOf(value));
                    System.out.println("Anterior: " + doublyLinkedList.getPrev(value));
                    System.out.println("Posterior: " + doublyLinkedList.getNext(value));
                } else {
                    System.out.println("Valor no encontrado: " + value);
                }

                System.out.println("_".repeat(40));
            }

            Integer removedFirst = doublyLinkedList.removeFirst();
            System.out.println("removeFirst(): " + removedFirst);
            System.out.println(doublyLinkedList);
            System.out.println("_".repeat(80));

            Integer removedLast = doublyLinkedList.removeLast();
            System.out.println("removeLast(): " + removedLast);
            System.out.println(doublyLinkedList);
            System.out.println("_".repeat(80));

            Integer valueToRemove = doublyLinkedList.get(random.nextInt(doublyLinkedList.size()) + 1);
            doublyLinkedList.remove(valueToRemove);

            System.out.println("remove(" + valueToRemove + ")");
            System.out.println("Elemento eliminado: " + valueToRemove);
            System.out.println(doublyLinkedList);

        } catch (ListException e) {
            System.out.println(e.getMessage());
        }
    }
}