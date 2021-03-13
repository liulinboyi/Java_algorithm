package Bloomfilter;

import java.util.BitSet;
import java.util.Random;

public class Bloomfilter<E> {
    private final int size; // 总共元素大小
    private final int has_num; // 一个元素分多少二机制位
    // Bitset类创建一种特殊类型的数组来保存位值
    // BitSet中数组大小会随需要增加。这和位向量（vector of bits）比较类似
    private BitSet bit_array;
    private Random prng = new Random();

    public Bloomfilter(int size, int hash_num) {
        this.size = size;
        this.has_num = hash_num;
        // 允许用户指定初始大小。所有位初始化为0。
        this.bit_array = new BitSet(size);
    }

    public int hash(int seed, E value) {
        int next = prng.nextInt();
        return next < 0 ? -next : next;
    }

    public void init(E value) {
        int code = value.hashCode();
        prng.setSeed(code);
    }

    public void add(E value) {
        init(value);
        for (int i = 0; i < this.has_num; i++) {
            int next = hash(i, value);
            int index = next % this.size;
            // 将指定索引处的位设置为 true
            this.bit_array.set(index);
        }
    }

    public String lookup(E value) {
        init(value);
        for (int i = 0; i < has_num; i++) {
            int next = hash(i, value);
            int index = next % this.size;
            if (this.bit_array.get(index)) {
                return "Probably";
            }
        }
        return "Nope";
    }

    public static void main(String[] args) {
        Bloomfilter f = new Bloomfilter(200000, 7);

// Add elements to the filter
// it uses Object.hashCode() internally, so you can add objects of any type
        f.add("hello");
        f.add("hello, world!");

// Check if an element is in the filter
        System.out.println(f.lookup("hello")); // true
        System.out.println(f.lookup("hello, world!")); // false
        System.out.println(f.lookup("world")); // false

    }
}
