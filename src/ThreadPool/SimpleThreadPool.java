package ThreadPool;

import java.util.concurrent.*;

/**
 * honglinwei created on 3/30/20 inside the package - ThreadPool
 */
public class SimpleThreadPool {
    static LinkedBlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingQueue<>(20);
    static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(20);
   static ExecutorService executorService = new ThreadPoolExecutor(2, 100, 2, TimeUnit.SECONDS, linkedBlockingQueue);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        for (int i = 0; i < 100 ; i++) {
//            int finalI = i;
            Runnable runnable =()->
            {
                try {
                    Thread.sleep(100);
                    System.out.println("hello");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            };


           // executorService.submit(runnable);
            ScheduledFuture future = scheduledExecutorService.scheduleAtFixedRate(runnable, 2, 5,TimeUnit.SECONDS);
//        }

        //executorService.shutdown();

        System.out.println(future.get());
        Thread.sleep(2000);

        scheduledExecutorService.shutdown();
    }
}
