package thread;

public class WindowTest {

    public static int ticket = 100;
    public static Object object = new Object();

    public static void main(String[] args) {

        Runnable runnable = () -> {
            while (true) {
                synchronized (object) {
                    if (ticket > 0) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException exception) {
                            exception.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + "售出了" + ticket);
                        ticket--;
                    } else {
                        break;
                    }
                }
            }
        };

        Thread t1 = new Thread(runnable);
        t1.setName("窗口1");
        Thread t2 = new Thread(runnable);
        t2.setName("窗口2");
        Thread t3 = new Thread(runnable);
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
