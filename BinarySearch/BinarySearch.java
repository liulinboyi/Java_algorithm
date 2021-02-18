package BinarySearch;

public class BinarySearch {
    private BinarySearch() {

    }

    /**
     * 二分搜索前提就是数组是有序的
     *
     * @param data
     * @param target
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> int searchR(E[] data, E target) {
        return searchR(data, 0, data.length - 1, target);
    }

    private static <E extends Comparable<E>> int searchR(E[] data, int l, int r, E target) {
        if (l > r) {
            return -1;
        }
        int mid = l + (r - l) / 2;

        if (target.compareTo(data[mid]) == 0) {
            return mid;
        } else if (target.compareTo(data[mid]) > 0) {
            // target > data[mid]
            return searchR(data, mid + 1, r, target);
        } else { //  target < data[mid]
            return searchR(data, l, mid - 1, target);
        }
    }

    public static <E extends Comparable<E>> int search(E[] data, E target) {
        int l = 0;
        int r = data.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (target.compareTo(data[mid]) > 0) {
                l = mid + 1;
            } else if (target.compareTo(data[mid]) < 0) {
                r = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int count = 10000;
//        Integer[] arr = ArrayGenerator.generateOrderArray(count);
        Integer[] arr = {-1, 0, 3, 5, 9, 12};
        int result = search(arr, 9);
        System.out.println(result);
    }

}
