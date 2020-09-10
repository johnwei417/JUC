package waitAndNotify;

/**
 * honglinwei created on 3/30/20 inside the package - waitAndNotify
 */
public class printOddAndEvenWithWait {

    static Object object = new Object();
    static int counter = 0;
    public static void main(String[] args) throws InterruptedException {
       Thread t1 =  new Thread(()->{
            while(counter <= 100){
                synchronized (object){
                    object.notify();
                    System.out.println(Thread.currentThread().getName()+": "+counter++);

                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        },"Even");

        Thread t2 =  new Thread(()->{
            while(counter <= 100){
                synchronized (object){
                    object.notify();
                    System.out.println(Thread.currentThread().getName()+": "+counter++);

                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        },"Odd");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

    }


}
