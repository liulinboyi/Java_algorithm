package QuickSort;

import java.util.Random;

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

        int count = 10000000;
//        Integer[] arr = ArrayGenerator.generateRandomArray(count);
//        SortingHelper.sortTest("QuickSort", arr);


        Integer[] arr = ArrayGenerator.generateOrderArray(count);
        SortingHelper.sortTest("QuickSort", arr);


    }

}
