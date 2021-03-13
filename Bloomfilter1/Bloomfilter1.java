package Bloomfilter1;

import java.util.BitSet;
import java.util.Random;

import java.util.Iterator;

public class Bloomfilter1 implements Cloneable {
    private BitSet hashes;
    private RandomInRange prng;
    private int k; // Number of hash functions
    private static final double LN2 = 0.6931471805599453; // ln(2)

    /**
     * Create a new bloom filter.
     *
     * @param n Expected number of elements
     * @param m Desired size of the container in bits
     **/
    public Bloomfilter1(int n, int m) {
        k = (int) Math.round(LN2 * m / n);
        if (k <= 0) k = 1;
        this.hashes = new BitSet(m);
        this.prng = new RandomInRange(m, k);
    }

    /**
     * Create a bloom filter of 1Mib.
     *
     * @param n Expected number of elements
     **/
    public Bloomfilter1(int n) {
        this(n, 1024 * 1024 * 8);
    }

    /**
     * Add an element to the container
     **/
    public void add(Object o) {
        prng.init(o);
        for (RandomInRange r : prng) hashes.set(r.value);
    }

    /**
     * If the element is in the container, returns true.
     * If the element is not in the container, returns true with a probability ≈ e^(-ln(2)² * m/n), otherwise false.
     * So, when m is large enough, the return value can be interpreted as:
     * - true  : the element is probably in the container
     * - false : the element is definitely not in the container
     **/
    public boolean contains(Object o) {
        prng.init(o);
        for (RandomInRange r : prng)
            if (!hashes.get(r.value))
                return false;
        return true;
    }

    /**
     * Removes all of the elements from this filter.
     **/
    public void clear() {
        hashes.clear();
    }

    /**
     * Create a copy of the current filter
     **/
    public Bloomfilter1 clone() throws CloneNotSupportedException {
        return (Bloomfilter1) super.clone();
    }

    /**
     * Generate a unique hash representing the filter
     **/
    public int hashCode() {
        return hashes.hashCode() ^ k;
    }

    /**
     * Test if the filters have equal bitsets.
     * WARNING: two filters may contain the same elements, but not be equal
     * (if the filters have different size for example).
     */
    public boolean equals(Bloomfilter1 other) {
        return this.hashes.equals(other.hashes) && this.k == other.k;
    }

    /**
     * Merge another bloom filter into the current one.
     * After this operation, the current bloom filter contains all elements in
     * other.
     **/
    public void merge(Bloomfilter1 other) {
        if (other.k != this.k || other.hashes.size() != this.hashes.size()) {
            throw new IllegalArgumentException("Incompatible bloom filters");
        }
        this.hashes.or(other.hashes);
    }

    private class RandomInRange
            implements Iterable<RandomInRange>, Iterator<RandomInRange> {

        private Random prng;
        private int max; // Maximum value returned + 1
        private int count; // Number of random elements to generate
        private int i = 0; // Number of elements generated
        public int value; // The current value

        RandomInRange(int maximum, int k) {
            max = maximum;
            count = k;
            prng = new Random();
        }

        public void init(Object o) {
            prng.setSeed(o.hashCode());
        }

        public Iterator<RandomInRange> iterator() {
            i = 0;
            return this;
        }

        public RandomInRange next() {
            i++;
            value = prng.nextInt() % max;
            if (value < 0) value = -value;
            return this;
        }

        public boolean hasNext() {
            return i < count;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Bloomfilter1 f = new Bloomfilter1(200000, 7);

// Add elements to the filter
// it uses Object.hashCode() internally, so you can add objects of any type
        f.add("hello");
        f.add("hello, world!");

// Check if an element is in the filter
        System.out.println(f.contains("hello")); // true
        System.out.println(f.contains("hello, world!")); // false
        System.out.println(f.contains("world")); // false

    }
}
