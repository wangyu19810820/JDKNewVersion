package juc;

public class CompareAndSwapTest {

    public static void main(String[] args) {
        CompareAndSwap cas = new CompareAndSwap();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                int expectValue = cas.get();
                boolean b = cas.compareAndSet(expectValue, (int)(Math.random() * 100));
                System.out.println(b);
            }).start();
        }
    }
}

/**
 * 模拟CAS算法
 */
class CompareAndSwap {
    private int value;

    // 获取
    public synchronized int get() {
        return value;
    }

    // 比较
    public synchronized int compareAndSwap(int expectValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectValue) {
            value = newValue;
        }
        return oldValue;
    }

    // 设置
    public synchronized boolean compareAndSet(int expectValue, int newValue) {
        return expectValue == compareAndSwap(expectValue, newValue);
    }
}