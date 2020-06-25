package thread;

public class WindowTest2 {
    public static void main(String[] args) {
        Thread t1 = new Window();
        t1.setName("窗口1");
        Thread t2 = new Window();
        t2.setName("窗口2");
        Thread t3 = new Window();
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}

class Window extends Thread {
    private static int ticket = 100;

    @Override
    public void run() {
        while (true) {
            show();
        }
    }

    private static synchronized void show() {   // 此种方式，同步锁是Window.class，唯一。
//    private synchronized void show() {        // 此种方式不行，因为同步锁是this，不唯一。
        if (ticket > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "售出了" + ticket);
            ticket--;
        }
    }
}
