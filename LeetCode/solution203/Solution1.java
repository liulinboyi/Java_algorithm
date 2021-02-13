package LeetCode.solution203;

public class Solution1 {
    public ListNode removeElements(ListNode head, int val, int deep) {
        generate(deep);
        System.out.print("Call: ");
        System.out.println("remove " + val + " in " + head);
        if (head == null) {
            return null;
        }
        // 将链表分为head和head.next
        // removeElements将head.next删除val
        ListNode result = removeElements(head.next, val, deep + 1);
        generate(deep);
        System.out.println("After remove " + val + ": " + result);
        if (head.val == val) {
            generate(deep);
            System.out.println("Return: " + result);
            return result;
        } else {
            head.next = result;
            generate(deep);
            System.out.println("Return: " + head);
            return head;
        }
    }

    public static void generate(int deep) {
        for (int i = 0; i < deep; i++) {
            System.out.print("--");
        }
    }

    public static void main(String[] args) {
        int[] testCase = {1, 2, 6, 3, 4, 5, 6};
        int target = 6;

        ListNode listNode = new ListNode(testCase);
//        System.out.println(listNode);
        Solution1 solution = new Solution1();
        ListNode res = solution.removeElements(listNode, target, 0);
//        System.out.println(res);
    }
}
