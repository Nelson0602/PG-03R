package model.stack;

import model.Node;

public class LinkedStack<T> implements MyStack<T> {
    private Node<T> top;
    private int size;

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public T peek() throws StackException {
        return null;
    }

    @Override
    public T top() throws StackException {
        return null;
    }

    @Override
    public void push(T element) throws StackException {

    }

    @Override
    public T pop() throws StackException {
        return null;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Linked Stack is Empty";
        StringBuilder sb = new StringBuilder(" → ");
        try {
            LinkedStack<T> auxStack = new LinkedStack<>();
            while (!isEmpty()) {
                sb.append("[").append(peek()).append("] ");
                auxStack.push(pop());
                if(isEmpty()) sb.append(", ");
            }
            //dejamos la pila original
            while (!auxStack.isEmpty())
                push(auxStack.pop());

        } catch (StackException e) {
            System.out.println(e.getMessage());
        }
        sb.append(" → BOTTOM");
        return sb.toString();
    }
}
