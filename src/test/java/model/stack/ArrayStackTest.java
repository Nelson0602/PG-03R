package model.stack;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStackTest {
    @Test
    void push() throws StackException {
        ArrayStack<Integer> stack = new ArrayStack<>(20);
        for (int i = 0; i < 5; i++) {
            int value = new Random().nextInt(50);
            System.out.println("Push (" + value+")");
            stack.push(value);
        }
        System.out.println("Stack size: " + stack.size());
        System.out.println("Peek / Top: " + stack.peek());

        for (int i = 0; i < 5; i++) {
            System.out.println("Pop: " + stack.pop());
        }
    } catch (StackException e) {
        System.out.println(e.getMessage());
    }


}