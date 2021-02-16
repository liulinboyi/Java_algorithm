package QuickSort;

public class QuickSort {
    public QuickSort() {

    }

    public static <E extends Comparable> E[] sort(E[] arr) {
        sort(arr, 0, arr.length - 1);
        return arr;
    }

    private static <E extends Comparable> void sort(E[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int p = partition(arr, l, r);
        sort(arr, l, p - 1);
        sort(arr, p + 1, r);
    }

    private static <E extends Comparable> int partition(E[] arr, int l, int r) {

        // [l+1,...,j] < v
        // [j+1,...,i-1] >= v
        int j = l;
        int i = j + 1;
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
        Integer[] arr = ArrayGenerator.generateRandomArray(count);
        SortingHelper.sortTest("QuickSort", arr);


    }

}
