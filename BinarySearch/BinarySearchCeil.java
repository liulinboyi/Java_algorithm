package BinarySearch;

public class BinarySearchCeil {

    /**
     * 查找target如果存在元素，返回最大索引
     * 如果不存在元素，返回upper
     *
     * @param nums
     * @param target
     * @param <E>
     * @return
     */
    public <E extends Comparable<E>> int ceil(E[] nums, E target) {
        int index = upper(nums, target);
//        if (index == nums.length) { // 如果在数组中找你不到upper
//            if (nums[index - 1].compareTo(target) == 0) {
//                // 判断一下，数组最后索引的值，是否为target是的话，返回索引
//                return index - 1;
//            }
//            return index;
//        }
//        if (index == 0) { // 如果索引为0，则直接返回upper的值
//            return index;
//        }
//        if (nums[index - 1].compareTo(target) == 0) {
//            // 如果索引在(0,nums.length)之间，则判断当前upper索引前一个是否与target相等
//            return index - 1;
//        } else {
//            return index;
//        }
        if (index > 0 && nums[index - 1].compareTo(target) == 0) {
            return index - 1;
        } else {
            return index;
        }
    }


    /**
     * 查找大于>target的最小值
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
            if (nums[mid].compareTo(target) > 0) {
                // target mid
                // 索引mid的值，大于target，查找范围就变成了[l, mid]
                r = mid;
            } else if (nums[mid].compareTo(target) <= 0) {
                // mid target
                // 索引mid的值都小于target，索引mid以及左边的所有值都小于target，都舍弃
                // 即使索引mid的值等于target也要舍弃
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
        int result = (new BinarySearchCeil()).ceil(arr, 13);
        System.out.println(result);
        Integer[] data = {1, 1, 3, 3, 5, 5};
        for (int i = 0; i <= 6; i++) {
            System.out.print((new BinarySearchCeil().ceil(data, i)) + " ");
        }
        System.out.println();
    }
}
