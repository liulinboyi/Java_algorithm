package queue;


public class LinkedListQueue<E> implements Queue<E> {

    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            if (e != null) {
                return e.toString();
            } else {
                return null;
            }
        }
    }

    private Node head, tail;
    public int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {
        if (tail == null) {
            tail = new Node(e);
            head = tail;
        } else {
            tail.next = new Node(e); // 在链表尾部添加元素
            tail = tail.next; // 维护tail
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (head == null) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }
        Node result = head; // 将当前头结点暂存起来
        head = head.next; // 后面节点覆盖头结点
        result.next = null; // 将原来头结点与后面节点断开
        if (head == null) {
            // 如果链表只有一个元素，出队后，tail应该也为null，
            // 如果不对tail进行维护，此时tail的值仍是创建的第一个元素
            tail = null;
        }
        size--; // 维护size
        return result.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("The queue is empty.");
        }
        return head.e;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Node cur = head;
        while (cur != null) {
            str.append(cur.e);
            str.append(" -> ");
            cur = cur.next;
        }
        str.append("NULL");
        return "LinkedListQueue{" +
                "front: " + str.toString() + " tail" +
                ", tail=" + tail +
                ", size=" + size +
                '}';
    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue + "  " + i);
            }
        }
    }
}
