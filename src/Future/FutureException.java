package Future;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

/**
 * honglinwei created on 3/29/20 inside the package - PACKAGE_NAME
 */
public class FutureException {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(20);


            Future<Integer> future = executorService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                   throw new IllegalArgumentException("Callable throw exception");
                }
            });



            try {
                for (int i = 0; i < 5; i++) {
                    System.out.println(i);
                    Thread.sleep(1000);
                }
                System.out.println(future.isDone());
                Integer integer = future.get();
                System.out.println(integer);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
                e.printStackTrace();
            } catch (ExecutionException e) {
                System.out.println("ExecutionException");
                e.printStackTrace();
            }
        }

}
