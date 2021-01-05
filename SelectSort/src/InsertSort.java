import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class InsertSort {
    private InsertSort() {

    }

    public static <E> void swap(E[] arr, int i, int j) {
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static <E extends Comparable> E[] sort(E[] arr) {
        // 优化
        // 减少swap的次数，使用暂存值以及比较暂存值与索引j-1的值
        for (int i = 0; i < arr.length; i++) {
            E temp = arr[i]; // 暂存索引i的值
            int j; // 需要将索引i的值，赋值的索引
            for (j = i; j - 1 >= 0; j--) {
                if (temp.compareTo(arr[j - 1]) < 0) { // 如果暂存的索引i的值比索引j-1小，将索引j-1的值赋值给j，此时索引j与索引j-1值相同
                    arr[j] = arr[j - 1];
                } else {
                    // 如果暂存的索引i的值比索引j -1的值大，则找到暂存值赋值的位置
                    break;
                }
            }
            arr[j] = temp;
        }
        return arr;
    }

    public static <E extends Comparable> E[] sort2(E[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j - 1 >= 0; j--) {
                if (arr[j].compareTo(arr[j - 1]) < 0) {
                    swap(arr, j - 1, j);
                } else {
                    break;
                }
            }


//            for (int j = i + 1; j > 0; j--) {
//                if (j <= arr.length - 1 && arr[j].compareTo(arr[j - 1]) < 0) {
//                    swap(arr, j - 1, j);
//                } else {
//                    break;
//                }
//            }
        }
        return arr;
    }

    public static void main(String[] args) {
//        Integer[] arr = {2, 3, 5, 7, 1, 8, 9, 0, 2};
//        Integer[] result = InsertSort.sort(arr);
//        System.out.println(Arrays.toString(result));


        int[] dataSize = {10000, 100000}; // 大规模测试

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

//            for (int i = 0; i < 10; i++) {
//                SortingHelper.sortTest("InsertSort", all[i]);
//            }
            SortingHelper.sortTest("InsertSort", all[0]);
        }
    }
}
