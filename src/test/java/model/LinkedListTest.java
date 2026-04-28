package model;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    @Test
    public void linkedListTest() {
        LinkedList<Integer> linkedList = new LinkedList();
        linkedList.add(20);
        linkedList.add(10);
        for (int i = 0; i < 20; i++) {
            linkedList.add(new Random().nextInt(50));
        }
        System.out.println(linkedList);
        //
        System.out.println("getHead: " + linkedList.getHead().data);
        System.out.println("getTail: " + linkedList.getTail().data);

        System.out.println("addFirst(100)");
        linkedList.addFirst(100);
        System.out.println("addFirst(200)");
        linkedList.addFirst(200);
        System.out.println(linkedList);
        try {
            System.out.println("LinkedList size: " + linkedList.size());
            //probamos contains
            for (int i = 0; i < 10; i++) {
                int value = new Random().nextInt(50);
                System.out.println(linkedList.contains(value) ? "value [" + value + "] exist. Position " +
                        linkedList.indexOf(value)
                        : "value [ " + value + "] does not exist");
            }

            for (int i = 0; i < 20; i++) {
                System.out.println("get(" + i + ") =" + linkedList.get(i));
            }
        } catch (ListException e) {
            throw new RuntimeException(e);
        }
    }

//    //Probamos removeFirts
//    int value = linkedList.removeFirst();
//    int n = linkedList.size();
//    for(
//    int i = 1;
//    i<=)
//} catch(
//ListException e){
//        throw new
//
//RuntimeException(e);


    @Test
    public void linkedListTest2() throws ListException {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(20);
        linkedList.add(10);
        linkedList.add(15);
        linkedList.add(30);


        System.out.println(linkedList);
        System.out.println("\nremoveFirst: " + linkedList.removeLast());
        System.out.println(linkedList);
        System.out.println("removeLast" + linkedList.removeLast());
        System.out.println(linkedList);
        System.out.println("removeLast" + linkedList.removeLast());
        System.out.println(linkedList);
        System.out.println("removeLast" + linkedList.removeLast());
        System.out.println(linkedList);
    }

    @Test
    public void linkedListTest3() {
        try {
            LinkedList<Integer> linkedList = new LinkedList<>();
            linkedList.add(20);
            linkedList.add(10);
            linkedList.add(15);
            linkedList.add(30);

            int n = linkedList.size();
            System.out.println("_".repeat(10));
            System.out.println(linkedList);

            System.out.println("EL PREV DE 10 ES: " + linkedList.getPrev(10));
            System.out.println("EL NEXT DE 10 ES: " + linkedList.getNext(10));
        } catch (ListException e) {
            throw new RuntimeException(e);
        }
    }
}