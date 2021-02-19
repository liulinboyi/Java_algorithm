package BinarySearch;

public class BinarySearchLower {

    /**
     * 查找小于target的最大值
     * 错误示范
     *
     * @param nums
     * @param target
     * @param <E>
     * @return
     */
    public <E extends Comparable<E>> int lower(E[] nums, E target) {
        int l = -1;
        int r = nums.length - 1; // 需要多留出来一个位置
        // 在data[l,r]中寻找解
        while (l < r) {
            // 错误示范，因为计算机是向下取整，所以当l与r相差1时，
            // 例如[l:0,r:1],mid = 0;
            // 碰巧此时nums[mid] < target; 则 l=mid=0
            // 陷入死循环
            int mid = l + (r - l) / 2;
            if (nums[mid].compareTo(target) >= 0) {
                // target mid
                // 索引mid的值，大于target，查找范围就变成了[l, mid]
                r = mid - 1;
            } else if (nums[mid].compareTo(target) < 0) {
                // mid target
                // 索引mid的值都小于target，索引mid以及左边的所有值都小于target，都舍弃
                l = mid;
//                if (l == mid) { // 搜索空间不变
//                    if (nums[r].compareTo(target) >= 0) {
//                        r = r - 1;
//                    } else {
//                        l = l + 1;
//                    }
//                } else {
//                    l = mid;
//                }
            }
        }
        return l;
    }


    public <E extends Comparable<E>> int lower1(E[] nums, E target) {
        int l = -1;
        int r = nums.length - 1; // 需要多留出来一个位置
        // 在data[l,r]中寻找解
        while (l < r) {
//            int mid = l + (r - l) / 2; // 计算机默认下取整
//            int mid;
//            if (r - l == 1) {
//                mid = l + (r - l + 1) / 2;
//            } else {
//                mid = l + (r - l) / 2; // 计算机默认下取整
//            }
            int mid = l + (r - l + 1) / 2; // 计算机默认下取整
            if (nums[mid].compareTo(target) >= 0) {
                // target mid
                // 索引mid的值，大于target，查找范围就变成了[l, mid]
                r = mid - 1;
            } else if (nums[mid].compareTo(target) < 0) {
                // mid target
                // 索引mid的值都小于target，索引mid以及左边的所有值都小于target，都舍弃
                l = mid;
            }
        }
        return l;
    }

    public static void main(String[] args) {
//        Integer[] arr = {-1, 0, 3, 5, 9, 12};
//        int result = (new BinarySearchLower()).lower(arr, 9);
//        System.out.println(result);

        Integer[] data = {1, 1, 3, 3, 5, 5};
        System.out.println((new BinarySearchLower().lower1(data, 2)) + " ");
        for (int i = 0; i <= 6; i++) {
            System.out.print((new BinarySearchLower().lower1(data, i)) + " ");
        }
        System.out.println();
    }
}
