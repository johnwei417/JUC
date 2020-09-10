package Volatile;

/**
 * honglinwei created on 3/24/20 inside the package - PACKAGE_NAME
 */
public class volatileDemo {
    static volatile boolean flag = true;
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                flag = false;

            }
        }).start();

        new Thread(()->{
           while(flag){

           }
        }).start();
    }
}
