package countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * honglinwei created on 3/24/20 inside the package - PACKAGE_NAME
 */
public class countDownLatchDemo2 {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 5 ; i ++){
            int finalI = i;
            Runnable r = new Runnable(){
                final int no = finalI + 1;
                @Override
                public void run() {
                    System.out.println("ready, waiting to start");

                    try {
                        countDownLatch.await();
                        System.out.println("No. "+no+" start running");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            executorService.submit(r);
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Begin!");

        countDownLatch.countDown();

        executorService.shutdownNow();
    }
}
