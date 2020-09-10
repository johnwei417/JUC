package Singleton;

/**
 * honglinwei created on 4/2/20 inside the package - Singleton
 */
//double-checked singleton
public class LazySingleton {
    private volatile static LazySingleton INSTANCE;

    public LazySingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (LazySingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LazySingleton();
                }
            }
        }
        return INSTANCE;
    }

}
