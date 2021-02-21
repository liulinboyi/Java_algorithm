package BinarySearchTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二分搜索树，存储的数据必须可以被比较（局限性或者说是代价）
 */
public class BinarySearchTree<E extends Comparable<E>> {
    private class Node {
        public E e;
        public Node left;
        public Node right;
        public int NodeSize;


        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
            NodeSize = 1;
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

    private int NodeSize(Node node) {
        if (node == null) {
            return 0;
        } else {
            return node.NodeSize;
        }
    }

    public int NodeSize() {
        return NodeSize(root);
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
            return;
        }
        Node childRoot = root;
        while (childRoot != null) {
            // 插入节点值大于根节点，则向右子树添加
            // 寻找节点该放置的位置时，每访问一次节点，以该节点为根的元素数量加1
            childRoot.NodeSize++;
            if (e.compareTo(childRoot.e) > 0) {
                if (childRoot.right == null) {
                    childRoot.right = new Node(e);
                    size++;
                    return;
                } else {
                    childRoot = childRoot.right;
                }
                // 插入节点值小于根节点，则向左子树添加
            } else if (e.compareTo(childRoot.e) < 0) {
                if (childRoot.left == null) {
                    childRoot.left = new Node(e);
                    size++;
                    return;
                } else {
                    childRoot = childRoot.left;
                }
                // 插入节点值等于于根节点，则退出，此插入不包含相等情况
            } else {
                return;
            }
        }
    }

    // 向以node为根的二分搜索树种插入元素e，递归算法
    public void addREC(E e) {
        root = add(root, e);
    }

    // 向以node为根的二分搜索树种插入元素e，递归算法
    // null 本身也是二叉树
    // 返回插入新节点后，二分搜索树的根
    private Node add(Node node, E e) {
        // 递归终止条件
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        node.NodeSize = NodeSize(node.left) + NodeSize(node.right) + 1;
        return node;
    }

    public boolean contains(E e) {
        Node childRoot = root;
        while (childRoot != null) {
            if (e.compareTo(childRoot.e) == 0) {
                return true;
            } else if (e.compareTo(childRoot.e) > 0) {
                // 右子树
                childRoot = childRoot.right;
            } else {
                // 左子树
                childRoot = childRoot.left;
            }
        }
        return false;
    }

    public boolean containsREC(E e) {
        return contains(root, e);
    }

    public boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }

        if (e.compareTo(node.e) > 0) {
            // 右子树
            return contains(node.right, e);
        } else if (e.compareTo(node.e) < 0) {
            // 左子树
            return contains(node.left, e);
        } else {
            return true;
        }
    }

    // 非递归前序遍历
    // 深度优先遍历
    public void preOrderNR() {
//        ArrayStack<Node> stack = new ArrayStack<Node>();
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node top = stack.pop();
            System.out.println(top.e);
            if (top.right != null) {
                stack.push(top.right);
            }
            if (top.left != null) {
                stack.push(top.left);
            }
        }
    }

    // 前序遍历
    public void preOrder() {
        preOrder(root);
    }

    // traverse遍历
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        // 访问节点
        // 前序遍历 开始
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
        // 前序遍历 结束
    }

    // 中序遍历
    // 遍历结果是，所有可比较元素，排序后的结果，所以二分搜索树有时又被称为排序树
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        // 访问节点
        // 中序遍历 开始
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
        // 中序遍历 结束
    }

    // 二分搜索树，后序遍历
    // 应用于，内存释放，先释放当前节点的孩子节点释放完，再来释放节点本身
    // 对于一些树结构的问题，也需要节点的孩子节点求解出答案，用孩子节点的答案组合成这个节点的答案
    // 比如分治算法，回溯算法、动态规划算法等
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    // 层序遍历
    // 广度优先遍历
    // 如果做图的遍历，要做记录，查看当前节点是否遍历过，否则会产生重复（一个节点的父亲节点可能有多个）
    // 树结构不存在这个情况
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.e);
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
    }

    // 寻找二分搜索树的最小元素
    public E minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("The BST is empty!");
        }
        return minimum(root).e;
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    // 寻找二分搜索树的最大元素
    public E maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("The BST is empty!");
        }
        return maximum(root);
    }

    private E maximum(Node node) {
        if (node.right == null) {
            return node.e;
        }
        return maximum(node.right);
    }

    // 从二分搜索树中删除最小值，返回最小值
    public E removeMinOther() {
        E res = minimum();
        root = removeMinOther(root);
        return res;
    }

    // 删除以root为根的二分搜索树中的最小节点
    // 返回删除节点后的新的二分搜索树
    private Node removeMinOther(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMinOther(node.left);
        return node;
    }

    // 从二分搜索树中删除最大值，返回最大值
    public E removeMaxi() {
        E res = maximum();
        root = removeMaxi(root);
        return res;
    }

    // 删除以root为根的二分搜索树中的最大节点
    // 返回删除节点后的新的二分搜索树
    private Node removeMaxi(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMaxi(node.right);
        return node;
    }

    // 从二分搜索树中删除最小的节点，返回被删除节点
    public E removeMin() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("The BST is empty!");
        }
        Node dummyNode = new Node(null);
        dummyNode.left = root;
        E res = removeMin(dummyNode.left, dummyNode);
        root = dummyNode.left;
        return res;
    }

    private E removeMin(Node node, Node prev) {
        if (node.left == null) {
            E res = node.e;
            if (node.right == null) {
//                node.e = null;
                prev.left = null;
            } else {
                Node next = node.right;
                node.right = null;
//                node.e = null;
                prev.left = next;
            }
            size--;
            return res;
        }
        return removeMin(node.left, node);
    }

    public void remove(E e) {
        remove(root, e);
    }

    // 删掉以node为根的二分搜索树中值为e的节点，递归算法
    // 返回删除节点后新的二分搜索树的根
    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else { // e == node.e
            // 待删除节点左子树为null
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            // 待删除节点右子树为null
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // 待删除节点，左右子树都不为null
            // 找到待删除节点大的最小节点，即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点位置
            Node successor = minimum(node.right); // 后继者
            // 删除右子树的最小节点，赋值给后继者的右子树
            successor.right = removeMinOther(node.right);
            successor.left = node.left;

            node.left = null;
            node.right = null;

            return successor;

        }

    }

    private String generateDepthString(int depth) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            s.append("---");
        }
        return s.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder str, String p) {
        if (node == null) {
            str.append(generateDepthString(depth)).append("null").append(" ").append(p).append(" ").append("\n");
            return;
        }
        str.append(generateDepthString(depth)).append(node.e).append(" ").append(p).append(" ").append("\n");
        generateBSTString(node.left, depth + 1, str, "left");
        generateBSTString(node.right, depth + 1, str, "right");
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        generateBSTString(root, 0, str, "");
        return str.toString();
//        return "BinarySearchTree{" +
//                "root=" + root +
//                ", size=" + size +
//                '}';
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        tree.add(13);
//        tree.add(9);
//        tree.add(16);
//        tree.add(18);
//        tree.add(6);
//        tree.add(6);
//        tree.add(10);
//        tree.add(15);
        //           13
        //     9           16
        // 6      10   15     18

//        boolean res = tree.contains(7);
//        boolean res = tree.containsREC(9);
//        System.out.println(res);

//        tree.addREC(10);
//        tree.addREC(9);
//        tree.addREC(11);
//        tree.addREC(12);
//        tree.addREC(6);
//        tree.addREC(6);
        System.out.println(tree);
        tree.preOrder();
        int min = tree.removeMin();
        System.out.println(min);
        tree.preOrder();
    }
}
