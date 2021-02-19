package BinarySearch;

public class BinarySearchUpper {

    /**
     * 查找大于target的最小值
     *
     * @param nums
     * @param target
     * @param <E>
     * @return
     */
    public <E extends Comparable<E>> int upper(E[] nums, E target) {
        int l = 0;
        int r = nums.length; // 需要多留出来一个位置
        // 在data[l,r]中寻找解

        // l        mid        r
        // |         |         |
        // 0   1  2  3  4  5   6
        // -1, 0, 3, 5, 9, 12
        // nums[mid]: 5; target: 1; => target < nums[mid] =>  r = mid = 3;

        // l   mid   r
        // |   |     |
        // 0   1  2  3  4  5   6
        // -1, 0, 3, 5, 9, 12
        // nums[mid]: 0; target: 1; => target > nums[mid] => l = mid + 1 = 1 + 1 = 2;

        //       mid
        //        l  r
        //        |  |
        // 0   1  2  3  4  5   6
        // -1, 0, 3, 5, 9, 12
        // nums[mid]: 3; target: 1; => target < nums[mid] => r = mid = 2;

        //       mid
        //        r
        //        l
        //        |
        // 0   1  2  3  4  5   6
        // -1, 0, 3, 5, 9, 12

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid].compareTo(target) > 0) {
                // target mid
                // 索引mid的值，大于target，查找范围就变成了[l, mid]
                r = mid;
            } else if (nums[mid].compareTo(target) <= 0) {
                // mid target
                // 索引mid的值都小于target，索引mid以及左边的所有值都小于target，都舍弃
                l = mid + 1;
            }
        }
        return l;
//        if (r == nums.length) {
//            return -1;
//        } else {
//            if (l == r) {
//                return l;
//            } else {
//                return -1;
//            }
//        }
    }

    public static void main(String[] args) {
        Integer[] arr = {-1, 0, 3, 5, 9, 12};
        int result = (new BinarySearchUpper()).upper(arr, 9);
        System.out.println(result);
//        Integer[] data = {1, 1, 3, 3, 5, 5};
//        for (int i = 0; i <= 6; i++) {
//            System.out.print((new BinarySearchUpper().upper(data, i)) + " ");
//        }
//        System.out.println();
    }
}
