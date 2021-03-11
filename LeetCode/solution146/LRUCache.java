package LeetCode.solution146;

import java.util.HashMap;
import java.util.Map;

// 缓存
// 大小 替换策略
// Hash Table + Double LinkedList
// 哈希表和双向链表来实现

// O(1) 查询
// O(1) 修改更新

// 最近最少使用 的放在最后去淘汰
// 最近被使用的元素永远放（翻新）在前面
// LRU => Least Recent Used

public class LRUCache {

    public class Node {
        public Integer e;
        public Integer key;
        public Integer value;
        private String sign = null;
        public Node next;
        public Node prev;

        public Node(Integer e, Node next, Node prev) {
            this.e = e;
            this.next = next;
            this.prev = prev;
        }

        public Node(Integer e) {
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


    public class DoubleLinkedList<E> {

        private final Node dummyHead; /* 虚拟头结点 */
        private final Node dummyTail; /* 虚拟尾结点 */
        private int size;

        public DoubleLinkedList() {
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

        public Node add(int index, Integer e) {
            if (index < 0 || index > size) {
                throw new IllegalArgumentException("Index is Error.");
            }
            // 添加节点，关键在于找到上一个节点
            Node prev = dummyHead;
            for (int i = 0; i < index; i++) {
                prev = prev.next;
            }
            Node node = new Node(e);
            node.next = prev.next; // 将新建节点的next指向，前一个节点的next
            node.prev = prev; // 新建节点的prev指向上一个节点
            Node cur = prev.next; // 当前节点
            cur.prev = node; // 上一个节点的下一个节点，也就是当前节点的上一个节点指向新建节点
            prev.next = node; // 上一个节点下一个节点指向新建节点


            size++;
            return node;
        }

        public Node add(int index, Integer e, Integer key, Integer value) {
            if (index < 0 || index > size) {
                throw new IllegalArgumentException("Index is Error.");
            }
            // 添加节点，关键在于找到上一个节点
            Node prev = dummyHead;
            for (int i = 0; i < index; i++) {
                prev = prev.next;
            }
            Node node = new Node(e);
            node.key = key;
            node.value = value;
            node.next = prev.next; // 将新建节点的next指向，前一个节点的next
            node.prev = prev; // 新建节点的prev指向上一个节点
            Node cur = prev.next; // 当前节点
            cur.prev = node; // 上一个节点的下一个节点，也就是当前节点的上一个节点指向新建节点
            prev.next = node; // 上一个节点下一个节点指向新建节点


            size++;
            return node;
        }

        public Integer remove(int index) {
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
//            Node delNode = dummyTail.prev;
//            Node prev = delNode.prev;
//            prev.next = dummyTail;
//            dummyTail.prev = prev;
            Node delNode = dummyTail.prev;
            removeNode(delNode);
            return delNode;
        }

        public void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            du.size--;
        }

        public Node addFirst(Integer e) {
            return add(0, e);
        }

        public void addToHead(Node node) {
            Node head = dummyHead;

//            node.prev = head;
//            node.next = head.next;
//            head.next.prev = node;
//            head.next = node;

            node.next = head.next; // 将新建节点的next指向，前一个节点的next
            node.prev = head; // 新建节点的prev指向上一个节点
            Node cur = head.next; // 当前节点
            cur.prev = node; // 上一个节点的下一个节点，也就是当前节点的上一个节点指向新建节点
            head.next = node; // 上一个节点下一个节点指向新建节点
            size++;
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

    }


    private Map<Integer, Node> cache = new HashMap<>();
    private int size;
    private int capacity;
    private DoubleLinkedList<Integer> du;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        // 使用伪头部和伪尾部节点
        du = new DoubleLinkedList<Integer>();
//        head = new DLinkedNode();
//        tail = new DLinkedNode();
//        head.next = tail;
//        tail.prev = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 如果 key 存在，先通过哈希表定位，再移到头部
        moveToHead(node);
//        System.out.println(node + "  node");
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null) {
            // 如果 key 不存在，创建一个新的节点
            Node newNode = new Node(value);
            newNode.key = key;
            newNode.value = value;
            // 添加进哈希表
            cache.put(key, newNode);
            // 添加至双向链表的头部
            du.addToHead(newNode);

//            Node newNode = du.add(0, value, key, value); // 这么写不行
            cache.put(key, newNode);

            ++size;
            if (size > capacity) {
                // 如果超出容量，删除双向链表的尾部节点
                Node tail = du.removeTail();
                // 删除哈希表中对应的项
                cache.remove(tail.key);
                --size;
            }
        } else {
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            node.value = value;
            moveToHead(node);
        }
    }


    private void moveToHead(Node node) {
        du.removeNode(node);
        du.addToHead(node);
//        Node newNode = du.add(0, node.value, node.key, node.value); // 这么写不行
    }

    public static void main(String[] args) {
        LRUCache LRU = new LRUCache(2);
        LRU.put(1, 1); // 缓存是 {1=1}
        LRU.put(2, 2); // 缓存是 {1=1, 2=2}
        LRU.get(1);    // 返回 1
        LRU.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        LRU.get(2);    // 返回 -1 (未找到)
        LRU.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        LRU.put(3, 10);
        LRU.get(3);
        LRU.get(3);
        LRU.get(1);    // 返回 -1 (未找到)
        LRU.get(3);    // 返回 3
        LRU.get(4);    // 返回 4

        System.out.println(LRU.du);
    }


}
