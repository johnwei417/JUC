package CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * honglinwei created on 3/24/20 inside the package - PACKAGE_NAME
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("all threads arrived barrier, resume all threads");
            }
        });

        for (int i = 0; i < 10 ; i++) {
            new Thread(()->{
                try {
                    Thread.sleep((long) (Math.random()*10000));
                    System.out.println(Thread.currentThread().getName()+" is waiting");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName()+" finish waiting");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        //System.out.println("finish");
    }
}
