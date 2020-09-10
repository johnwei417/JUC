package Semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * honglinwei created on 3/24/20 inside the package - PACKAGE_NAME
 */
public class SemaphoreDemo{
    static Semaphore semaphore = new Semaphore(3, true);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        for(int i =0; i < 100; i ++){
            executorService.submit(()->{
                try{
                    semaphore.acquire();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " get permit");
                try{
                    Thread.sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" release a permit");
                semaphore.release();
            });
        }
        executorService.shutdown();
    }

}

