package customCache.computable;

/**
 * honglinwei created on 4/1/20 inside the package - customCache.computable
 */
public class ExpensiveFunction implements Computable<String, Integer>{

    @Override
    public Integer compute(String arg) throws Exception {
        Thread.sleep(5000);
        return Integer.valueOf(arg);
    }
}
