public class SortingHelper {
    private SortingHelper() {
    }

    public static <E extends Comparable<E>> boolean isSorted(E[] arr) {
        // 后一个比前一个大，排序成功
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1].compareTo(arr[i]) > 0) {
                return false;
            }
        }
        return true;
    }

    public static <E extends Comparable> void sortTest(String sortName, E[] arr) {
        Long startTime = System.nanoTime();
        Comparable[] result = null;
        if (sortName.equals("SelectionSort")) {
            result = SelectionSort.sort3(arr);
        } else if (sortName.equals("InsertSort")) {
            result = InsertSort.sort(arr);
        }
        Long endTime = System.nanoTime();
        if (!SortingHelper.isSorted(result)) {
            // 抛出错误，运行时异常
            throw new RuntimeException(sortName + "排序错误");
        }
        Double runTime = (endTime - startTime) / 1000 / 1000 / 1000.0; // 最后除以浮点数
        System.out.println(String.format("%s , n = %d : %f s", sortName, arr.length, runTime));
    }
}
