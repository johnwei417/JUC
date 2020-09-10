package FunctionalInterface;

/**
 * honglinwei created on 3/30/20 inside the package - PACKAGE_NAME
 */
public class demo {
    public static void main(String[] args) {

        new Thread(()->{
            System.out.println("hello world");
        }).start();

        FunctionalInterface anInterface = ((i)->{
          return  "hello" + i;
        });
        System.out.println(anInterface.run(10));
    }


}
