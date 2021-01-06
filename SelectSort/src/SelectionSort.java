package SelectSort.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import compare.SortingHelper;

public class SelectionSort {
    private SelectionSort() {
    }

    public static Integer[] sort1(Integer[] array) {
        ArrayList<Integer> arr = new ArrayList(Arrays.asList(array));
        ArrayList<Integer> copyArray = new ArrayList();
        int count = arr.size();
        for (int i = 0; i < count; i++) {
            int miniIndex = -1;
            Integer min = arr.get(0);
            for (int j = 0; j < arr.size(); j++) {
                Integer cur = arr.get(j);
                if (cur <= min) {
                    min = cur;
                    miniIndex = j;
                }
            }
            copyArray.add(min);
            arr.remove(miniIndex);
        }
        // System.out.println(copyArray);
        return copyArray.toArray(new Integer[0]);
    }

    // extends 泛型约束 实现某一个接口Comparable，代表这个类可以比较的
    public static <E extends Comparable<E>> E[] sort3(E[] array) {
        // 循环不变量 arr[0...i)是有序的，arr[i...n)是无序的
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                // 前者小于后者
                if (array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }
        return array;
    }

    public static Integer[] sort2(Integer[] array) {
        // 循环不变量 arr[0...i)是有序的，arr[i...n)是无序的
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }
        return array;
    }

    public static <E> void swap(E[] array, int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static Integer[] sort(Integer[] array) {
        ArrayList<Integer> arr = new ArrayList(Arrays.asList(array));
        int count = arr.size();
        // 循环不变量 arr[0...i)是有序的，arr[i...n)是无序的
        for (int i = 0; i < count; i++) {
            int miniIndex = i;
            for (int j = i; j < arr.size(); j++) {
                Integer cur = arr.get(j);
                if (cur <= arr.get(miniIndex)) {
                    miniIndex = j;
                }
            }
            // 维持循环不变量
            Collections.swap(arr, miniIndex, i);
        }
        // System.out.println(arr);
        return arr.toArray(new Integer[0]);
    }

    public static void main(String[] args) {
        // Integer[] array = {2, 3, 4, 6, 1, 8, 9, 0, 2};
        // System.out.println(Arrays.toString(SelectionSort.sort2(array)));
        // System.out.println(Arrays.deepToString(SelectionSort.sort(array)));

        int[] dataSize = { 10000, 100000 }; // 大规模测试

        for (int n : dataSize) {

            ArrayList<ArrayList<Integer>> allList = new ArrayList<ArrayList<Integer>>();
            Integer[][] all = new Integer[10][]; // 二维数组
            for (int i = 0; i < 10; i++) {
                Integer[] data = ArrayGenerator.generateOrderArray(n);
                ArrayList<Integer> list = new ArrayList(Arrays.asList(data));
                // 数组乱序
                Collections.shuffle(list);
                Integer[] l = list.toArray(new Integer[0]);
                allList.add(list);
                all[i] = l;
            }

            System.out.println(n + "数组准备 ok");

            // for (int i = 0; i < 10; i++) {
            // SortingHelper.sortTest("SelectionSort", all[i]);
            // }
            SortingHelper.sortTest("SelectionSort", all[0]);
        }

    }
}
