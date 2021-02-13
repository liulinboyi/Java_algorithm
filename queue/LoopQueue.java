package queue;

public class LoopQueue<E> implements Queue<E> {
    private E[] data;
    private int front;
    private int tail;
    private int size; // 方便获取长度

    public LoopQueue(int capacity) {
        // 因为需要保证front==tail表示队列为空，需要浪费一个空间
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    public int getCapacity() {
        // 获取容器真实大小
        // 容器长度被我们有意识的浪费掉了
        return data.length - 1;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(E e) {
        // 是否满了，满了则扩容
        if (isFull()) {
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        size++;
        tail = (tail + 1) % data.length;
    }

    private boolean isFull() {
        //               tail front
        //                |    |
        //         0  1   2    3  4  5
        // data = [1 ,2, 空,   3, 4, 5] tail = 2 front = 3
        // (2+1)%6 == 3 这个队列是满的

        //       front              tail
        //         |                 |
        //         0  1   2    3  4  5
        // data = [1 ,2, 3,   4, 5, 空] tail = 5 front = 0
        // (5+1)%6 == 0 这个队列是满的
        return (tail + 1) % data.length == front;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < getSize(); i++) {
            // front偏移量
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public E dequeue() {
        // 缩容
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }
        E result = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return result;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty.");
        }
        return data[front];
    }

    @Override
    public String toString() {
        StringBuilder datas = new StringBuilder();
        datas.append("[");
        for (int i = 0; i < getSize(); i++) {
            int index = (i + front) % data.length;
            datas.append(data[index]);
            // stack元素在data中的索引加1是tail的索引则表示是最后一个
            if ((index + 1) % data.length != tail) {
                datas.append(", ");
            }
        }

//        for (int i = front; i != tail; i = (i + 1) % data.length) {
//            datas.append(data[i]);
//            if ((i + 1) % data.length != tail) {
//                datas.append(", ");
//            }
//        }

        datas.append("] \n");
        return "LoopQueue{" +
                "data=" + datas.toString() +
//                "         dates=" + Arrays.toString(data) +
                ", front=" + front +
                ", tail=" + tail +
                ", size=" + size +
                ", capacity= " + getCapacity() +
                '}';
    }

    public static void main(String[] args) {
        LoopQueue<Integer> queue = new LoopQueue<>();

        for (int i = 1; i <= 10; i++) {
            queue.enqueue(i);
            if (i % 3 == 0) {
                queue.dequeue();
            }
            System.out.println(queue);
        }

    }
}
