package Future;

import java.util.concurrent.*;

/**
 * honglinwei created on 3/29/20 inside the package - PACKAGE_NAME
 */
public class FutureTimeout {

    private static final Ad DEFUALT_AD =new Ad("default ad when no internet");

    private static final ExecutorService exec = Executors.newFixedThreadPool(10);
    static class Ad{
        String name;
        public Ad(String name){
            this.name = name;
        }
        @Override
        public String toString(){
            return "Ad{"+"name = '"+name+"'\' "+'}';
        }
    }

    static class FetchAdTask implements Callable<Ad> {

        @Override
        public Ad call() throws Exception {
            try{
                Thread.sleep(3000);
            }catch(InterruptedException e){
                System.out.println("sleep be interrupted");
                return new Ad("default ad when interrupted");
            }
            return new Ad("ad from trip");
        }
    }

    public void printAd(){
        Future<Ad> f = exec.submit(new FetchAdTask());
        Ad ad;
        try {
            ad = f.get(1000,TimeUnit.MICROSECONDS);
        } catch (InterruptedException e) {
            ad = new Ad("interrupted ad");
            e.printStackTrace();
        } catch (ExecutionException e) {
            ad = new Ad("execution exception ad");
            e.printStackTrace();
        } catch (TimeoutException e) {
            ad = new Ad("time out ad");
            System.out.println("cannot get ad");
            e.printStackTrace();
           boolean cancel =  f.cancel(true);
            System.out.println("cancel result: "+cancel);
        }
        exec.shutdown();
       System.out.println(ad);
    }

    public static void main(String[] args) {
        FutureTimeout timeout = new FutureTimeout();
        timeout.printAd();
    }

}
