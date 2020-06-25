package thread;

public class SingletonTest {

}

class Bank {
    private static Bank instance = null;

    private Bank(){
    }

    public static Bank getInstance() {
        if (instance == null) {
            synchronized (Bank.class) {
                if (instance == null) {
                    instance = new Bank();
                }
            }
        }
        return instance;
    }
}
