package thread;

public class SupplierConsumerTest {
    public static void main(String[] args) {
        Shop shop = new Shop();
        Supplier supplier = new Supplier(shop);
        supplier.setName("生产者1");
        Consumer consumer = new Consumer(shop);
        consumer.setName("消费者1");

        supplier.start();
        consumer.start();
    }
}

class Shop {
    private int productNum = 0;

    public synchronized void supplier() {
        if (productNum < 20) {
            productNum++;
            System.out.println(Thread.currentThread().getName() + "生产了一个产品，产品库存为：" + productNum);
            notifyAll();
        } else {
            try {
                wait();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    public synchronized void consumer() {
        if (productNum > 0) {
            productNum--;
            System.out.println(Thread.currentThread().getName() + "消费了一个产品，产品库存为：" + productNum);
            notifyAll();
        } else {
            try {
                wait();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }
}

class Supplier extends Thread {
    private Shop shop;
    public Supplier(Shop shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            shop.supplier();
        }
    }
}

class Consumer extends Thread {
    private Shop shop;
    public Consumer(Shop shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            shop.consumer();
        }
    }
}