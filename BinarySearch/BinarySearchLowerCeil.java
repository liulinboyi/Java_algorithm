package BinarySearch;

public class BinarySearchLowerCeil {

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
//            else {
//                while (mid >= l) {
//                    if (mid > 0 && nums[mid - 1].compareTo(target) == 0) {
//                        mid = mid - 1;
//                        r = mid;
//                    } else {
//                        l = mid;
//                        break;
//                    }
//                }
//            }
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
//        Integer[] arr = {-1, 0, 3, 5, 9, 12};
//        int result = (new BinarySearchLowerCeil()).LowerCeil(arr, 12);
//        System.out.println(result);
        Integer[] data = {0, 1, 1, 1, 3, 3, 5, 5};
        System.out.println((new BinarySearchLowerCeil().LowerCeil(data, 4)) + " ");
        System.out.println((new BinarySearchLowerCeil().LowerCeil(data, 5)) + " ");
        for (int i = 0; i <= 6; i++) {
            System.out.print((new BinarySearchLowerCeil().LowerCeil(data, i)) + " ");
        }
        System.out.println();
    }
}
