package thread;

import java.util.concurrent.locks.ReentrantLock;

public class AccountTest {

    public static void main(String[] args) {
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        user1.start();
        user2.start();
        user3.start();
    }
}

class Bank1 {
    private static Bank1 instance = new Bank1();
    private ReentrantLock lock = new ReentrantLock(true);

    private Bank1() {
    }

    public static Bank1 getInstance() {
        return instance;
    }

    private int total = 0;

    public void tranfer(int money) {
        lock.lock();
        try {
            total += money;
            System.out.println(Thread.currentThread().getName() + "存入" + money + "，账户余额：" + total);
        } finally {
            lock.unlock();
        }
    }
}

class User extends Thread {

    @Override
    public void run() {
        Bank1 bank = Bank1.getInstance();
        for (int i = 0; i < 3; i++) {
            bank.tranfer(1000);
        }
    }
}