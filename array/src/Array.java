package array.src;

public class Array<E> {
    // 变量本质表示数据，而E泛型表示类型
    private E[] data; // 增删改查
    private int size; // 数组实际大小，指向数组中第一个没有元素的位置，向数组末尾添加元素，就是在size这个位置添加元素

    /**
     * 构造函数传入数组的容量capacity构造Array
     *
     * @param capacity
     */
    public Array(int capacity/* 容量*/) {
        // Object 类是任意类的父类
        data = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * 无参数的构造函数，默认数组容量capacity=10
     * 默认构造函数
     */
    public Array() {
        this(10);
    }

    /**
     * 获取数组中元素个数
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取数组容量
     *
     * @return
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 返回数组是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向数组末尾添加元素
     */
    public void addLast(E e) {
//        if (size == data.length) {
//            throw new IllegalArgumentException("AddLast failed.Array is full.");
//        }
//        data[size] = e;
//        size++;

        add(size, e);
    }

    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 动态数组能力
     *
     * @param newCapacity
     */
    private void resize(int newCapacity) {
        E[] newDate = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newDate[i] = data[i];
        }
        data = newDate;
    }

    /**
     * 向数组指定index位置，添加元素
     */
    public void add(int index, E e) {

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("AddLast failed.Require index > 0 and index <= size.");
        }
        if (size == data.length) {
//            throw new IllegalArgumentException("AddLast failed.Array is full.");
            resize(data.length * 2);
        }
//        for (int i = size + 1; i > index; i--) {
//            data[i] = data[i - 1];
//        }
        // 将index后面的元素向后移动一个索引
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * 获取index索引元素
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }
        return data[index];
    }

    public E getLast() {
        return get(size - 1);
    }

    public E getFirst() {
        return get(0);
    }

    /**
     * 修改index索引位置元素
     *
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        }
        data[index] = e;
    }

    /**
     * 查找是否包含元素
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            // 两个类对象值得比较，使用equals
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找包含元素的索引
     *
     * @param e
     * @return
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除索引元素，并返回删除的元素
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index is illegal");
        }
        E removed = data[index];
        // 将index后面的元素都向前移动一个索引
        for (int i = index; i < size; i++) {
            data[i] = data[i + 1];
        }
        size--;
        // 最后一个元素仍有值，现在置空
        data[size] = null; // loitering objects != memory leak
        // data类似于蓄水池，size类似于蓄水池刻度
        // 元素个数为1/4时，才缩容
        if (size == data.length / 4 && data.length / 2 != 0) {
            // data.length有可能为1，data.length / 2就会为0
            resize(data.length / 2);
        }
        return removed;
    }

    /**
     * 删除第一个索引元素
     *
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除最后一个索引元素
     *
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 如果元素e存在于数组则删除元素
     *
     * @param e
     */
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n", size, data
                .length));
        res.append("[");
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) { // 看是不是最后一个元素
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }
};
