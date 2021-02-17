package QuickSort;

import java.util.Arrays;
import java.util.Random;

/**
 * 快排，会在排序，有序数组时，退化成O(n^2)
 * 最后期望优化至接近O(nlogn)
 */

public class QuickSort {
    public QuickSort() {

    }

    public static <E extends Comparable> E[] sort(E[] arr) {
        Random ram = new Random();
        sort(arr, 0, arr.length - 1, ram);
        return arr;
    }

    private static <E extends Comparable> void sort(E[] arr, int l, int r, Random ram) {
        if (l >= r) {
            return;
        }
        int p = partition(arr, l, r, ram);
        sort(arr, l, p - 1, ram);
        sort(arr, p + 1, r, ram);
    }

    private static <E extends Comparable> int partition(E[] arr, int l, int r, Random ra) {

        // [l+1,...,j] < v
        // [j+1,...,i-1] >= v
        int j = l;
        int i = j + 1;
        // 随机索引[l,r]之间
//        int ram = (int) (l + (Math.random() * (r - l + 1)));
//        System.out.println(ram);
        int ram = l + ra.nextInt(r - l + 1);
        swap(arr, ram, l);
        E v = arr[l];
        while (i <= r) {
            // arr[i] < v
            if (arr[i].compareTo(v) < 0) {
                j++;
                swap(arr, j, i);
            }
            i++;
        }
        swap(arr, l, j);
        return j;
    }


    /**
     * 双路快速排序算法
     *
     * @param arr
     * @param <E>
     * @return
     */
    public static <E extends Comparable> E[] sort2ways(E[] arr) {
        Random ram = new Random();
        sort2ways(arr, 0, arr.length - 1, ram);
        return arr;
    }

    private static <E extends Comparable> void sort2ways(E[] arr, int l, int r, Random ram) {
        if (l >= r) {
            return;
        }
        int p = partition2(arr, l, r, ram);
        sort2ways(arr, l, p - 1, ram);
        sort2ways(arr, p + 1, r, ram);
    }

    private static <E extends Comparable> int partition2(E[] arr, int l, int r, Random ra) {

        // [l+1,...,j] < v
        // [j+1,...,i-1] >= v
        // 随机索引[l,r]之间
        // int ram = (int) (l + (Math.random() * (r - l + 1)));
        // System.out.println(ram);
        int ram = l + ra.nextInt(r - l + 1);
        swap(arr, ram, l);

        int i = l + 1;
        int j = r;
        while (true) {
            // arr[i] < arr[l]
            while (i <= j && arr[i].compareTo(arr[l]) < 0) {
                i++;
            }
            while (j >= i && arr[j].compareTo(arr[l]) > 0) {
                j--;
            }
            if (i >= j) {
                break;
            }
            swap(arr, i, j);
            i++;
            j--;
        }
        swap(arr, l, j);
        return j;
    }

    private static <E> void swap(E[] arr, int i, int j) {
        E TEMP = arr[i];
        arr[i] = arr[j];
        arr[j] = TEMP;
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
//        Integer[] arr = {4, 6, 8, 2, 3, 1, 0};
//        sort(arr);
//        print(arr);

        int count = 100000;
        Integer[] arr = ArrayGenerator.generateRandomArray(count, 1);
        Integer[] arr1 = Arrays.copyOf(arr, arr.length);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);

        SortingHelper.sortTest("QuickSort2", arr);
        SortingHelper.sortTest("QuickSort", arr1);
        SortingHelper.sortTest("MergeSort", arr2);


//         Integer[] arr = ArrayGenerator.generateOrderArray(count);
//         SortingHelper.sortTest("QuickSort", arr);


    }

}
