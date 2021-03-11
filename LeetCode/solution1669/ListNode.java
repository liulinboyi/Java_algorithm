package LeetCode.solution1669;

public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    public ListNode(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("arr cannot bo empty.");
        }
        this.val = arr[0];
        ListNode cur = this;

        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        ListNode cur = this;
        while (cur != null) {
            str.append(cur.val);
            str.append(" -> ");
            cur = cur.next;
        }
        str.append("NULL");
        return str.toString();
    }
}
