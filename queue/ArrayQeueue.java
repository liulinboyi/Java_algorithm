package queue;

import array.src.Array;

public class ArrayQeueue<E> implements Queue<E> {

    private Array<E> array;

    public ArrayQeueue(int capacity) {
        array = new Array(capacity);
    }

    public ArrayQeueue() {
        array = new Array();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public String toString() {
        return "ArrayQeueue{" +
                "array=" + array +
                '}';
    }

    public static void main(String[] args) {
        ArrayQeueue<Integer> arrays = new ArrayQeueue<Integer>();
        for (int i = 1; i <= 10; i++) {
            arrays.enqueue(i);
            if (i % 3 == 0) {
                arrays.dequeue();
            }
            System.out.println(arrays);
        }
    }

}
