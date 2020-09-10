package customCache.computable;

import java.util.HashMap;
import java.util.Map;

/**
 * honglinwei created on 4/1/20 inside the package - customCache.computable
 */
public class Cache3<A, V> implements Computable<A, V> {

    private final Map<A,V > cache = new HashMap<>();

    private final Computable<A,V> c;

    public Cache3(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public synchronized V compute(A arg) throws Exception {
        System.out.println("Get into the cache");
        V result = cache.get(arg);
        if(result == null){
            result = c.compute(arg);
            cache.put(arg,result);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        Cache2<String, Integer> stringIntegerCache2 = new Cache2<>(new ExpensiveFunction());
        new Thread(new Runnable() {
            @Override
            public void run() {
                Integer result = null;
                try {
                    result = stringIntegerCache2.compute("666");
                    System.out.println("1st result is: "+result);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();

        Thread.sleep(1000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Integer result = null;
                try {
                    result = stringIntegerCache2.compute("667");
                    System.out.println("2nd result is: "+result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Thread.sleep(1000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Integer result = null;
                try {
                    result = stringIntegerCache2.compute("666");
                    System.out.println("3rd result is: "+result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
