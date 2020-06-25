package thread;

public class CommunicationTest {

    public static void main(String[] args) {
        PrintNumber p = new PrintNumber();
        Thread t1 = new Thread(p);
        t1.setName("线程1");
        Thread t2 = new Thread(p);
        t2.setName("线程2");

        t1.start();
        t2.start();
    }
}

/**
 * wait(),线程进入等待状态，释放锁
 * notify(),notifyAll(),唤醒一个或所有等待状态的线程
 * 三个方法都必须写在同步代码块或同步方法内，调用者必须上锁对象，都定义在Object中
 */
class PrintNumber implements Runnable {

    private int number = 1;
    private Object obj = new Object();

    @Override
    public void run() {
        while (true) {
//            synchronized (this) {
            synchronized (obj) {
//                this.notifyAll();
                obj.notifyAll();

                if (number <= 100) {
                    System.out.println(Thread.currentThread().getName() + ":" + number);
                    number++;

                    try {
//                        this.wait();
                        obj.wait();
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                } else {
                    break;
                }
            }
        }
    }
}
