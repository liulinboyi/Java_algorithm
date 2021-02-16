package LineSearch.src;

public class LinerSearch<E> {
    // 将构造函数设置私有
    private LinerSearch() {
    }

    public static <E> int search(E[] data, E target) {
        for (int i = 0; i < data.length; i++) {
            // == 判断的是引用相等 data[i] == target
            // equals 值相等
            // String 是一个包装类，比较应该用 equals比较，不要 使用 ==
            if (data[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    // 8中基本数据类型
    // short int long float double byte char boolean
    // 泛型需要使用类对象，不能使用基本数据类型
    // 每个基本数据类型都有对应的包装类
    // Short Integer Long Float Double Byte Character Boolean
    //
    public static void main(String[] args) {
//        Integer[] data = {24, 18, 12, 9, 16, 66, 32, 4};
        int[] dataSize = {1000000, 10000000};
        for (int n : dataSize) {
            int count = 100;
            Integer[] data = ArrayGenerator.generateOrderArray(n);
            Long startTime = System.nanoTime();
            for (int i = 0; i < count; i++) {
                LinerSearch.<Integer>search(data, n);
            }
            Long endTime = System.nanoTime(); // 纳秒 1000纳秒是一个微秒，1000个微秒是一个毫秒
            Double runTime = (endTime - startTime) / 1000 / 1000 / 1000.0; // 最后除以浮点数
            System.out.println("n = " + n + ", 100 runs : " + runTime + " s");
        }
        // System.out.println("结果是： " + result);
        // sout
        /*
        int result2 = LinerSearch.search(data, 666);
        System.out.println("结果是： " + result2);

        Student[] students = {new Student("Albert"), new Student("Tom"), new Student("Alice")};
        Student Albert = new Student("Albert");
        int result3 = LinerSearch.search(students, Albert);
        System.out.println("结果是： " + result3);
         */
    }
}
