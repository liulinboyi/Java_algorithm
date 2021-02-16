package LeetCode.solution206;

public class Solution1 {

    public static ListNode reverseList(ListNode head) {
        // 链表本身为null，或者链表只有一个节点，链表不需要翻转
        if (head == null || head.next == null) {
            return head;
        }
        ListNode rev = reverseList(head.next);
        // 此时head仍指向rev的结尾
        head.next.next = head;
        head.next = null;
        return rev;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        ListNode listNode = new ListNode(arr);
//        ListNode listNode = new ListNode();
        System.out.println(listNode);
        ListNode res = reverseList(listNode);
        System.out.println(res);
    }

}
