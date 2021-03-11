package DuLinkList;

public class DuLinkList<E> {

    private final Node dummyHead; /* 虚拟头结点 */
    private final Node dummyTail; /* 虚拟尾结点 */
    private int size;

    public DuLinkList() {
        dummyHead = new Node(null, null, null);
        dummyTail = new Node(null, null, null);
        dummyHead.sign = "dummyHead";
        dummyTail.sign = "dummyTail";
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
        size = 0;
    }

    public Node head() {
        return dummyHead;
    }

    public Node tail() {
        return dummyTail;
    }

    public int getSize() {
        return size;
    }

    public class Node {
        public E e;
        public Integer key;
        public Integer value;
        private String sign = null;
        public Node next;
        public Node prev;

        public Node(E e, Node next, Node prev) {
            this.e = e;
            this.next = next;
            this.prev = prev;
        }

        public Node(E e) {
            this(e, null, null);
        }

        public Node() {
            this(null, null, null);
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

    public Node add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Index is Error.");
        }
        // 添加节点，关键在于找到上一个节点
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node node = new Node(e, null, null);
        node.next = prev.next; // 将新建节点的next指向，前一个节点的next
        node.prev = prev; // 新建节点的prev指向上一个节点
        Node cur = prev.next; // 当前节点
        cur.prev = node; // 上一个节点的下一个节点，也就是当前节点的上一个节点指向新建节点
        prev.next = node; // 上一个节点下一个节点指向新建节点
        size++;
        return node;
    }

    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Index is Error.");
        }
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node delNode = prev.next;
        prev.next = delNode.next;
        delNode.next.prev = prev;
        size--;
        return delNode.e;
    }

    public Node removeTail() {
        Node delNode = dummyTail.prev;
        Node prev = delNode.prev;
        prev.next = dummyTail;
        dummyTail.prev = prev;
        size--;
        return delNode;
    }

    public Node addFirst(E e) {
        return add(0, e);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Node cur = dummyHead.next; cur.next != null; cur = cur.next) {
            str.append(cur.e).append(" -> ");
        }
        StringBuilder strTail = new StringBuilder();
        for (Node cur = dummyTail.prev; cur.prev != null; cur = cur.prev) {
            strTail.append(cur.e).append(" -> ");
        }
        str.append("NULL");
        strTail.append("NULL");
        return "DuLinkList{" +
                "head= " + str.toString() +
                ", tail= " + strTail.toString() +
                ", size= " + size +
                '}';
    }

    public static void main(String[] args) {
        DuLinkList<Integer> dou = new DuLinkList<Integer>();
//        dou.add(0, 0);
//        dou.add(1, 1);
//        dou.add(2, 2);
//        dou.add(3, 3);
//        dou.add(4, 4);
        dou.addFirst(0);
        dou.addFirst(1);
        dou.addFirst(2);
        dou.addFirst(3);
        dou.addFirst(4);
        dou.remove(2);

        System.out.println(dou);
    }


}
