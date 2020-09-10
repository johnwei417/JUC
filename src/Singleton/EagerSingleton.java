package Singleton;

/**
 * honglinwei created on 4/2/20 inside the package - Singleton
 */
public class EagerSingleton {
    private  static  EagerSingleton INSTANCE;
    static {
        INSTANCE = new EagerSingleton();
    }


    public EagerSingleton getInstance(){
        return INSTANCE;
    }
}
