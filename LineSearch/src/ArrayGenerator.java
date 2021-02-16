package LineSearch.src;

public class ArrayGenerator {
    private ArrayGenerator() {
    }

    public static Integer[] generateOrderArray(int n) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        return arr;
    }
}
