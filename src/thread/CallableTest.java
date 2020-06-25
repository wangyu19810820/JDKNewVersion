package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建Callable的实现类，重写call方法
 * 实例化Callable的实现类
 * 实例化FutureTask，并将Callable对象传入
 * 实例化Thread，并将FutureTask对象传入，并启动该Thread
 * 调用FutureTask对象的get方法，获取Callable对象的call方法的返回值
 */
public class CallableTest {

    public static void main(String[] args) {
        NumberCallable numberCallable = new NumberCallable();
        FutureTask<Integer> futureTask = new FutureTask<>(numberCallable);
        new Thread(futureTask).start();

        try {
            Integer sum = futureTask.get();
            System.out.println("总和为：" + sum);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
class NumberCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                sum += i;
                System.out.println(i);
            }
        }
        return sum;
    }
}