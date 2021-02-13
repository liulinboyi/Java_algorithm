package LeetCode.solution203;

public class sum {
    public static int sum(int[] arr) {
        return sums(arr, 0);
    }

    // arr[l] + [l,...n]
    private static int sums(int[] arr, int l) {
        if (l == arr.length) {
            return 0;
        } else {
            return arr[l] + sums(arr, l + 1);
        }
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(sum(arr));
    }
}
