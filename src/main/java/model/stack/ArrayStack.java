package model.stack;

public class ArrayStack<T> implements MyStack<T> {

    private int n;
    private int tOP;
    private T[] data;

    public ArrayStack(int n) {
        if (n <= 0) System.exit(1);
        this.n = n;
        this.tOP = -1;
        this.data = (T[]) new Object[n];
    }

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
}
