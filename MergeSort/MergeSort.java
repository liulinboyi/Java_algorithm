package MergeSort;

import java.util.Arrays;

public class MergeSort {
    private MergeSort() {

    }

    public static <E extends Comparable<E>> E[] sort(E[] arr) {
        sort(arr, 0, arr.length - 1);
        return arr;
    }

    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r) {
        if (l >= r) return;
        // int mid = (l + r) / 2;
        // l与r都是整形，l+r有可能整形溢出

        int mid = l + (r - l) / 2;
        sort(arr, l, mid);
        sort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    // 合并两个有序的区间
    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r) {
        // copyOfRange 区间是前闭后开区间
        // 新的数组索引范围是[0,r-l]，l与r与新的数组索引范围并非对应关系，他们是有l偏移量的
        E[] temp = Arrays.copyOfRange(arr, l, r + 1);

        // l = 0, r = 7, mid = (0 + 7) / 2 = 3
        // i    mid j     r
        // |     |  |     |
        // 1 2 4 5  3 6 7 8
        //
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                // arr[l]存储在temp[0]的位置,存在l偏移量
                arr[k] = temp[j - l];
                j++;
            } else if (j > r) {
                arr[k] = temp[i - l];
                i++;
            } else if (temp[i - l].compareTo(temp[j - l]) <= 0) {
                arr[k] = temp[i - l];
                i++;
            } else {
                arr[k] = temp[j - l];
                j++;
            }
        }
//        System.out.println(arr);
    }


    private static Integer[] test1() {
        Integer[] arr = {1, 2, 4, 5, 3, 3, 6, 7, 8};
        int l = 0;
        int r = arr.length - 1;
        merge(arr, l, 3, r);
        return arr;
    }

    private static <E> void print(E[] arr) {
        StringBuilder str = new StringBuilder();
        str.append("[ ");
        for (int i = 0; i < arr.length; i++) {
            str.append(arr[i]);
            if (i < arr.length - 1) {
                str.append(", ");
            }
        }
        str.append(" ]");
        System.out.println(str.toString());
    }

    public static void main(String[] args) {

//        Integer[] res1 = test1();
//        print(res1);

        Integer[] arr = {1, 3, 4, 7, 2, 6, 9, 0};
//        Integer[] arr = {2, 1, 3};
        sort(arr);
        print(arr);
        System.out.println(SortingHelper.isSorted(arr));

    }

}
