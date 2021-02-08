package array.src;

public class Main {
    public static void main(String[] args) {
        // int 类型的包装类Integer，泛型不允许使用基本数据类型
        Array<Integer> a = new Array<Integer>();
//        a.addLast(1);
//        a.addLast(2);
//        a.addLast(3);
//        a.add(1, 10);
//        System.out.println(a);
//        a.add(2, 200);
//        System.out.println(a);
//        a.addFirst(300);
//        System.out.println(a);
//        a.remove(0);
//        System.out.println(a);
//        a.add(5, 300);

        for (int i = 0; i < 10; i++) {
            a.addLast(i);
        }
        a.addLast(10);
        a.addFirst(-1);
        a.add(3, 100);
        System.out.println(a);


//        自定义类演示

        Array<Student> student = new Array<>();
        student.addLast(new Student("小明"));
        student.addLast(new Student("小红"));
        student.addLast(new Student("小刚"));
        System.out.println(student);
        student.add(2, new Student("小二"));
        System.out.println(student);
        // 基本演示
//        int[] arr = new int[10];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = i;
//        }
//        int[] scores = new int[]{100, 99, 66};
//        System.out.println(Arrays.toString(scores));
//        scores[0] = 80;
//        System.out.println(Arrays.toString(scores));
    }
}
