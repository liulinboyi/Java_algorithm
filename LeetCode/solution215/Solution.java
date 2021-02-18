package LeetCode.solution215;

import java.util.Random;

public class Solution {
    public int findKthLargest(int[] nums, int k) {
        int min = nums.length - k; // 转换成数组中第K个最小元素
        Random ran = new Random();
        int result = findKthLargest(nums, 0, nums.length - 1, min, ran);
        System.out.println(result);
        return result;
    }

    private int findKthLargest(int[] nums, int l, int r, int min, Random ran) {
        int p = partition(nums, l, r, ran);
        if (p == min) {
            return nums[p];
        } else if (p > min) {
            return findKthLargest(nums, l, p - 1, min, ran);
        } else {
            return findKthLargest(nums, p + 1, r, min, ran);
        }
    }

    private int partition(int[] nums, int l, int r, Random ran) {
        // [l, j - 1] < v
        // [j + 1, i - 1] >= v
        // random 默认右边为开区间
        int ram = l + ran.nextInt(r - l + 1);
        swap(nums, l, ram);
        int j = l;
        int i = j + 1;
        while (i <= r) {
            if (nums[i] < nums[l]) {
                j++;
                swap(nums, j, i);
            }
            i++;
        }
        swap(nums, l, j);
        return j;
    }

    private void swap(int[] arr, int l, int r) {
        int TEMP = arr[l];
        arr[l] = arr[r];
        arr[r] = TEMP;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 5, 6, 4};
        int target = 2;
        (new Solution()).findKthLargest(arr, target);
    }
}
