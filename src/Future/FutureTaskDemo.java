package Future;

import java.util.concurrent.*;

/**
 * honglinwei created on 3/29/20 inside the package - PACKAGE_NAME
 */
public class FutureTaskDemo {
    public static void main(String[] args) {
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
//        new Thread(futureTask).start();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(futureTask);

        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
    class Task implements Callable{

        @Override
        public Object call() throws Exception {
            System.out.println("calculating ...");
            Thread.sleep(3000);
            int sum = 0 ;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            return sum;
        }
}
