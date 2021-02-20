package LeetCode.solution875;

import java.util.Arrays;

public class Solution {
    public int minEatingSpeed(int[] piles, int H) {
        int result = BinarySearch(piles, H);
        return result;
    }

    public int BinarySearch(int[] nums, int target) {
        int l = 1;
//        int r = Max(nums);
        int r = Arrays.stream(nums).max().getAsInt();
        // y轴吃掉所有香蕉的时间
        // |       \
        // |        \
        // |         \
        // |          \
        // _________________________> x轴每小时吃香蕉的速速

        while (l < r) {
            int mid = l + (r - l) / 2; // 每小时吃的速度
            int time = eatTime(nums, mid);
            if (time > target) {
                // mid > target
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    private int eatTime(int[] nums, int speed) {
        int time = 0;
        for (int i = 0; i < nums.length; i++) {
            time += Math.ceil(nums[i] / (float) speed);
        }
        return time;
    }

    private int Max(int[] nums) {
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] data = {3, 6, 7, 11};
        int H = 8;
        int res = (new Solution()).minEatingSpeed(data, H);
        System.out.println(res);

        int[] data1 = {30, 11, 23, 4, 20};
        int H1 = 5;
        int res1 = (new Solution()).minEatingSpeed(data1, H1);
        System.out.println(res1);

        int[] data2 = {30, 11, 23, 4, 20};
        int H2 = 6;
        int res2 = (new Solution()).minEatingSpeed(data2, H2);
        System.out.println(res2);

        int[] data3 = {100};
        int H3 = 99;
        int res3 = (new Solution()).minEatingSpeed(data3, H3);
        System.out.println(res3);

    }
}
