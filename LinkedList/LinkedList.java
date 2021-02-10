package LinkedList;

public class LinkedList<E> {

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
            return e.toString();
        }
    }


    private Node head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E e) {
//        Node node = new Node(e);
//        node.next = head;
//        head = node;

        head = new Node(e, head);
        size++;
    }

    public void addLast(E e) {
        add(size, e);
    }

    public void add(int index, E e) {
        // index 可以等于size，等同于在链表末尾添加元素
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Index is Error.");
        }
        if (index == 0) {
            addFirst(e);
        } else {
            Node prev = head;
            // 找到需要添加位置的前一个位置
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
//            Node newNode = new Node(e);
//            newNode.next = prev.next;
//            prev.next = newNode;

            // 例如我想在位置为2的地方插入元素10，此时找到的prev就是位置为1
            // 将当前新建的node节点的next改指向位置为1的prev的next
            // 将prev的next改指向新建的node
            // 实例如下图：
            // 0 -> 1 ->  2 -> 3 -> null
            //           /|\
            //           /
            //         10

            // 0 -> 1 ->  2 -> 3 -> null
            //       \
            //      \|/
            //      10

            prev.next = new Node(e, prev.next);
            size++;
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Node prev = head;
        for (int i = 0; i < size; i++) {
            str.append(prev.e);
            str.append(" -> ");
            prev = prev.next;
        }
        str.append("NULL");
        return "LinkedList{" +
                "head=" + head +
                ", size=" + size +
                '}' + "\nList = " +
                str.toString()
                ;
    }

    private static void outOfBoundsAdd(int index, int e, LinkedList linkedList) {
        int size = linkedList.getSize();
        for (int i = size; i < index; i++) {
            linkedList.addLast(null);
        }
        linkedList.addLast(e);
        System.out.println(linkedList);
    }

    public static void main(String[] args) {


        LinkedList linkedList = new LinkedList();

        for (int i = 0; i < 10; i++) {
            linkedList.add(i, i);
        }
        System.out.println(linkedList);
        linkedList.add(2, 20);
        System.out.println(linkedList);
        linkedList.addFirst(-1);
        System.out.println(linkedList);
//        int size = linkedList.getSize();
        // 让第15个位置为15 ----- 开始
        int index = 15;
//        for (int i = size; i < index; i++) {
//            linkedList.addLast(null);
//        }
//        linkedList.addLast(15);
//        System.out.println(linkedList);
        outOfBoundsAdd(index, 15, linkedList);
        // 让第15个位置为15 ----- 结束
    }
}
