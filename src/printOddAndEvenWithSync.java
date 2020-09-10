/**
 * honglinwei created on 3/23/20 inside the package - PACKAGE_NAME
 */
public class printOddAndEvenWithSync{

    static Object lock  = new Object();
    static int count;
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(count< 100){
                    synchronized (lock) {
                        if (count % 2 == 0) {
                            System.out.println(Thread.currentThread().getName() + ": " + count++);
                        }
                    }
                }
            }
        },"Even").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(count<100){
                    synchronized (lock) {
                        if (count % 2 != 0) {
                            System.out.println(Thread.currentThread().getName() + ": " + count++);
                        }
                    }
                }
            }
        },"Odd").start();
    }
}
