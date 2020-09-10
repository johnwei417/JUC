package countDownLatch;

import java.util.concurrent.*;

/**
 * honglinwei created on 3/24/20 inside the package - PACKAGE_NAME
 */
public class countDownLatchDemo {
    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(5);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i= 0; i < 5; i++ ){
            int finalI = i;
            Runnable r = new Runnable(){
                final int no = finalI + 1;
                @Override
                public void run() {
                    try {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }finally {
                        System.out.println("No: "+no + " finish count down");
                        countDownLatch.countDown();
                    }
                }
            };

            executorService.submit(r);
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("all tasks finished. resume main thread");
        executorService.shutdownNow();
    }


}
