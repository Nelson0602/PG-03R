package model.queue;

import model.stack.ArrayStack;

public class ArrayQueue<T> implements MyQueue<T> {
    private int n; //el tam max de la cola
    private T[] data; //arreglo de objetos
    private Integer[] priorityQueue; //para el manejo de prioridades
    //me permite manejar los extremos de la cola
    private int front, rear; //anterior, posterior

    //Constructor
    public ArrayQueue(int n) {
        if (n <= 0) System.exit(1); //se sale
        this.n = n;
        this.data = (T[]) new Object[n];//n=capacidad
        this.priorityQueue = new Integer[n];
        this.rear = n - 1; //ultimo elemento de la cola
        this.front = rear;
    }

    @Override
    public int size() {
        return rear - front;
    }

    @Override
    public void clear() {

        data = (T[]) new Object[n];//n=capacidad
        rear = n - 1; //ultimo elemento de la cola
        front = rear;
    }

    @Override
    public boolean isEmpty() {
        return front == rear;
    }

    @Override
    public int indexOf(T element) throws QueueException {
        return 0;
    }

    @Override
    public void enQueue(T element) throws QueueException {
        if (size() == data.length) {
            throw new QueueException("Array Queue is full");
        }
        //la primera vez cuando esta vacio no entra al for
        for (int i = front; i < rear; i++) {
            data[i] = data[i + 1];//mueve el elemento una pos a la izquierda
        }
        data[rear] = element;
        front--;//la idea es que anterior que en un campo vacio

    }

    @Override
    public T deQueue() throws QueueException {
        if (isEmpty()) {
            throw new QueueException("Array Queue is empty");
        }
        return data[++front];
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
        if (size() == data.length) {
            throw new QueueException("Array Queue is full");
        }
        return data[front + 1];
    }

    @Override
    public T front() throws QueueException {
        if (size() == data.length) {
            throw new QueueException("Array Queue is full");
        }
        return data[front + 1];
    }

    @Override
    public String toString() {
        if(isEmpty()) return "Arrat Queue is empty";
        StringBuilder sb =new StringBuilder("FRONT -> ");
        ArrayQueue<T> auxQueue = new ArrayQueue<>(size());
        try {
            while (!isEmpty()){

                sb.append("[").append(peek()).append("]");
                auxQueue.enQueue(deQueue());
                if(!isEmpty()) sb.append(", ");
            }
            while ((!auxQueue.isEmpty())){
                enQueue((auxQueue.deQueue()));
            }
        } catch (QueueException e) {
            throw new RuntimeException(e);
        }
        sb.append(" → REAR");
        return sb.toString();
    }
}