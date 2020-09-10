package DeadLock;

/**
 * honglinwei created on 3/30/20 inside the package - DeadLock
 */
public class DeadLock implements Runnable {
    static Object o1 = new Object();
    static Object o2 = new Object();


    int flag = 0;
    public static void main(String[] args) {

        DeadLock deadLock1 = new DeadLock();
        DeadLock deadLock2 = new DeadLock();
        deadLock1.flag = 0;
        deadLock2.flag = 1;
        Thread t1 = new Thread(deadLock1);
        Thread t2 = new Thread(deadLock2);
        t1.start();
        t2.start();
    }


    @Override
    public void run() {
        if(flag == 0) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" get lock 1");
                synchronized (o2){
                    System.out.println(Thread.currentThread().getName() +" get lock 2");
                }
            }
        }

        if(flag == 1){
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" get lock 1");
                synchronized (o1){
                    System.out.println(Thread.currentThread().getName() +" get lock 2");
                }
            }
        }
    }
}

