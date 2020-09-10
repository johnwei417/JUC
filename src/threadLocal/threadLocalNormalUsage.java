package threadLocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * honglinwei created on 3/29/20 inside the package - threadLocal
 */
public class threadLocalNormalUsage {
   public static ExecutorService executorService = Executors.newFixedThreadPool(10);
   public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

   public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new threadLocalNormalUsage().date(finalI);
                    System.out.println(date);
                }
            });
        }
       executorService.shutdown();
    }
    public String date(int sec){
        Date date = new Date(1000 * sec);
        String  s = null;
        synchronized (threadLocalNormalUsage.class) {
            s = simpleDateFormat.format(date);
        }
        return s;
    }
}
