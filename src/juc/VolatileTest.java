package juc;

public class VolatileTest {

    public static void main(String[] args) {
        VolatileRunnable r = new VolatileRunnable();
        new Thread(r).start();
        while (true) {
            if (r.isFlag()) {
                System.out.println("----------------");
                break;
            }
        }
    }
}

/**
 * volatile关键字，保证此变量在不同线程中，是内存可见的
 * 轻量级的内存同步策略，与synchronize相比，没有互斥性、原子性
 */
class VolatileRunnable implements Runnable {

    private volatile boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        flag = true;
        System.out.println("flag=" + flag);
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}