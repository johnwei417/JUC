package customCache;

/**
 * honglinwei created on 4/1/20 inside the package - customCache
 */

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/***
 * easiest version: using hashmap
 */
public class Cache1 {
    private final HashMap<String, Integer> cache = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        Cache1 cache1 = new Cache1();
        System.out.println("Start computing... ");
        Integer result = cache1.compute("13");
        System.out.println("1st Result is: "+ result);
        result =  cache1.compute("13");
        System.out.println("2nd Result is: "+result);
    }
    //add synchronized for thread-safe
    public synchronized Integer compute(String userId) throws InterruptedException {
        Integer result =  cache.get(userId);
        //check HashMap if there is any compute result store in the cache before
        if(result == null){
            //if we cannot find from cache, then we need compute and store into HashMap;
            result = doCompute(userId);
            cache.put(userId, result);
        }
        return result;
    }

    private Integer doCompute(String userId) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return new Integer(userId);
    }
}

