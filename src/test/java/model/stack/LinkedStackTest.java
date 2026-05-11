package model.stack;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class LinkedStackTest {
    @Test
    void push() {
        LinkedStack<Integer> stack = new LinkedStack<>();
        try {
            for (int i = 0; i < 10; i++) {
                int value = new Random().nextInt(50);

                System.out.println("push(" + value + ")");
                stack.push(value);

            }
            System.out.println("stack size: " + stack.size());
            System.out.println("Peek | Top: " + stack.peek());
            System.out.println(stack);
            for (int i = 0; i < 5; i++) {
                System.out.println("pop(): " + stack.pop());

            }
            System.out.println(stack);
        } catch (StackException e) {
            throw new RuntimeException(e);
        }

    }

}