package Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * honglinwei created on 3/31/20 inside the package - Lock
 */
public class LockInterruptibly implements Runnable {
    private Lock lock  = new ReentrantLock();

    public static void main(String[] args) {
        LockInterruptibly lockInterruptibly = new LockInterruptibly();
        Thread t1 = new Thread(lockInterruptibly);
        Thread t2 = new Thread(lockInterruptibly);
        t1.start();
        t2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt();
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+ " trying to acquire the lock");
        try {
            lock.lockInterruptibly();
            try{
                System.out.println(Thread.currentThread().getName() +" got the lock");
                Thread.sleep(5000);
            }catch(InterruptedException e) {
                System.out.println(Thread.currentThread().getName()+ " Interrupted during the sleeping");
            }finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName()+ " release the lock");
            }
        } catch (InterruptedException ex) {
            //e.printStackTrace();
            System.out.println(Thread.currentThread().getName() + " Interrupted during the waiting");
        }
    }
}
