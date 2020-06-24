package thread;

/**
 * 静态currentThread()获取当前线程
 * getName()获取线程名
 * setName()设置线程名
 * yield()放弃当前CPU的执行权
 * join()线程A中调用线程B的join方法，线程A进入阻塞状态，直到线程B执行完毕，才结束阻塞状态
 * stop()结束此线程
 * 静态sleep()当前线程休眠指定毫秒数
 * isAlive()判断线程是否存活
 * getPriority()setPriority()获取设置线程优先级Thread.MAX_PRIORITY 10 Thread.NORM_PRIORITY 5 Thread.MIN_PRIORITY 1
 */
public class ThreadMethodTest {

    public static void main(String[] args) {
        MyThread1 t1 = new MyThread1("线程1");
//        t1.setName("线程一");
        t1.setPriority(Thread.MIN_PRIORITY);
        t1.start();

        Thread.currentThread().setName("主线程");
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getPriority() + ":" + i);
            }
//            if (i == 20) {
//                try {
//                    t1.join();
//                } catch (InterruptedException exception) {
//                    exception.printStackTrace();
//                }
//            }
        }
        System.out.println(t1.isAlive());
    }

}

class MyThread1 extends Thread {

    public MyThread1(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getPriority() + ":" + i);
            }
//            if (i % 20 == 0) {
//                this.yield();
//            }
        }
    }
}