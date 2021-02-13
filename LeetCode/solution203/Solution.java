package LeetCode.solution203;

public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        // 链表删除操作，需要找到待删除位置的前一个位置，所以最好使用虚拟头结点，来统一操作

        ListNode dummyHead = new ListNode(-1); // 设置虚拟节点
        dummyHead.next = head;
        ListNode prev = dummyHead;

        while (prev.next != null) { // 判断是否为尾结点
            if (prev.next.val == val) { // 判断prev节点的下个节点的值是否等于被删除值
                // 待删除节点为prev的next节点。
                // 要删除节点，需要知道待删除节点的上一个节点
                ListNode delNode = prev.next;
                // 跳过待删除节点，将prev节点的next指向待删除节点的next
                prev.next = delNode.next;
                // 将待删除节点与链表取消关联
                delNode.next = null;
            } else {
                prev = prev.next;
            }
        }

        return dummyHead.next;

    }

    public static void main(String[] args) {
        int[] testCase = {1, 2, 6, 3, 4, 5, 6};
        int target = 6;

        ListNode listNode = new ListNode(testCase);
        System.out.println(listNode);
        Solution solution = new Solution();
        System.out.println(solution.removeElements(listNode, target));
    }
}
