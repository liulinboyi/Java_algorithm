package LeetCode.solution206;

public class Solution {

    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head == null ? null : head;
        ListNode next = cur == null ? null : cur.next;

        // 第一步
        //  prev   cur   next
        //   |      |     |
        // null ->  1 ->  2 -> 3 -> 4 -> 5

        // 第二步
        // 将cur的next指向prev,此时cur与next已断开联系，幸好我们保存着next
        // prev    cur  next
        //   |      |    |
        // null <-  1    2 -> 3 -> 4 -> 5

        // 第三部
        // 此时将cur赋值给prev;
        // 将next赋值给cur;
        // 将next.next赋值给next即cur.next赋值给next
        //         prev  cur  next
        //          |    |     |
        // null <-  1    2 -> 3 -> 4 -> 5

        // 再次进行第二步

        // 第四部
        // 最后链表翻转后为
        // null <- 1 <- 2 <- 3 <- 4 <- 5

        while (cur != null) {
            System.out.println("prev:  " + prev);
            System.out.println("cur:  " + cur);
            System.out.println("next:  " + next);
            cur.next = prev;

            prev = cur;
            if (next == null) {
                return prev;
            }
            cur = next;
            next = cur.next;
        }
        return null;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
//        ListNode listNode = new ListNode(arr);
        ListNode listNode = new ListNode();
        System.out.println(listNode);
        ListNode res = reverseList(listNode);
        System.out.println(res);
    }

}
