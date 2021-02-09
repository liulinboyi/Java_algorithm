package stack;

import queue.ArrayQeueue;
import queue.Queue;

import java.util.Random;

public class Main {

    /**
     * 入队出队所需时间s
     *
     * @param q
     * @param op
     * @return
     */
    private static double testQueue(Queue<Integer> q, int op) {
        long start = System.nanoTime(); // 纳秒为单位


        Random random = new Random();
        for (int i = 0; i < op; i++) {
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < op; i++) {
            q.dequeue();
        }

        long end = System.nanoTime();
        return (end - start) / 1000 / 1000 / 1000.0;
    }

    public static void main(String[] args) {
//        ArrayStack<Integer> stack = new ArrayStack<>();
//        for (int i = 0; i < 5; i++) {
//            stack.push(i);
//            System.out.println(stack);
//        }
//        stack.pop();
//        System.out.println(stack);
//        System.out.println(stack.peek());
        int opCount = 100000;

        ArrayQeueue<Integer> arrayQeueue = new ArrayQeueue<>();
        System.out.println("ArrayQeueue: " + testQueue(arrayQeueue, opCount) + " s");

        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        System.out.println("LoopQueue: " + testQueue(loopQueue, opCount) + " s");

    }

}
