package LeetCode.solution1669;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {

    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        // a = 2; b = 4
        // list1: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
        // list2: 8 -> 9 -> 10 -> null
        //

        int count = 0;
        ListNode cur = list1;
        ListNode delPrev = null;
        ListNode delNext = null;
        ListNode list2Tail = null; // list2长度不定
        ListNode next = list2;
        while (count <= b + 1 || next.next != null) {
            if (count <= b + 1) {
                if (count == a - 1) { // 前一个
                    delPrev = cur;
                }
                if (count == b + 1) { // 后一个
                    delNext = cur;
                }
                cur = cur.next;
                System.out.println(delPrev);
                System.out.println(delNext);
                count++;
            }
            if (next.next != null) {
                next = next.next;
            }
        }
        list2Tail = next;

        delPrev.next = list2;
        list2Tail.next = delNext;

        return list1;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5, 6};
        int[] arr2 = {8, 9, 10};
        ListNode listNode1 = new ListNode(arr1);
        ListNode listNode2 = new ListNode(arr2);
        //        ListNode listNode = new ListNode();
        System.out.println(listNode1);
        ListNode res = (new Solution()).mergeInBetween(listNode1, 2, 4, listNode2);
        System.out.println(res);
    }

}
