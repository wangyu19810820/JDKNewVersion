package juc;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListTest {

    public static void main(String[] args) {
        CopyOnWriteArrayListRunnable r = new CopyOnWriteArrayListRunnable();
        for (int i = 0; i < 10; i++) {
            new Thread(r).start();
        }
    }
}

/**
 * CopyOnWriteArrayList可以在迭代的时候修改列表
 * 修改表操作效率低下，并发迭代多时可考虑
 */
class CopyOnWriteArrayListRunnable implements Runnable {
    private static List<String> list = new CopyOnWriteArrayList<>();
//    private static List<String> list = new ArrayList<>();

    static {
        list.add("aa");
        list.add("bb");
        list.add("cc");
    }

    @Override
    public void run() {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            list.add("dd");
            list.remove("aa");
            list.set(1, "ee");
        }
    }
}
