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
            if (e != null) {
                return e.toString();
            } else {
                return null;
            }
        }
    }


    private final Node dummyHead; /* 虚拟头结点 */
    private int size;

    public LinkedList() {
        dummyHead = new Node(null, null);
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index is Error.");
        }
        // 取链表index当前位置的值
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            // 此时此处当cur取第1个位置时
            cur = cur.next;
            // 赋值操作后，此时cur为2的位置
        }
        return cur.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index is Error.");
        }
        // 取链表index当前位置的值
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            // 此时此处当cur取第1个位置时
            cur = cur.next;
            // 赋值操作后，此时cur为2的位置
        }
        cur.e = e;
    }

    public boolean contains(E e) {
        Node cur = dummyHead;
        // 实例：
        //      dummyHead
        // 当链表为 null -> 0 -> 1 -> 2 -> null 时
        // 要遍历链表所有元素
        // 从dummyHead开始遍历，遍历完所有元素，需要 i=0,i=1,i=2
        // 所以for循环结束条件为i<size(链表长度)
        for (int i = 0; i < size; i++) {
            cur = cur.next;
            if (cur.e.equals(e)) {
                return true;
            }
//            System.out.println(cur.e);
        }
        return false;
    }

    public boolean whileContains(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e == e) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public void add(int index, E e) {
        // index 可以等于size，等同于在链表末尾添加元素
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Index is Error.");
        }

        Node prev = dummyHead;
        // 找到需要添加位置的前一个位置
        for (int i = 0; i < index; i++) {
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

    public void addFirst(E e) {
//        Node node = new Node(e);
//        node.next = head;
//        head = node;

//        head = new Node(e, head);
//        size++;

        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index is Error.");
        }
        Node prev = dummyHead;
        // 找到要删除位置的前一个位置
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;
        size--;
        return delNode.e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
//        Node cur = dummyHead.next;
//        for (int i = 0; i < size; i++) {
//            str.append(cur.e);
//            str.append(" -> ");
//            cur = cur.next;
//        }

//        Node cur = dummyHead.next;
//        while (cur != null) {
//            str.append(cur.e);
//            str.append(" -> ");
//            cur = cur.next;
//        }

        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            str.append(cur.e + " -> ");
        }

        str.append("NULL");
        return "LinkedList{" +
                "head=" + dummyHead.next +
                ", size=" + size +
                '}' + "\nList = " +
                str.toString()
                ;
    }

    private static void outOfBoundsAdd(int index, int e, LinkedList linkedList) {
        int size = linkedList.getSize();
        for (int i = size + 1; i < index; i++) {
            linkedList.addLast(0);
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

        System.out.println(linkedList.contains(150));
        System.out.println(linkedList.whileContains(150));
    }
}
