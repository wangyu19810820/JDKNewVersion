package future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTest1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int i = 1/10;
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            return 100;
        });
        //        System.out.println(future.join());
        System.out.println(future.get());
        System.out.println("OKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
    }
}
