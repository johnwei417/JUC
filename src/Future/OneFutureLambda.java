package Future;

import java.util.Random;
import java.util.concurrent.*;


/**
 * honglinwei created on 3/29/20 inside the package - PACKAGE_NAME
 */
public class OneFutureLambda {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        Callable<Integer> callable = ()->{
//            Thread.sleep(3000);
//            return new Random().nextInt();
//        };
       // Future<Integer> future = executorService.submit(callable);
        Future<Integer> future1 = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(3000);
                return new Random().nextInt();
            }
        });
        try {
            System.out.println(future1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }

}
