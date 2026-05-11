package model.queue;

import model.Node;

public class LinkedQueue<T> implements MyQueue<T> {
    private Node<T> front; //anterior o frente de la cola
    private Node<T> rear; //posterior o final de la cola
    private int size; //control de elementos encolados

    public LinkedQueue() {
        front = rear = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        front = rear = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return front == null;
    }

    @Override
    public int indexOf(T element) throws QueueException {
        return 0;
    }

    @Override
    public void enQueue(T element) throws QueueException {
        Node<T> node = new Node<>(element);
        if (isEmpty()) front = rear = node;
        else {
            rear.next = node;
            rear = node;
        }
        size++;
    }

    @Override
    public T deQueue() throws QueueException {
        if (isEmpty()) {
            throw new QueueException("Array Queue is empty");
        }
        T element = front.data;
        //caso 1:solo hay un elemento
        if (front == rear) clear();
        else {//caso 2: hay mas de un elemento
            front = front.next;

        }
        size--;
        return element ;
    }

    @Override
    public void enQueue(T element, Integer priority) throws QueueException {

    }

    @Override
    public boolean contains(T element) throws QueueException {
        return false;
    }

    @Override
    public T peek() throws QueueException {
        if (isEmpty()) {
            throw new QueueException("Array Queue is empty");
        }
        return front.data;
    }

    @Override
    public T front() throws QueueException {
        if (isEmpty()) {
            throw new QueueException("Array Queue is empty");
        }
        return front.data;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Arrat Queue is empty";
        StringBuilder sb = new StringBuilder("FRONT -> ");
        LinkedQueue<T> auxQueue = new LinkedQueue<>();
        try {
            while (!isEmpty()) {

                sb.append("[").append(peek()).append("]");
                auxQueue.enQueue(deQueue());
                if (!isEmpty()) sb.append(", ");
            }
            while ((!auxQueue.isEmpty())) {
                enQueue((auxQueue.deQueue()));
            }
        } catch (QueueException e) {
            throw new RuntimeException(e);
        }
        sb.append(" → REAR");
        return sb.toString();
    }
}