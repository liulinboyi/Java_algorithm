package BinarySearchTree;

/**
 * 二分搜索树，存储的数据必须可以被比较（局限性或者说是代价）
 */
public class BinarySearchTree<E extends Comparable<E>> {
    private class Node {
        public E e;
        public Node left;
        public Node right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "e=" + e +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    private Node root;
    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 向以node为根的二分搜索树种插入元素e，非递归算法
    public void add(E e) {
        if (root == null) {
            root = new Node(e);
            size++;
        }
        Node childRoot = root;
        while (true) {
            // 插入节点值大于根节点，则向右子树添加
            if (e.compareTo(childRoot.e) > 0) {
                if (childRoot.right == null) {
                    childRoot.right = new Node(e);
                    size++;
                    break;
                } else {
                    childRoot = childRoot.right;
                }
                // 插入节点值小于根节点，则向左子树添加
            } else if (e.compareTo(childRoot.e) < 0) {
                if (childRoot.left == null) {
                    childRoot.left = new Node(e);
                    size++;
                    break;
                } else {
                    childRoot = childRoot.left;
                }
                // 插入节点值等于于根节点，则退出，此插入不包含相等情况
            } else {
                break;
            }
        }
    }

    // 向以node为根的二分搜索树种插入元素e，递归算法
    public void addREC(E e) {
        if (root == null) {
            root = new Node(e);
            size++;
        } else {
            add(root, e);
        }
    }

    // 向以node为根的二分搜索树种插入元素e，递归算法
    private void add(Node node, E e) {
        // 递归终止条件
        if (e.equals(node.e)) {
            return;
        } else if (e.compareTo(node.e) < 0 && node.left == null) {
            node.left = new Node(e);
            size++;
            return;
        } else if (e.compareTo(node.e) > 0 && node.right == null) {
            node.right = new Node(e);
            size++;
            return;
        }

        if (e.compareTo(node.e) < 0) {
            add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            add(node.right, e);
        }
    }

    @Override
    public String toString() {
        return "BinarySearchTree{" +
                "root=" + root +
                ", size=" + size +
                '}';
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
//        tree.add(10);
//        tree.add(9);
//        tree.add(11);
//        tree.add(12);
//        tree.add(6);
//        tree.add(6);

        tree.addREC(10);
        tree.addREC(9);
        tree.addREC(11);
        tree.addREC(12);
        tree.addREC(6);
        tree.addREC(6);
        System.out.println(tree);
    }
}
