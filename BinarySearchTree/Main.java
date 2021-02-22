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

        int res = bst.select(3);
        System.out.println(res);

//        bst.preOrder(); // 前序遍历
//        bst.inOrder(); // 中序遍历
//        bst.postOrder(); // 后序遍历
//        bst.preOrderNR();
//        bst.levelOrder();

//        int mini = bst.minimum();
//        int max = bst.maximum();
//        System.out.println("Mini: " + mini + " " + "Max: " + max);

//        int min = bst.removeMin();
//        System.out.println(min);
//
//        min = bst.removeMin();
//        System.out.println(min);
//
//        min = bst.removeMin();
//        System.out.println(min);
//
//        min = bst.removeMin();
//        System.out.println(min);


//        int min = bst.removeMaxi();
//        System.out.println(min);
//
//        min = bst.removeMaxi();
//        System.out.println(min);
//
//        min = bst.removeMaxi();
//        System.out.println(min);
//
//        min = bst.removeMaxi();
//        System.out.println(min);

//        bst.remove(3);
//
//
//        System.out.println();
//        System.out.println(bst);
//        System.out.println(bst.size());


//        int[] nums = {1, 2, 3, 4, 5, 6};
//        for (int num : nums) {
//            bst.add(num);
//        }
//        System.out.println(bst);
    }
}
