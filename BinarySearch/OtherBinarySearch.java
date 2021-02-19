package BinarySearch;

public class OtherBinarySearch {

    /**
     * 查找target如果存在元素，返回最小索引
     * 如果不存在元素，返回upper => 查找大于>target的最小索引
     * 综上 查找 >= target 的最小索引
     *
     * @param nums
     * @param target
     * @param <E>
     * @return
     */
    public <E extends Comparable<E>> int LowerCeil(E[] nums, E target) {
        int index = upper(nums, target);
        return index;
    }

    /**
     * 使用查找 >= target 的最小索引来进行二分查找
     *
     * @param nums
     * @param target
     * @param <E>
     * @return
     */
    public <E extends Comparable<E>> int BinarySearch(E[] nums, E target) {
        int l = LowerCeil(nums, target);
        // 如果 nums[l] == target，则返回 l；否则返回 -1
        // 注意，求解 >= target 的最小值索引，结果可能是 nums.length，不是合法索引
        // 所以，我们要对 l 的合法性进行判断，即确定 l < nums.length
        if (l < nums.length && nums[l].compareTo(target) == 0)
            return l;
        return -1;
    }


    /**
     * 查找大于>target的最小索引
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
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid].compareTo(target) >= 0) {
                // target mid
                // 索引mid的值，大于target，查找范围就变成了[l, mid]
                r = mid;
            } else if (nums[mid].compareTo(target) < 0) {
                // mid target
                // 索引mid的值都小于target，索引mid以及左边的所有值都小于target，都舍弃
                // 即使索引mid的值等于target也要舍弃
                l = mid + 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
//        Integer[] arr = {-1, 0, 3, 5, 9, 12};
//        int result = (new BinarySearchLowerCeil()).LowerCeil(arr, 12);
//        System.out.println(result);
        Integer[] data = {0, 1, 1, 1, 3, 3, 5, 5};
        System.out.println((new OtherBinarySearch().BinarySearch(data, 4)) + " ");
        System.out.println((new OtherBinarySearch().BinarySearch(data, 5)) + " ");
        for (int i = 0; i <= 6; i++) {
            System.out.print((new OtherBinarySearch().BinarySearch(data, i)) + " ");
        }
        System.out.println();
    }
}
