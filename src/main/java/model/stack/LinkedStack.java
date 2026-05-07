package model.stack;

import model.Node;

public class LinkedStack<T> implements MyStack<T> {
    private Node<T> top;
    private int size;

    public LinkedStack(){
        top = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        top = null;
        size = 0;

    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public T peek() throws StackException {
        if (isEmpty()) throw new StackException("Linked Stack is empty");
        return top.data;
    }

    @Override
    public T top() throws StackException {
        if (isEmpty()) throw new StackException("Linked Stack is empty");
        return top.data;
    }

    @Override
    public void push(T element) throws StackException {
        Node<T> node = new Node<>(element);
        if(isEmpty()){
            top = node;
        } else {
            node.next = top;
            top = node;
        }
        size++;
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
