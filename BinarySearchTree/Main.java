package BinarySearchTree;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num : nums) {
            bst.add(num);
        }
        //       5
        //   3       6
        // 2   4         8

//        bst.preOrder(); // 前序遍历
//        bst.inOrder(); // 中序遍历
//        bst.postOrder(); // 后序遍历
//        bst.preOrderNR();
//        bst.levelOrder();
        int mini = bst.minimum();
        int max = bst.maximum();
        System.out.println("Mini: " + mini + " " + "Max: " + max);
        System.out.println();
        System.out.println(bst);
    }
}
