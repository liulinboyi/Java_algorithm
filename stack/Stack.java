package stack;

public interface Stack<E> {
    int getSize();

    boolean isEmpty();

    int getCapacity();

    void push(E e);

    E pop();

    E peek();
}
