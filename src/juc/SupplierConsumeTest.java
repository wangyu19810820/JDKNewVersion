package juc;

public class SupplierConsumeTest {

    public static void main(String[] args) {
        Shop shop = new Shop();
        Supplier supplier = new Supplier(shop);
        Consume consume = new Consume(shop);

        new Thread(supplier, "生产者A").start();
        new Thread(supplier, "生产者B").start();
        new Thread(consume, "消费者C").start();
        new Thread(consume, "消费者D").start();
    }
}

class Shop {
    int product = 0;

    public synchronized void buy() {
        while (product >= 1) {
            System.out.println("货满");
            try {
                wait();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }

        product++;
        System.out.println(Thread.currentThread().getName() + "制造了货物，存货为：" + product);
        notifyAll();
    }

    public synchronized void sell() {
        while (product <= 0) {
            System.out.println("缺货");
            try {
                wait();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }

        product--;
        System.out.println(Thread.currentThread().getName() + "消费了货物，存货为：" + product);
        notifyAll();
    }
}

class Supplier implements Runnable {

    private Shop shop = new Shop();

    public Supplier(Shop shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            shop.buy();
        }
    }
}

class Consume implements Runnable {

    private Shop shop = new Shop();

    public Consume(Shop shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            shop.sell();
        }
    }
}